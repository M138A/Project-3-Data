package ProjectData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;




public class adminEnvironment extends loginEnvironment implements DocumentListener {
    //the authorized user, which will be set in the constructor
    authorizedUser currentUser = null;
    /** Variables declarated outside Method scope, because they have to be used in different method**/
    private String[] userGrant = {"1", "2"};
    private JComboBox<String> userList = new JComboBox<String>(userGrant);
    /**Database login credentials**/
    private final String usernameDB = "mijnma1q_prjuser";
    private final String password = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";
    private JButton btnAddAccount = new JButton("Voeg Account Toe");

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
        x.add(createUserNameLabel("Username", 10, 20));
        x.add(createUserNameField(90, 20));
        userField.getDocument().addDocumentListener(this);
        x.add(createUserNameLabel("Password",10, 50));
        x.add(createPassWordField(90, 50));
        x.add(createComboBox(90, 80));
        x.add(createValidationButton(90, 120));
        x.setVisible(true);
        x.setSize(1000, 800);
    }

    /***
     * Bart **
     * Method setBounds to JComboBox and creates it*
     * @param x-location
     * @param y-Location
     * @return the JComboBox<String>
     */
    public JComboBox<String> createComboBox(int x, int y) {
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
    public JButton createValidationButton(int x, int y) {
        btnAddAccount.setBounds(x, y, 100, 20);
        btnAddAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = String.valueOf(passField.getPassword());
                String functions = (String) userList.getSelectedItem();
                crypt AESCrypter = new crypt();
                try {
                    password = AESCrypter.encrypt(password);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                /***
                 * Checks If fields are empty, if they aren't the parameters are passed to the Database Method
                 *
                 */
                if (!username.isEmpty() & passField.getText().trim().length() != 0 ) {
                    connectionDB(username, password, functions);
                }
                else {
                    System.out.println("Voer data in!");
                }
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
    public void connectionDB(String u, String p, String f)
    {
        try {
            int foo = Integer.parseInt(f);  // String f to int (1 or 2)
            Connection connection = DriverManager.getConnection(url, usernameDB, password);
            String sql = "INSERT INTO Leden (ID, username, password, role) VALUES (null,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, u);
            preparedStatement.setString(2, p);
            preparedStatement.setInt(3, foo);
            if (!checkIfUsernameExists()) {
                preparedStatement.execute();
            }
            /**Close connection with Database **/
            connection.close();
            /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
        }catch (SQLException e) {
            e.printStackTrace();
        }
        /**Reports Account made in terminal**/
        System.out.println("Account gemaakt!");
    }

    /**
     * WORK IN PROGRESS
     * @return
     * @throws SQLException
     */
    public boolean checkIfUsernameExists() throws SQLException {

        Connection connection = DriverManager.getConnection(url, usernameDB, password);
        Statement statement = connection.createStatement();
        String check = userField.getText();
        String user = null;
        String sql = "SELECT * FROM Leden WHERE username='" +check+ "'";

        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            user = rs.getString("username");
        }
        if (check.equals(user)) {
            btnAddAccount.setEnabled(false);
            return true;
        }
        else {
            btnAddAccount.setEnabled(true);
            return false;
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            checkIfUsernameExists();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            checkIfUsernameExists();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            checkIfUsernameExists();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}



