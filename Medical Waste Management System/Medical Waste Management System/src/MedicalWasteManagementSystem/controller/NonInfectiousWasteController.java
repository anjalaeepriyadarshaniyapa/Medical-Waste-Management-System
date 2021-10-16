package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import MedicalWasteManagementSystem.Database.Const;
import MedicalWasteManagementSystem.Database.DatabaseHandler;
import MedicalWasteManagementSystem.FileHandlers.PDFSaver;
import MedicalWasteManagementSystem.model.Infectious_Waste;
import MedicalWasteManagementSystem.tableViewModel.WasteTableViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.Kodithuwakku on 9/15/2021.
 */
public class NonInfectiousWasteController {

    private DatabaseHandler databaseHandler;
    @FXML
    private TableView<WasteTableViewModel> infectiousWasteViewTable;

    @FXML
    private TableColumn<WasteTableViewModel, Integer> wasteId_clm;

    @FXML
    private TableColumn<WasteTableViewModel, String> typeWaste_clm;

    @FXML
    private TableColumn<WasteTableViewModel, String> object_clm;

    @FXML
    private TableColumn<WasteTableViewModel, String> typeOfContainer_clm;

    @FXML
    private TableColumn<WasteTableViewModel, String> wastePerDay_clm;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton loadButton;

    @FXML
    private JFXButton totalWasteDayButton;

    @FXML
    private JFXButton insertDataButton;

    @FXML
    private JFXTextField typeOfWaste_txt;

    @FXML
    private JFXTextField wasteObject_txt;

    @FXML
    private JFXTextField typeOfContainer_txt;

    @FXML
    private JFXTextField totalWastePerDay_txt;

    @FXML
    private JFXButton updateButton;

    @FXML
    private JFXTextField UpdateID_txt;

    @FXML
    private JFXTextField DeleteID_txt;

    @FXML
    private Label totalWasteDayOutput;

    @FXML
    private JFXButton savePDFButton;

    ObservableList<WasteTableViewModel> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        //Functionality for Delete button
        deleteButton.setOnAction(event -> {
            if (!DeleteID_txt.getText().trim().equals("")) {
                databaseHandler.deleteRecordFromWasteTable(Const.NON_INFECTIOUS_WASTE_TABLE, Integer.parseInt(DeleteID_txt.getText().trim()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Data Deleted Successfully.!");
                alert.showAndWait();
                DeleteID_txt.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setContentText("Please Enter an ID to Proceed");
                alert.showAndWait();
            }
        });

        //Functionality for Save PDF button
        savePDFButton.setOnAction(event -> {
            String filename = "GeneratedPDF/Non_Infectious_Waste_Details.pdf"; //PDF will be saved to the GeneratedPDF folder in project directory root
            PDFSaver pdfSaver = new PDFSaver();
            pdfSaver.SaveFile(Const.NON_INFECTIOUS_WASTE_TABLE, "Non Infectious Waste Details", filename, databaseHandler);
        });

        //Functionality for Update button
        updateButton.setOnAction(event -> {
            if (!UpdateID_txt.getText().trim().equals("")) {
                Infectious_Waste infectious_waste = new Infectious_Waste();
                infectious_waste.setType_of_waste(typeOfWaste_txt.getText().trim());
                infectious_waste.setObject(wasteObject_txt.getText().trim());
                infectious_waste.setType_of_container(typeOfContainer_txt.getText().trim());
                infectious_waste.setWasteperday(Float.parseFloat(totalWastePerDay_txt.getText().trim()));
                int id = Integer.parseInt(UpdateID_txt.getText().trim());
                try {
                    databaseHandler.updateRecordOfWasteTable(Const.NON_INFECTIOUS_WASTE_TABLE, id, infectious_waste);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("Data Updated Successfully.!");
                    alert.showAndWait();
                    typeOfWaste_txt.clear();
                    wasteObject_txt.clear();
                    typeOfContainer_txt.clear();
                    totalWastePerDay_txt.clear();
                    UpdateID_txt.clear();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setContentText("Please Enter an ID to Proceed");
                alert.showAndWait();
            }
        });

        //Functionality for Load Data button
        loadButton.setOnAction(event -> {
            oblist.removeAll();
            infectiousWasteViewTable.getItems().clear();

            //Creating Table View from data from DB
            wasteId_clm.setCellValueFactory(new PropertyValueFactory<>("id"));
            typeWaste_clm.setCellValueFactory(new PropertyValueFactory<>("type_of_waste"));
            object_clm.setCellValueFactory(new PropertyValueFactory<>("object"));
            typeOfContainer_clm.setCellValueFactory(new PropertyValueFactory<>("type_of_container"));
            wastePerDay_clm.setCellValueFactory(new PropertyValueFactory<>("wasteperday"));
            ResultSet resultSet = databaseHandler.populateTableData(Const.NON_INFECTIOUS_WASTE_TABLE);

            try {
                while (resultSet.next()) {
                    oblist.add(new WasteTableViewModel(Integer.parseInt(resultSet.getString("id")),
                            resultSet.getString("type_of_waste"),
                            resultSet.getString("object"),
                            resultSet.getString("type_of_container"),
                            Float.parseFloat(resultSet.getString("wasteperday"))));
                }
                infectiousWasteViewTable.setItems(oblist);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //Functionality for Display Total Waste Per Day button
        totalWasteDayButton.setOnAction(event -> {
            List<Float> allWastePerDays = new ArrayList<Float>();
            float sum = 0;
            ResultSet resultSet = databaseHandler.populateTableData(Const.NON_INFECTIOUS_WASTE_TABLE);
            try {
                while (resultSet.next()) {
                    allWastePerDays.add(resultSet.getFloat("wasteperday"));
                    for (float i : allWastePerDays) {
                        sum += i;
                    }
                    totalWasteDayOutput.setText(String.valueOf(sum));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        //Functionality for Insert Data button
        insertDataButton.setOnAction(event -> {
            if (!typeOfWaste_txt.getText().trim().equals("") ||
                    !wasteObject_txt.getText().trim().equals("") ||
                    !typeOfContainer_txt.getText().trim().equals("") ||
                    !totalWastePerDay_txt.getText().trim().equals("")) {
                Infectious_Waste infectious_waste = new Infectious_Waste();
                infectious_waste.setType_of_waste(typeOfWaste_txt.getText().trim());
                infectious_waste.setObject(wasteObject_txt.getText().trim());
                infectious_waste.setType_of_container(typeOfContainer_txt.getText().trim());
                infectious_waste.setWasteperday(Float.parseFloat(totalWastePerDay_txt.getText().trim()));
                databaseHandler.insertWasteData(Const.NON_INFECTIOUS_WASTE_TABLE, infectious_waste);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Data Added Successfully.!");
                alert.showAndWait();
                typeOfWaste_txt.clear();
                wasteObject_txt.clear();
                typeOfContainer_txt.clear();
                totalWastePerDay_txt.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fail");
                alert.setContentText("Please fill all fields to Proceed");
                alert.showAndWait();
            }
        });


    }
}
