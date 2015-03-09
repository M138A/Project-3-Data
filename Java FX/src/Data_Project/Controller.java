package Data_Project;


import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
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
    private Stage window;


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



    public void goToScreen2(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Admin.fxml"));
        Parent root1 = loader1.load();
        submitButton.setOnAction(e -> window.setScene(new Scene(root1,500,500)));
        System.out.println("test");
    }



}
