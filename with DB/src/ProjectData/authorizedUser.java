package ProjectData;

import java.sql.*;

/**
 * Created by Mark on 14-2-2015.
 * The test user :
 * username : user
 * password : pass
 */
public class authorizedUser {
    Boolean authorized = false;
    String username;

    public boolean checkCredentials(String username1, String password) {
        Connection conn = null;
        String dbPass = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connect to database
            conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT password FROM Leden WHERE username='" + username1 + "'";
            //execute sql query
            ResultSet rs = statement.executeQuery(sql);
            //process result of query
            while (rs.next()) {
                dbPass = rs.getString("password");
            }
            //close database connection
            conn.close();

        } catch (SQLException e) {//throwed when there is an sql exception
            e.printStackTrace();
        } catch (ClassNotFoundException e) {//throwed when com.mysql.jdbc.Driver is not found

            e.printStackTrace();
        }
        if (dbPass != null && dbPass.equals(password)) {//when password is not null and equals entered password : log user in
            authorized = true;
            username = username1;
            adminEnvironment x = new adminEnvironment(this);
            return true;//returned to loginForm to close the login screen
        } else {
            return false;//returned to loginForm to show error message
        }
    }
    public String getUsername()
    {
        return username;
    }


}
