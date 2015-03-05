package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class Controller{
    @FXML Button submitButton;
    @FXML TextField userField;
    @FXML PasswordField passField;
    public void submitForm(ActionEvent actionEvent) {
       authorizedUser user = new authorizedUser();
        user.checkCredentials(userField.getText(), passField.getText());

    }
}
