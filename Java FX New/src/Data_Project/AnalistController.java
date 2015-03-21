package Data_Project;

import facebook4j.*;
import facebook4j.ResponseList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Review;
import twitter4j.GeoLocation;
import twitter4j.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import se.walkercrou.places.Place;


/**
 * Created by bart on 10-3-2015.
 */
public class AnalistController implements Initializable {
    //Social media count
    private int Googlecount;
    private int Twittercount;
    private int Facebookcount;

    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String passwordDB = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    private dbConnect connect = new dbConnect();
    Connection con;
    //fxml


    @FXML
    public TextArea outputTempArea;

    @FXML
    public TextArea outputTextArea;
    @FXML
    public TextField inputTextArea;

    public AnalistController() throws Exception {
        con = connect.connectToDb();
    }

    @FXML
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "sample.fxml");
    }

    @FXML
    private void dataButtonAction() {
        fxmlController logout = new fxmlController();
        DataController dc = new DataController();
        logout.setLogin("Data", "Data.fxml");
    }
    @FXML
    PieChart Piechart;

    @FXML
    private void TwitzoekButtonAction() {

            String inp = inputTextArea.getText();
            // input > twitternaam > return timelijn/tweets naar output

            try {
                outputTextArea.setText(" ");
                Twitter latestTweetChecker = new TwitterFactory().getInstance();
                List<Status> statuses = latestTweetChecker.getUserTimeline(inp);
                outputTextArea.appendText("Showing " + " " + inp + " " + "timeline.\r\n \r\n");
                for (Status status : statuses) {
                    outputTextArea.appendText(status.getUser().getName() + ":" +
                            status.getText() + "\r\n");
                }
            }catch (TwitterException te) {
                te.printStackTrace();
                outputTextArea.appendText("Failed : " + te.getMessage());
                System.exit(0);
            }
    }


    @FXML
    private void HashtaggButtonAction() { //twitter api non oob
        String input = inputTextArea.getText(); // kijkt wat je hebt getypt
        int socialmediaID = 0;
        try {
            outputTextArea.setText(" "); // cleanup
            twitter4j.Twitter twitter =  TwitterFactory.getSingleton();
            Query query = new Query(input);
            query.setCount(50);// aantal tweets LAG!
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {// print uit

                String Usrname = status.getUser().getScreenName();
                int FollowerCount = status.getUser().getFollowersCount();
                int RetweetCount = status.getRetweetCount();
                String Message = status.getText();
                GeoLocation Location = status.getGeoLocation();
                int FavoriteCount = status.getFavoriteCount();
                String end = "=-=-=-=-=-=";


                outputTextArea.appendText( "Naam : @" + Usrname + " : "+
                        "\n\r volger count : " + FollowerCount +
                        "\n\r bericht : " + Message +
                        "\n\r locatie :" + Location +
                        "\n\r fav count:" + FavoriteCount +
                        "\n\r retweet count :" + RetweetCount +
                        "\n\r "+ end +
                        "\n\r"      );
                try {
                    Connection con = connect.connectToDb();
                    String sql = "INSERT INTO Bericht (Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(2, Message);
                    preparedStatement.setString(3, "Twitter");
                    preparedStatement.setInt(4, 1);
                    preparedStatement.execute();

                    sql = "INSERT INTO twitter (Bericht_BerichtID,retweet, favorite,username,gerelateerd,volgercount) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setInt(1, connect.getSocialMedia("Twitter"));
                    preparedStatement2.setInt(2, RetweetCount);
                    preparedStatement2.setInt(3, FavoriteCount);
                    preparedStatement2.setString(4, Usrname);
                    preparedStatement2.setInt(5, 1);
                    preparedStatement2.setInt(6, FollowerCount);
                    preparedStatement2.execute();
                    /**Close connection with Database **/
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (TwitterException te) { // error message
            te.printStackTrace();
            outputTextArea.appendText("Failed : " + te.getMessage());
            System.exit(0);
        }
    }

    @FXML
    private void PlacesButtonAction() {

        GooglePlaces client = new GooglePlaces("AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg");
        ArrayList<Place> places = (ArrayList<Place>) client.getPlacesByQuery("Euromast", GooglePlaces.MAXIMUM_RESULTS);
        int review = 0;
        List<Review> l1 = null;
        outputTextArea.setText(" "); // cleanup

        for (int i = 0; i < places.size(); i++) {

            Place me = places.get(i);
            String rating = String.valueOf(me.getRating());
           // outputTextArea.appendText(rating + "\n\r");
            if (rating.equals("-1.0")) {
                continue;
            }else {
                try {
                    Connection con = connect.connectToDb();

                    String sql = "INSERT INTO Bericht (Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(2, "Google Rating");
                    preparedStatement.setString(3, "Google");
                    preparedStatement.setInt(4, 1);
                    preparedStatement.execute();

                    sql = "INSERT INTO google (Bericht_BerichtID,rating) VALUES (?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setInt(1, connect.getSocialMedia("Google"));
                    preparedStatement2.setString(2, rating);
                    preparedStatement2.execute();
                    System.out.println("Google+ Rating updated");


                    /**Close connection with Database **/
                    con.close();
                    /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
                } catch (SQLException e) {
                    System.out.println("geen nieuwe updates");
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
    /*
     https://developers.facebook.com/tools/explorer/145634995501895/?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.2
      */

    @FXML
    private void FacebookButtonAction() throws FacebookException {
        outputTextArea.setText(" "); // cleanup

        Facebook facebook = new FacebookFactory().getInstance();
        ResponseList<Post> feeds = facebook.getFeed("313850611958467",new Reading().limit(75));

        Page pgId = facebook.getPage("313850611958467");
        int likeCount = pgId.getLikes(); // aantal likes op de facebook pagina van rotterdam
        outputTextArea.appendText("De facebook pagina van Rotterdam Centraal heeft op dit moment : " +likeCount +" likes\n\r \n\r");
        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
            Integer sharecount = post.getSharesCount();
            if (sharecount == null) {
                sharecount = 0;
            }
            // Print textarea test
            outputTextArea.appendText("Message : \n\r" + message +
                    "\n\r Shares : " + sharecount+
                    "\n\r ======\n\r");
            try {
                Connection con = connect.connectToDb();

                String sql = "INSERT INTO Bericht (Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(2, message);
                preparedStatement.setString(3, "FaceBook");
                preparedStatement.setInt(4, 1);
                preparedStatement.execute();

                sql = "INSERT INTO facebook (Bericht_BerichtID,likes,gedeeld) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                preparedStatement2.setInt(1, connect.getSocialMedia("Facebook"));
                preparedStatement2.setInt(2, likeCount);
                preparedStatement2.setInt(3, sharecount);
                preparedStatement2.execute();
                System.out.println("Facebook data updated");


                /**Close connection with Database **/
                con.close();
                /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
            } catch (SQLException e) {
                System.out.println("geen nieuwe updates");
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void WeerButtonAction() throws IOException {
        weerInfo info = new weerInfo();
        outputTempArea.appendText(String.valueOf(info.getGemid())+ "'C " +String.valueOf(info.getDescrip()));

        }
    @FXML
    private void UpdateWeather() throws Exception {
        weerInfo info = new weerInfo();
        System.out.println(info.getTranslate());

        try {
            Connection con = connect.connectToDb();
            String sql = "INSERT INTO Weersvoorspelling (Datum, Temperatuur, Weersituatie) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setDouble(2, info.getGemid());
            preparedStatement.setString(3, info.getDescrip());
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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



