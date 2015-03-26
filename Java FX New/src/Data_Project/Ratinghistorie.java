package Data_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Lappie on 3/26/2015.
 */
public class Ratinghistorie {
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

    public Ratinghistorie() throws Exception {
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-26\"";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingvandaag = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-25\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin1 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-24\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin2 = rs.getDouble(1);
            }
            sql = "SELECT AVG(positief) AS Positief,datum FROM Bericht where datum = \"2015-03-23\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Ratingmin3 = rs.getDouble(1);
            }
            con.close();

        }catch (Exception e) {
            e.printStackTrace();

        }
    }
}
