package MedicalWasteManagementSystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import MedicalWasteManagementSystem.Database.Const;
import MedicalWasteManagementSystem.Database.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by v.Kodithuwakku on 9/18/2021.
 */
public class InfectiousWasteTypeLinechartController {

    @FXML
    private LineChart<?, ?> lineChartInfectiousWaste;

    @FXML
    private JFXButton loadLineChart_btn;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {
        databaseHandler = new DatabaseHandler();

        loadLineChart_btn.setOnAction(event -> {
            ResultSet resultSetInfectiousWaste = databaseHandler.populateTableData(Const.INFECTIOUS_WASTE_TABLE);
            XYChart.Series series = new XYChart.Series();

            try {
                while (resultSetInfectiousWaste.next()) {
                    series.getData().add(new XYChart.Data<>(resultSetInfectiousWaste.getString("type_of_waste"),
                            Float.parseFloat(resultSetInfectiousWaste.getString("wasteperday"))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            series.setName("Infectious Type of Waste");
            lineChartInfectiousWaste.getData().addAll(series);
        });


    }
}
