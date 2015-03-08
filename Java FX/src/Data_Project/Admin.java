package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;


public class Admin{
    @FXML Button submitButton;
    @FXML TextField userField;
    @FXML PasswordField passField;
    @FXML Label errorLabel;

    public Button getSubmitButton() {
        return submitButton;
    }
}
