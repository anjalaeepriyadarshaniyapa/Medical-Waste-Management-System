package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import MedicalWasteManagementSystem.Database.Const;
import MedicalWasteManagementSystem.Database.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by v.Kodithuwakku on 9/18/2021.
 */
public class WasteAmountPieChartController {


    @FXML
    private PieChart pieChart;

    @FXML
    private JFXButton loadChart_btn;

    @FXML
    private Label infectiousWasteLabel;

    @FXML
    private Label nonInfectiousWasteLabel1;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        //Load Pie Chart Button Functionality
        loadChart_btn.setOnAction(event -> {

            //Get Data from DB and enter data to pie chart object
            ResultSet resultSetInfectiousWaste = databaseHandler.populateTableData(Const.INFECTIOUS_WASTE_TABLE);
            ResultSet resultSetNonInfectiousWaste = databaseHandler.populateTableData(Const.NON_INFECTIOUS_WASTE_TABLE);
            int noOfInfectiousWasteLines = 0;
            int noOfNonInfectiousWasteLines = 0;

            try {
                while (resultSetInfectiousWaste.next()) {
                    noOfInfectiousWasteLines = noOfInfectiousWasteLines + 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                while (resultSetNonInfectiousWaste.next()) {
                    noOfNonInfectiousWasteLines = noOfNonInfectiousWasteLines + 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Infectional Waste", noOfInfectiousWasteLines),
                    new PieChart.Data("Non Infectional Waste", noOfNonInfectiousWasteLines)
            );

            int totalWaste = noOfInfectiousWasteLines + noOfNonInfectiousWasteLines;
            float noOfInfectiousWasteLinesPer = (float) ((noOfInfectiousWasteLines * 100) / totalWaste);
            float noOfNonInfectiousWasteLinesPer = (float) ((noOfNonInfectiousWasteLines * 100) / totalWaste);

            pieChart.setData(pieChartData);

            infectiousWasteLabel.setText("Infectional Waste Percentage : " + noOfInfectiousWasteLinesPer + "%");
            nonInfectiousWasteLabel1.setText("Non Infectional Waste Percentage : " + noOfNonInfectiousWasteLinesPer + "%");
        });
    }

}


