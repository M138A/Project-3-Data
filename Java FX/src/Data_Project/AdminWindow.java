package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    private Button AddAccount;


    public AdminWindow()
    {

    }

    public void LogoutScreen(ActionEvent actionEvent) {
        fxmlController logout =  new fxmlController();
        logout.setLogin("Log in", "sample.fxml");
    }
}
