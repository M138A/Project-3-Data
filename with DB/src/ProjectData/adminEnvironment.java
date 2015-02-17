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

    /***
     * Bart **
     * Method setBounds to JComboBox and creates it*
     * @param x-location
     * @param y-Location
     * @return the JComboBox<String>
     */
    public static JComboBox<String> createComboBox(int x, int y) {
        userList.setBounds(x, y, 100, 20);
        return userList;
    }

    /***
     * Bart **
     * Method creates button and handling the event when the button is pressed* 
     * @param x-location
     * @param y-location
     * @return the Jbutton
     */
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
                connectionDB(username, password, functions);

            }
        });
        return btnAddAccount;

    }

    /*** 
     * Bart ** 
     * Method, which connects to the database and executes the SQL Query (login credentials in DB)
     * @username u
     * @password p
     * @role (1,2) f
     */
    public static void connectionDB(String u, String p, String f)
    {
        try {
            String username = "mijnma1q_prjuser";
            String password = "password";
            String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
            int foo = Integer.parseInt(f);  // String f to int (1 or 2)
            Connection connection = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO Leden (ID, username, password, role) VALUES (null,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,u);
            preparedStatement.setString(2,p);
            preparedStatement.setInt(3,foo);
            preparedStatement.execute();
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("Database connected!");
    }

}



