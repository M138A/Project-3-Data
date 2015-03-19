package Data_Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by MarkGame on 19-3-2015.
 */
public class DataGridController implements Initializable{
    @FXML
    public javafx.scene.control.TableView<authorizedUser> userTable;
    ObservableList<authorizedUser> users = FXCollections.observableList(new ArrayList<authorizedUser>());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserTable(userTable);

    }

    private void setUserTable(javafx.scene.control.TableView userTable) {
        this.userTable = userTable;
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://mijnmarklinbaan.nl/mijnma1q_PrjData", "mijnma1q_prjuser", "password");
            Statement statement = conn.createStatement();
            String sql;
            sql = "SELECT username,password,role FROM Leden ";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                users.add(new authorizedUser(rs.getString("username"),rs.getString("password"),rs.getString("role")));
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
       userTable.getItems().setAll(users);
    }
}
