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
import twitter4j.Paging;
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
import java.util.Random;
import java.util.ResourceBundle;

import se.walkercrou.places.Place;


/**
 * Created by bart on 10-3-2015.
 */
public class AnalistController implements Initializable {

    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String passwordDB = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    //SQL
    private dbConnect connect = new dbConnect();
    private Rating positief = new Rating();
    private Connection con;

    public AnalistController() throws Exception {
        con = connect.connectToDb();
    }
    //fxml
    @FXML
    PieChart Piechart;
    @FXML
    public TextArea outputTempArea;
    @FXML
    public TextArea outputTextArea;
    @FXML
    public TextField inputTextArea;

    @FXML // log out scherm
    private void logoutButtonAction() {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "Login.fxml");
    }

    @FXML // data scherm,
    private void dataButtonAction() {
        fxmlController logout = new fxmlController();
        DataController dc = new DataController();
        logout.setLogin("Data", "Data.fxml");
    }

    @FXML // haalt tweets op van een timelijn
    private void TwitzoekButtonAction() {

            String inp = inputTextArea.getText();
            // input > twitternaam > return timelijn/tweets naar output

            try {
                outputTextArea.setText(" "); // clean up
                Paging page = new Paging (1, 50); // aantal tweets'perpage'
                Twitter latestTweetChecker = new TwitterFactory().getInstance();
                List<Status> statuses = latestTweetChecker.getUserTimeline(inp, page);
                outputTextArea.appendText("Showing " + " " + inp + " " + "timeline.\r\n \r\n");
                String Message;
                String Usrname;
                int FollowerCount;
                int RetweetCount;
                int FavoriteCount;
                String ID;  // SQL MSG ID

                for (Status status : statuses) {

                    // variable
                    Message =  status.getText();
                    Usrname =  status.getUser().getName();
                    FollowerCount = status.getUser().getFollowersCount();
                    RetweetCount = status.getRetweetCount();
                    FavoriteCount = status.getFavoriteCount();
                    ID = String.valueOf(status.getId());

                    //sql connectie
                    Connection con = connect.connectToDb();
                    String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(3, Message);
                    preparedStatement.setString(4, "Twitter");
                    preparedStatement.setInt(5, positief.getRating(Message));
                    preparedStatement.execute();

                    sql = "INSERT INTO twitter (Bericht_BerichtID,retweet, favorite,username,gerelateerd,volgercount) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setString(1, ID);
                    preparedStatement2.setInt(2, RetweetCount);
                    preparedStatement2.setInt(3, FavoriteCount);
                    preparedStatement2.setString(4, Usrname);
                    preparedStatement2.setInt(5, 1);
                    preparedStatement2.setInt(6, FollowerCount);
                    preparedStatement2.execute();
                    /**Close connection with Database **/
                     con.close();
                //textareaoutput
                    outputTextArea.appendText(status.getUser().getName() + ":" +
                            status.getText() + "\r\n");
                }
            }catch (TwitterException te) {
                te.printStackTrace();
                outputTextArea.appendText("Failed : " + te.getMessage());
                System.exit(0);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @FXML// haalt tweets op van een hashtagg
    private void HashtaggButtonAction() { //twitter api non oob
        String input = inputTextArea.getText(); // kijkt wat je hebt getypt
        int socialmediaID = 0;
        try {
            outputTextArea.setText(" "); // cleanup
            twitter4j.Twitter twitter =  TwitterFactory.getSingleton();
            Query query = new Query(input);
            query.setCount(50);// aantal tweets LAG!
            QueryResult result = twitter.search(query);
            String ID;              // SQL MSG ID
            for (Status status : result.getTweets()) {// print uit

                String Usrname = status.getUser().getScreenName();
                int FollowerCount = status.getUser().getFollowersCount();
                int RetweetCount = status.getRetweetCount();
                String Message = status.getText();
                GeoLocation Location = status.getGeoLocation();
                int FavoriteCount = status.getFavoriteCount();
                ID = String.valueOf(status.getId());      //SQL MSG ID
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
                    String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(3, Message);
                    preparedStatement.setString(4, "Twitter");
                    preparedStatement.setInt(5,  positief.getRating(Message));
                    preparedStatement.execute();

                    sql = "INSERT INTO twitter (Bericht_BerichtID,retweet, favorite,username,gerelateerd,volgercount) VALUES (?,?,?,?,?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setString(1, ID);
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

    @FXML// haalt rating van google places op
    private void PlacesButtonAction() {

        GooglePlaces client = new GooglePlaces("AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg");
        ArrayList<Place> places = (ArrayList<Place>) client.getPlacesByQuery("RdamCentraal", GooglePlaces.MAXIMUM_RESULTS);
        int review = 0;
        List<Review> l1 = null;
        outputTextArea.setText(" "); // cleanup
        Random random = new Random(); // random ID GEN
        String ID; // SQL MSG ID
        for (int i = 0; i < places.size(); i++) {
            // variables
            ID = "GP"+ String.valueOf(random.nextInt(55123124) + 15123); // SQL MSG ID
            Place me = places.get(i);
            Double rating = me.getRating();
            // if geen rating, do nothing
            if (rating.equals("-1.0")) {
                continue;
            }else {
                try {
                    Connection con = connect.connectToDb();

                    String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, ID);
                    preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                    preparedStatement.setString(3, "Google Location Rating");
                    preparedStatement.setString(4, "Google");
                    preparedStatement.setInt(5, 0);
                    preparedStatement.execute();

                    sql = "INSERT INTO google (Bericht_BerichtID,rating) VALUES (?,?)";
                    PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                    preparedStatement2.setString(1, ID);
                    preparedStatement2.setDouble(2, rating);
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
                    System.out.println("Class niet gevonden");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }
    /*
     https://developers.facebook.com/tools/explorer/145634995501895/?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.2
      */

    @FXML // haalt facebook posts op van de facebook pagina Rotterdam centraal
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
            String ID = post.getId();     // SQL MSG ID
            if (sharecount == null) {
                sharecount = 0;
            }
            // Print textarea test
            outputTextArea.appendText("Message : \n\r" + message +
                    "\n\r Shares : " + sharecount+
                    "\n\r ======\n\r");
            try {
                Connection con = connect.connectToDb();

                String sql = "INSERT INTO Bericht (BerichtID,Datum, Beschrijving,socialmedia,Positief) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, ID);
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
                preparedStatement.setString(3, message);
                preparedStatement.setString(4, "FaceBook");
                preparedStatement.setInt(5, positief.getRating(message));
                preparedStatement.execute();

                sql = "INSERT INTO facebook (Bericht_BerichtID,likes,gedeeld) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = con.prepareStatement(sql);
                preparedStatement2.setString(1, ID);
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
    @FXML// haalt facebook posts op van de facebook pagina Rotterdam centraal v2
    private void FacebookData(){

     try {
        FacebookData fb = new FacebookData();
     } catch (FacebookException e) {
        e.printStackTrace();
     }
     try {
        ResponseList<Post> fbmsges = new FacebookData().getFeeds();
        for (int i = 0; i < fbmsges.size(); i++) {
            Post post = fbmsges.get(i);
            String message = post.getMessage();

            System.out.println(message);
        }
     } catch (FacebookException e) {
        e.printStackTrace();
     }

    }
    @FXML // Button voor het ophalen van het weer.
    private void WeerButtonAction() throws IOException {
        weerInfo info = new weerInfo();
        outputTempArea.appendText(String.valueOf(info.getGemid()) + "'C " + String.valueOf(info.getDescrip()));

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



