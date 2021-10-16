package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import MedicalWasteManagementSystem.Database.DatabaseHandler;
import MedicalWasteManagementSystem.animations.Shaker;
import MedicalWasteManagementSystem.model.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

//Controller for Login Functionality
public class LoginController {

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        //Functionality for Login Button
        loginButton.setOnAction(event -> {
            String loginUsername = userName.getText().trim();
            String loginPassword = password.getText().trim();

            User user = new User();
            user.setUsername(loginUsername);
            user.setPassword(loginPassword);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;

            if (userRow != null) {
                try {
                    while (userRow.next()) {
                        counter++;
                        String loggedUsersName = userRow.getString("name");
                        System.out.println("Login Successful" + loggedUsersName);
                    }
                    if (counter == 1) {
                        showProgramMainScreen();
                        System.out.println("Login Successful");
                    } else {
                        Shaker userNameShaker = new Shaker(userName);
                        Shaker passwordShaker = new Shaker(password);
                        passwordShaker.shake();
                        userNameShaker.shake();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Show Main Screen in a Successfull Login Attempt
    private void showProgramMainScreen() {
        loginButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/MedicalWasteManagementSystem/view/programMainFrame.fxml"));

        try {
            loader.setRoot(loader.getRoot());
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        ProgramMainController programMainController = loader.getController();

        stage.showAndWait();
    }

}
