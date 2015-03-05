package Data_Project;

import java.sql.*;

/**
 * Created by Mark on 14-2-2015.
 * The test user :
 * username : user2
 * password : pass
 */
public class authorizedUser{
    Boolean authorized = false;
    String username;

    public boolean checkCredentials(String username1, String notEncPassword) {
        Connection conn = null;
        String dbPass = null;

        crypt encryptAES = new crypt();
        String password = null;

        try {
            password = encryptAES.encrypt(notEncPassword);
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
            return false;
        } catch (ClassNotFoundException e) {//throwed when com.mysql.jdbc.Driver is not found

            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dbPass != null && dbPass.equals(password)) {//when password is not null and equals entered password : log user in
            authorized = true;
            username = username1;

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
