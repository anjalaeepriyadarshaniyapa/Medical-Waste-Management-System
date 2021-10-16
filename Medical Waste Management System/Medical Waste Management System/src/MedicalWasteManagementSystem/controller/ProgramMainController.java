package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Created on 9/15/2021.
 * Main Frame of the Program
 */
public class ProgramMainController {
    @FXML
    private JFXButton infectiousWasteButton;

    @FXML
    private JFXButton nonInfectiousWasteButton;

    @FXML
    private JFXButton pieChartWasteAmountButton;

    @FXML
    private JFXButton infactiousObjectTypeButton;

    @FXML
    private JFXButton infectiousWasteTypeButton;

    @FXML
    private AnchorPane anchorView;

    @FXML
    void initialize() {
        infectiousWasteButton.setOnAction(event -> {
            try {
                AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/infectious_waste.fxml"));
                anchorView.getChildren().setAll((mainSubView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        nonInfectiousWasteButton.setOnAction(event -> {
            try {
                AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/non_infectious_waste.fxml"));
                anchorView.getChildren().setAll((mainSubView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        pieChartWasteAmountButton.setOnAction(event -> {
            try {
                AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/piechart(wasteamount).fxml"));
                anchorView.getChildren().setAll((mainSubView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        infactiousObjectTypeButton.setOnAction(event -> {
            try {
                AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/piechart(infectious_object_type).fxml"));
                anchorView.getChildren().setAll((mainSubView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        infectiousWasteTypeButton.setOnAction(event -> {
            try {
                AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/linechart(infectious_waste_type).fxml"));
                anchorView.getChildren().setAll((mainSubView));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //Initial load of Infectious Waste Tab on Log in
        try {
            AnchorPane mainSubView = FXMLLoader.load(getClass().getResource("/MedicalWasteManagementSystem/view/infectious_waste.fxml"));
            anchorView.getChildren().setAll((mainSubView));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

