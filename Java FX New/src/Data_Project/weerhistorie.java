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

    public weerhistorie() throws Exception {
        try {
            Connection con = connect.connectToDb();
            Statement statement = con.createStatement();
            String sql = "SELECT Temperatuur FROM mijnma1q_PrjData.Weersvoorspelling;";
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                Tempmin7 = rs.getInt(1);
            }
            if(rs.next()){
                Tempmin6 = rs.getInt(1);
            }
            if(rs.next()){
                Tempmin5 = rs.getInt(1);
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
    public double getD1() {
        return Tempmin7;
    }
    public double getD2(){
        return Tempmin6;
    }
    public double getD3(){
        return Tempmin5;
    }
}
