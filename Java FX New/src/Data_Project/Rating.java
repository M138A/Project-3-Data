package Data_Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by MarkGame on 18-3-2015.
 */
public class Rating {
    private Double Ratingvandaag;
    private Double Ratingmin1;
    private Double Ratingmin2;
    private Double Ratingmin3;
    public Double getRtoday() {
        return Ratingvandaag;
    }
    public Double getRmin1(){
        return Ratingmin1;
    }
    public Double getRmin2(){
        return Ratingmin2;
    }
    public Double getRmin3(){
        return Ratingmin3;
    }

    private dbConnect connect = new dbConnect();

    public Rating() throws Exception {
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-25\"";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingvandaag = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-24\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin1 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-23\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin2 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-22\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin3 = rs.getDouble(1);
            }
            con.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getRating(String x) {
        int score  = 0;
        try {
            BufferedReader bfrP = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("positief.txt")));
            BufferedReader bfrN= new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("negatief.txt")));
            while (bfrP.readLine() != null) {
                if (x.contains(bfrP.readLine())) {
                    score++;
                }
            }
            while(bfrN.readLine() != null)
            {
                if (x.contains(bfrN.readLine())) {
                    score--;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return score;
    }

}
