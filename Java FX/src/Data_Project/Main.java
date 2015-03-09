package Data_Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
      //  FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Admin.fxml"));
        Parent root = loader.load();
       // Parent root1 = loader1.load();
        Controller a1 = loader.getController();
        //Admin a2 = loader1.getController();
       /** a1.getSubmitButton().setOnAction(e -> {
                window.setScene(new Scene(root1,500,500));
               });**/
              window.setTitle("Log in");
              window.setScene(new Scene(root, 500, 500));
              window.show();
          }


    public static void main(String[] args) throws IOException {
       launch(args);

    }
}
