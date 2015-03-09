package Data_Project;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller extends fxmlController {

    @FXML Button submitButton;
    @FXML TextField userField;
    @FXML PasswordField passField;
    @FXML Label errorLabel;
    public void submitForm(ActionEvent actionEvent) {
       authorizedUser user = new authorizedUser();
        if(!user.checkCredentials(userField.getText(), passField.getText()))
        {
            errorLabel.setText("Invalid credentials");
        }
        else{

            fxmlController x = new fxmlController();
            x.setMainStage();

        }
    }


}
