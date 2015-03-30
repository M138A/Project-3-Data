package Data_Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;


public class TestWindowController implements Initializable {
    public ToggleGroup raiting;
    public ToggleGroup temperatuur;
    @FXML
    private ToggleGroup socialmedia;
    private String sql = "";


    /**
     * Get the SocialMediaToggle
     * Bart
     */
    public void getSocialMediaGroup() {
       try {
           String toggle = ((RadioButton) socialmedia.selectedToggleProperty().getValue()).getText();
           if(toggle.equals("Alles")){
               sql = "";
               sql += "SELECT COUNT(IF(socialmedia='Twitter',1,null)), " +
                       "COUNT(IF(socialmedia='Facebook',1,null)), " +
                       "COUNT(IF(socialmedia='Google',1,null)) FROM Bericht";
               getTemperature();
           }
           else {
               sql = "";
               sql += "SELECT COUNT (IF(socialmedia='" + toggle + "',1,null)) FROM Bericht";
               getTemperature();
           }
       }
       catch (Exception e){
           System.out.println("Klik een button aan om een analyse te doen!");
       }
    }


    public void getTemperature() {

        try {
            String toggle = ((RadioButton) temperatuur.selectedToggleProperty().getValue()).getText();
            if(toggle.equals("Alle temperaturen")) {
                sql += " LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum WHERE temperatuur > - 50 ";
                getPositiveOrNegative();
            }
            else {
                sql += " LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum WHERE temperatuur " + toggle + "";
                getPositiveOrNegative();
            }
        }
        catch(Exception e){
            System.out.println("test");
        }
    }

    public String getPositiveOrNegative() {
        String positief = "positief >= 5";
        String negatief = "positief <= 5";
        String allebei = "positief > -50";

        try {
            String toggle = ((RadioButton) raiting.selectedToggleProperty().getValue()).getText();
            switch (toggle) {
                case "Allebei":
                    return sql += " AND" + " " + allebei;
                case "Positief":
                    return sql += " AND" + " " + positief;
                default:
                    return sql += " AND" + " " + negatief;
            }
        }catch (Exception e){
            System.out.println("Kies!");
        }
        return null;
    }


    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("Test Window is open!");
        System.out.println("\n\r");
    }

    public void testbutton(ActionEvent event) {
        getSocialMediaGroup();
        System.out.println(sql);
        System.out.println();
    }

    /**
     * SELECT Weersituatie, Bericht.Datum , Temperatuur, COUNT(socialmedia) FROM Bericht
     LEFT OUTER JOIN Weersvoorspelling ON Weersvoorspelling.Datum = Bericht.Datum
     WHERE positief > -50
     GROUP BY DATUM;

     */


}
