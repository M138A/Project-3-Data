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
    public StackedAreaChart<String, Integer> Stackedchart;


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


        // TODO
        Linechart.getXAxis().setAutoRanging(true);
        Linechart.getYAxis().setAutoRanging(true);

        XYChart.Series series = new XYChart.Series<>();
        series.setName("Rotterdam");
        Linechart.setTitle("Temperatuur Rotterdam");
        Linechart.getYAxis().setLabel("Temperatuur");
        Linechart.getXAxis().setLabel("Dag");
        series.getData().add(new XYChart.Data<>("dag-6",WH.getD1()));
        series.getData().add(new XYChart.Data<>("dag-5",WH.getD2()));
        series.getData().add(new XYChart.Data<>("dag-4",WH.getD3()));
        series.getData().add(new XYChart.Data<>("dag-3",4));
        series.getData().add(new XYChart.Data<>("dag-2",14));
        series.getData().add(new XYChart.Data<>("dag-1",6));
        series.getData().add(new XYChart.Data<>("Vandaag",4));

        Linechart.getData().add(series);
    }
}


