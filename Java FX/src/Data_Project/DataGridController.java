package Data_Project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TextFieldTableCell;

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
    @FXML TableColumn userCol;
    @FXML TableColumn passCol;
    @FXML TableColumn roleCol;
    ObservableList<authorizedUser> users = FXCollections.observableList(new ArrayList<authorizedUser>());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUserTable(userTable);
    }

    private void setUserTable(javafx.scene.control.TableView userTable) {

        this.userTable = userTable;

        userTable.setEditable(true);
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
        userCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passCol.setCellFactory(TextFieldTableCell.forTableColumn());
        roleCol.setCellFactory(TextFieldTableCell.forTableColumn());
       userTable.getItems().setAll(users);
    }

    public void updatePassword (TableColumn.CellEditEvent stCellEditEvent) {
       authorizedUser x = (authorizedUser) stCellEditEvent.getRowValue();
        String username = x.getUsername();
        String password = x.getPassword();
        x.updatePassword(username,stCellEditEvent.getNewValue().toString());
    }

    public void updateUsername(TableColumn.CellEditEvent stCellEditEvent) {
        authorizedUser x = (authorizedUser) stCellEditEvent.getRowValue();
        x.updateUsername(stCellEditEvent.getOldValue().toString(),stCellEditEvent.getNewValue().toString());

    }

    public void updateRole(TableColumn.CellEditEvent stCellEditEvent) {
        authorizedUser x = (authorizedUser) stCellEditEvent.getRowValue();
        x.updateRole(x.getUsername(), stCellEditEvent.getNewValue().toString());
    }


}
