package Data_Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DataChartController implements Initializable {

    @FXML
    PieChart Piechart;

    @FXML
    public LineChart<String, Integer> Linechart;
    @FXML
    public StackedAreaChart<Number, Number> Stackedchart;
    @FXML
    public ScatterChart<Number, Number> Scatterchart;
    //referenties

    public DataChartController() throws Exception {
    }

    @FXML
    public void BackButton(){
        fxmlController UD = new fxmlController();
        UD.setMainStage("Analyse", "AnalistWindow.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ratinghistorie rat = null;
        Socialmediacount SocMed = null;
        weerhistorie WH = null;
        try {
            rat = new Ratinghistorie();
            WH = new weerhistorie();
            SocMed = new Socialmediacount();


        } catch (Exception e) {
            e.printStackTrace();
        }


/**        // working piechart op basis van social media aantal
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Google", SocMed.getGoogle()),
                        new PieChart.Data("Twitter", SocMed.getTwitter()),
                        new PieChart.Data("Facebook", SocMed.getFacebook()));

        Piechart.setData(pieChartData);*/

        // pie chart die de resultaten laat zien van de query builder!
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(ResultController.SocialmediaSQLnaam[0], ResultController.SocialmediaSQLresult[0]),
                        new PieChart.Data(ResultController.SocialmediaSQLnaam[1], ResultController.SocialmediaSQLresult[1]),
                        new PieChart.Data(ResultController.SocialmediaSQLnaam[2], ResultController.SocialmediaSQLresult[2]));

        Piechart.setData(pieChartData);


        //  working line chart
        Linechart.getXAxis().setAutoRanging(true);
        Linechart.getYAxis().setAutoRanging(true);

        XYChart.Series series = new XYChart.Series<>();
        XYChart.Series rating = new XYChart.Series<>();

        rating.setName("Positief");
        series.setName("Temp");
        Linechart.setTitle("Temperatuur/positiviteit Rotterdam");
        Linechart.getYAxis().setLabel("Temperatuur");
        Linechart.getXAxis().setLabel("Dag");

        series.getData().add(new XYChart.Data<>("Vandaag",WH.getToday()));
        series.getData().add(new XYChart.Data<>("dag-1", WH.getminD1()));
        series.getData().add(new XYChart.Data<>("dag-2",WH.getminD2()));
        series.getData().add(new XYChart.Data<>("dag-3",WH.getminD3()));
        series.getData().add(new XYChart.Data<>("dag-4",WH.getminD4()));
        series.getData().add(new XYChart.Data<>("dag-5",WH.getminD5()));
        series.getData().add(new XYChart.Data<>("dag-6",WH.getminD6()));

        rating.getData().add(new XYChart.Data<>("Vandaag",rat.getRtoday()));
        rating.getData().add(new XYChart.Data<>("dag-1",rat.getRmin1()));
        rating.getData().add(new XYChart.Data<>("dag-2",rat.getRmin2()));
        rating.getData().add(new XYChart.Data<>("dag-3",rat.getRmin3()));
        rating.getData().add(new XYChart.Data<>("dag-4",rat.getRmin4()));
        rating.getData().add(new XYChart.Data<>("dag-5",rat.getRmin5()));
        rating.getData().add(new XYChart.Data<>("dag-6",rat.getRmin6()));

        Linechart.getData().addAll(series,rating);

        // Stackedchart
        Stackedchart.getXAxis().setAutoRanging(true);
        Stackedchart.getYAxis().setAutoRanging(true);

        XYChart.Series xseries = new XYChart.Series<>();
        xseries.setName("Facebook");
        Stackedchart.setTitle("Like Count");
        Stackedchart.getYAxis().setLabel("likes");
        Stackedchart.getXAxis().setLabel("Dag");
        xseries.setName("XYChart.Series 1");
        xseries.getData().add(new XYChart.Data<>(0,2227));
        xseries.getData().add(new XYChart.Data<>(1,2231));
        xseries.getData().add(new XYChart.Data<>(2,2247));
        xseries.getData().add(new XYChart.Data<>(3,2253));
        xseries.getData().add(new XYChart.Data<>(4,2273));


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


