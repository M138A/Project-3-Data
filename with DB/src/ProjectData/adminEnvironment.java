package ProjectData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class adminEnvironment extends loginForm {
    //the authorized user, which will be set in the constructor
    authorizedUser currentUser = null;
    private static String[] userGrant = {"", "1", "2"};
    private static JComboBox<String> userList = new JComboBox<String>(userGrant);


    /**
     * Link the logged in user to this session
     *
     * @param user which is set in adminEnvironment line 51
     */
    public adminEnvironment(authorizedUser user) {
        currentUser = user;
        showScreen();
    }

    /**
     * Shows the administrator screen
     */
    private void showScreen() {
        JFrame x = new JFrame();
        x.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        x.add(new JLabel("Welkom " + currentUser.getUsername()));
        x.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        x.setLocationRelativeTo(null);
        x.setLayout(null);
        x.add(createUserNameLabel(10, 20));
        x.add(createUserNameField(90, 20));
        x.add(createPasswordLabel(10, 50));
        x.add(createPassWordField(90, 50));
        x.add(createComboBox(90, 80));
        x.add(createValidationButton(90, 120));
        x.setVisible(true);
        x.setSize(300, 300);
    }

    public static JComboBox<String> createComboBox(int x, int y) {
        userList.setBounds(x, y, 100, 20);
        return userList;
    }

    public static JButton createValidationButton(int x, int y) {
        JButton btnAddAccount = new JButton("Voeg Account Toe");
        btnAddAccount.setBounds(x, y, 100, 20);
        btnAddAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = String.valueOf(passField.getPassword());
                String functions = (String) userList.getSelectedItem();
                System.out.println(username + password + functions);
            }
        });
        return btnAddAccount;
    }

    public boolean checkCredentials(String username1, String password1, String functions1) {
        Connection conn = null;
        String dbPass = null;
        crypt encryptAES = new crypt();

        try {
            crypt.encrypt(password1);
            Class.forName("com.mysql.jdbc.Driver");
            //connect to database
            conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
            Statement statement = conn.createStatement();
            String sql;
            sql = "INSERT INTO \"Leden\" (ID, \"username\",\"password\", \"role\") \n" +
                    "VALUES(4 , username1,password1, 2);";
            //execute sql query
            ResultSet rs = statement.executeQuery(sql);
            //process result of query
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
        return true;
    }
}

