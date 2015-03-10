package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by mark on 9-3-15.
 */
public class AdminWindow {
    @FXML
    public Button Grafiek;
    @FXML
    public Button RefreshData;
    @FXML
    public Button Logout;
    @FXML
    public Button AccountAddToDB;
    public TextField userField;
    public PasswordField passField;
    public ToggleGroup roleGroup;
    public RadioButton Analist;
    public RadioButton Administrator;
    @FXML
    private Button AddAccount;
    //Login credentials
    private final String usernameDB = "mijnma1q_prjuser";
    private final String password = "password";
    private final String url = "jdbc:mysql://mijnmarklinbaan.nl:3306/mijnma1q_PrjData";

    public AdminWindow() {

    }

    public void LogoutScreen(ActionEvent actionEvent) {
        fxmlController logout = new fxmlController();
        logout.setLogin("Log in", "sample.fxml");
    }

    public void addAccount(ActionEvent actionEvent) {
        String username = userField.getText();
        String password = passField.getText();
        try {
            String functions = ((RadioButton) roleGroup.selectedToggleProperty().getValue()).getId().toString();
            crypt AESCrypter = new crypt();
            password = AESCrypter.encrypt(password);
            /***
             * Checks If fields are empty, if they aren't the parameters are passed to the Database Method
             *
             */
            if (!username.isEmpty() & passField.getText().trim().length() != 0) {
                connectionDB(username, password, functions);
            } else {
                System.out.println("Voer data in!");
            }
        } catch (Exception e) {
            System.out.println("Voer alle velden in!");
        }

    }

    private void connectionDB(String username, String password, String functions) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, usernameDB, password);
            String sql = "INSERT INTO Leden (username, password, role) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, functions);
            preparedStatement.execute();
            /**Reports Account made in terminal**/
            System.out.println("Account gemaakt!");
            /**Close connection with Database **/
            connection.close();
            /**Catch exception when data can't be saved into database for example: There is nothing filled in **/
        }catch (SQLException e) {
            System.out.println("Username bestaat al!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
