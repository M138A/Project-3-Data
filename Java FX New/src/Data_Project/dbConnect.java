package Data_Project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by MarkGame on 19-3-2015.
 */
public class dbConnect {
    public Connection connectToDb() throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        //connect to database
        Connection conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
        return conn;
    }
}
