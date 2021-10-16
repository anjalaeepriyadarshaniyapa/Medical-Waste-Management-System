package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import MedicalWasteManagementSystem.Database.Const;
import MedicalWasteManagementSystem.Database.DatabaseHandler;
import MedicalWasteManagementSystem.tableViewModel.InefectiousObjectViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by v.Kodithuwakku on 9/18/2021.
 * Controller for generating Pie Chart
 */

public class InfectiousObjectTypePieChartController {
    @FXML
    private PieChart pieChart;

    @FXML
    private JFXButton loadColorCode_btn;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        //Load Pie Chart Button Functionality
        loadColorCode_btn.setOnAction(event -> {
            //Get Data from DB and create InfectiousObjectViewModel and entering data to pie chart object
            ResultSet resultSetInfectiousWaste = databaseHandler.populateTableData(Const.INFECTIOUS_WASTE_TABLE);
            List<InefectiousObjectViewModel> infectiousWasteList = new ArrayList<InefectiousObjectViewModel>();
            List<PieChart.Data> infectiousWasteListPeiChartData = new ArrayList<PieChart.Data>();
            try {
                while (resultSetInfectiousWaste.next()) {
                    infectiousWasteList.add(new InefectiousObjectViewModel(
                            resultSetInfectiousWaste.getString("object"),
                            Float.parseFloat(resultSetInfectiousWaste.getString("wasteperday"))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            infectiousWasteList.forEach((infectiousWasteObject) ->
                    infectiousWasteListPeiChartData.add(new PieChart.Data(infectiousWasteObject.getObjecName(), infectiousWasteObject.getWasteAmount())));

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(infectiousWasteListPeiChartData);

            pieChart.setData(pieChartData);
        });
    }

}
