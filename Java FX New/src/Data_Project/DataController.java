package Data_Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DataController implements Initializable {

    @FXML
    PieChart Piechart;

    @FXML
    public LineChart<String, Integer> Linechart;
    @FXML
    public StackedAreaChart<Number, Number> Stackedchart;
    @FXML
    public ScatterChart<Number, Number> Scatterchart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Socialmediacount SocMed = null;
        weerhistorie WH = null;
        try {
            WH = new weerhistorie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SocMed = new Socialmediacount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // working piechart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Google", SocMed.getGoogle()),
                        new PieChart.Data("Twitter", SocMed.getTwitter()),
                        new PieChart.Data("Facebook", SocMed.getFacebook()));

        Piechart.setData(pieChartData);
        //  working line chart
        Linechart.getXAxis().setAutoRanging(true);
        Linechart.getYAxis().setAutoRanging(true);

        XYChart.Series series = new XYChart.Series<>();
        series.setName("Rotterdam");
        Linechart.setTitle("Temperatuur Rotterdam");
        Linechart.getYAxis().setLabel("Temperatuur");
        Linechart.getXAxis().setLabel("Dag");
        series.getData().add(new XYChart.Data<>("dag-6", WH.getD1()));
        series.getData().add(new XYChart.Data<>("dag-5",WH.getD2()));
        series.getData().add(new XYChart.Data<>("dag-4",WH.getD3()));
        series.getData().add(new XYChart.Data<>("dag-3",4));
        series.getData().add(new XYChart.Data<>("dag-2",14));
        series.getData().add(new XYChart.Data<>("dag-1",6));
        series.getData().add(new XYChart.Data<>("Vandaag",4));

        Linechart.getData().add(series);

        // Stackedchart
        Stackedchart.getXAxis().setAutoRanging(true);
        Stackedchart.getYAxis().setAutoRanging(true);

        XYChart.Series xseries = new XYChart.Series<>();
        xseries.setName("Facebook");
        Stackedchart.setTitle("Like Count");
        Stackedchart.getYAxis().setLabel("likes");
        Stackedchart.getXAxis().setLabel("Dag");
        xseries.setName("XYChart.Series 1");
        xseries.getData().add(new XYChart.Data<>(0,WH.getD1()));
        xseries.getData().add(new XYChart.Data<>(1,WH.getD2()));
        xseries.getData().add(new XYChart.Data<>(2,WH.getD3()));
        xseries.getData().add(new XYChart.Data<>(3,4));
        xseries.getData().add(new XYChart.Data<>(4,14));
        xseries.getData().add(new XYChart.Data<>(5,6));
        xseries.getData().add(new XYChart.Data<>(6,4));
        xseries.getData().add(new XYChart.Data<>(7,14));
        xseries.getData().add(new XYChart.Data<>(8,6));
        xseries.getData().add(new XYChart.Data<>(9,4));
        xseries.getData().add(new XYChart.Data<>(10,14));
        xseries.getData().add(new XYChart.Data<>(11,6));
        xseries.getData().add(new XYChart.Data<>(12,4));
        xseries.getData().add(new XYChart.Data<>(13,6));

        Stackedchart.getData().add(xseries);
 /**       // Scatterchart TODO
        Scatterchart.getXAxis().setAutoRanging(true);
        Scatterchart.getYAxis().setAutoRanging(true);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Scatterstuff");
        Scatterchart.setTitle("teesstt");
        Scatterchart.getYAxis().setLabel("test");
        Scatterchart.getXAxis().setLabel("test");
        series1.setName("XYChart.Series 5");
        series1.getData().add(new XYChart.Data(4.2, 193.2));
        series1.getData().add(new XYChart.Data(2.8, 33.6));
        series1.getData().add(new XYChart.Data(23.8, 13.6));


        Scatterchart.getData().addAll(series1);*/
    }
}


