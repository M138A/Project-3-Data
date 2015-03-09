package Data_Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by mark on 9-3-15.
 */
public class fxmlController extends Main {
    /*private static Stage x;
    private static Parent controllerRoot;
    private static Scene sc ;
    public  fxmlController()
    {
        x= getTheStage();
        controllerRoot = getRoot();
        sc = new Scene(root, 500, 500);
    }*/

    public void setMainStage() {
        try {

            root = FXMLLoader.load(getClass().getResource("sample2.fxml"));
            theStage.setTitle("Log in");
            theStage.setScene(new Scene(root, 700, 700));
            theStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
