package Data_Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by bart on 10-3-2015.
 */
public class AnalistController implements Initializable {

    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String passwordDB = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    public ImageView weerplaatje;
    //SQL
    private weerInfo weatherInfo = new weerInfo();
    private dbConnect connect = new dbConnect();
    private Rating positief = new Rating();
    private Connection con;
    Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public AnalistController() throws Exception {
        con = connect.connectToDb();

    }
    //fxml
    @FXML
    PieChart Piechart;
    @FXML
    public Label outputTempArea;
    @FXML
    public Label outputTempDisc;
    @FXML
    public Label outputDate;

    @FXML // log out scherm
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "Login.fxml");
    }

    @FXML // data scherm,
    private void UpdatedataButtonAction() {
        fxmlController UD = new fxmlController();
        UD.setMainStage("Update Data", "UpdateDataWindow.fxml");
    }

    @FXML // Button voor het ophalen van het weer.
    private void WeerButtonAction() throws Exception {
        outputTempArea.setText("");//cleanup
        outputTempDisc.setText("");
        weerInfo info = new weerInfo(); //init weerInfo
        outputTempArea.setText(String.valueOf(info.getGemid()) + "'C "); // vult labels met de juiste info uit de weerinfo class
        outputTempDisc.setText(String.valueOf(info.getTranslate()));
        //date
        outputDate.setText(dateFormat.format(cal.getTime()));


    }
    @FXML // Button voor het wegschrijven van het weer naar de db
    private void UpdateWeather() throws Exception {
        weerInfo info = new weerInfo();
        System.out.println(info.getTranslate());

        try {
            Connection con = connect.connectToDb();
            String sql = "INSERT INTO Weersvoorspelling (Datum, Temperatuur, Weersituatie) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setDouble(2, info.getGemid());
            preparedStatement.setString(3, info.getTranslate());
            preparedStatement.execute();
            System.out.println("Success?");

            /**Close connection with Database **/
            con.close();
            /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
        }catch (SQLException e) {
            System.out.println("Weer al ge-update, wacht tot morgen.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    // Start de volgende methodes als de het analisten scherm opent
    // maakt pie chart op basis van SQL query
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  System.out.println(weatherInfo.setWeatherImage(weatherInfo.getWeatherConditionImg(weatherInfo.getDescrip())));
       weerplaatje.setImage((new Image(weatherInfo.setWeatherImage(weatherInfo.getWeatherConditionImg(weatherInfo.getDescrip())))));
        try {
            WeerButtonAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Socialmediacount SocMed = null;

        try {
            SocMed = new Socialmediacount();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Google", SocMed.getGoogle()),
                        new PieChart.Data("Twitter", SocMed.getTwitter()),
                        new PieChart.Data("Facebook", SocMed.getFacebook()));

        Piechart.setData(pieChartData);



    }



}



