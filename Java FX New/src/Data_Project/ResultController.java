package Data_Project;

import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by Lappie on 4/3/2015.
 */
public class ResultController implements Initializable {

    //setup
    private String ResultSQL = "leeg";
    private dbConnect connect = new dbConnect();
    private Connection con;
    //analyse variables
    private int socialmediaSQLcount1;
    private int socialmediaSQLcount2;
    private int socialmediaSQLcount3;
    int [] SocialmediaSQLresult = new int[3];
    String [] SocialmediaSQLnaam = new String[3];

    //Start analyse
    public ResultController() throws Exception {
        ResultSQL = AnalistController.SQLresult;
      //  System.out.println(ResultSQL);
        setResults();
    }
    //zet analyse results
    private void setResults() throws Exception {
        con = connect.connectToDb(); // open sql connectie

        try{ // probeer de data te analyseren
            Statement statement = con.createStatement(); // maakt sql statement
            ResultSet rs = statement.executeQuery(ResultSQL);// van de string
            ResultSetMetaData rsmd = rs.getMetaData(); // haalt de collumn naam op
            int columnsNummer = rsmd.getColumnCount(); // kijkt naar het aantal rijen beschikbaar
            System.out.println("--=SQL Results=--");

            while (rs.next()) { // loop op lengte van het aantal rijen
                for (int i = 1; i <= columnsNummer; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    SocialmediaSQLnaam[i -1] = rsmd.getColumnName(i);
                    SocialmediaSQLresult[i -1] = Integer.parseInt(columnValue);
                    System.out.print(SocialmediaSQLnaam[i -1] + " " + SocialmediaSQLresult[i -1]);
                }
                System.out.println("");
            }
            System.out.println("--=SQL Finished=--");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Niet alle data is gebruikt, probeer meer social media te gebruiken");
        }


        con.close(); // sluit sql connectie



    }
    public void initialize(URL url, ResourceBundle rb) {
    // bla
    }// returners



    public int getSocialmediaSQLcount3() {
        return socialmediaSQLcount3;
    }

    public int getSocialmediaSQLcount1() {
        return socialmediaSQLcount1;
    }

    public int getSocialmediaSQLcount2() {
        return socialmediaSQLcount2;
    }
}