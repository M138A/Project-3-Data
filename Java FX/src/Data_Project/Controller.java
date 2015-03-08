package Data_Project;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class Controller{
    @FXML Button submitButton;
    @FXML TextField userField;
    @FXML PasswordField passField;
    @FXML Label errorLabel;


    public Controller() {
        super();
    }

    public void submitForm(ActionEvent actionEvent) {
       authorizedUser user = new authorizedUser();
        if(!user.checkCredentials(userField.getText(), passField.getText()))
        {
            errorLabel.setText("Invalid credentials");
        }
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void goToScreen2(Event event) {
        System.out.println("test");
    }
}
