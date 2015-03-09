package Data_Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application implements MainImpl {
    public static Stage theStage ;
    public static  Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        theStage = primaryStage;
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        theStage.setTitle("Log in");
        theStage.setScene(new Scene(root, 500, 500));
        theStage.show();

    }
    public Parent getRoot()
    {
        return root;
    }
    public Stage getTheStage()
    {
        return theStage;
    }
/*
    public void refreshStage(Stage x)
    {
        theStage = x;
    }*/

    public static void main(String[] args) {
        launch(args);

    }
}
