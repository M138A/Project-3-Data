package ProjectData;

import java.sql.*;
/**
 * Created by Mark on 14-2-2015.
 * The test user :
 * username : user
 * password : pass
 */
public class authorizedUser extends loginForm {
    Boolean authorized = false;
    String username;
    public boolean checkCredentials(String username1, String password) {
        Connection conn = null;
        String dbPass = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Leden WHERE username='" + username1 + "'";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                dbPass = rs.getString("password");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (dbPass != null && dbPass.equals(password)) {
            //TODO redirect user

            authorized = true;
            username = username1;

            adminEnvironment x = new adminEnvironment(this);
            return true;
        } else {

            return false;
        }
    }

}
