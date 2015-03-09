package Data_Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * Created by mark on 9-3-15.
 */
public class fxmlController extends Main {

    public void setMainStage(String Title, String fxmlURL, int SceneWidth, int SceneHeight) {
        try {

            root = FXMLLoader.load(getClass().getResource(fxmlURL));
            theStage.setTitle(Title);
            theStage.setScene(new Scene(root, SceneWidth, SceneHeight));
            theStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
