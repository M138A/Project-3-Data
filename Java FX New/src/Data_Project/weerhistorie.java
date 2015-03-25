package Data_Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Lappie on 3/22/2015.
 */
public class weerhistorie {
    // week weer data
    private double Tempmin7;
    private double Tempmin6;
    private double Tempmin5;
    private double Tempmin4;
    private double Tempmin3;
    private double Tempmin2;
    private double Tempmin1;
    private double Tempmin0;

    ArrayList<Double> testar = new ArrayList();


    //SQL
    private dbConnect connect = new dbConnect();

    public weerhistorie() throws Exception {  //TODO dynamic datums
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where Datum = \"2015-03-25\"";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                Tempmin0 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = \"2015-03-24\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Tempmin1 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = \"2015-03-23\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Tempmin2 = rs.getDouble(1);
            }
            sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling where datum = \"2015-03-22\"";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                Tempmin3 = rs.getDouble(1);
            }
            con.close();
        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(double x = 1; x < 8; x = x+1) {
            testar.add(x);
            System.out.println(testar);
        }
    }
    public double getminD1() {
        return Tempmin1;
    }
    public double getminD2(){
        return Tempmin2;
    }
    public double getminD3(){
        return Tempmin3;
    }
    public double getminD4() {
        return Tempmin4;
    }
    public double getminD5(){
        return Tempmin5;
    }
    public double getminD6(){
        return Tempmin6;
    }
    public double getToday(){
        return Tempmin0;
    }
}
