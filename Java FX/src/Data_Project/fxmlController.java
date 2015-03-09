package Data_Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * Created by mark&bart on 9-3-15.
 */
public class fxmlController extends Main {

    public void setMainStage(String Title, String fxmlURL, int SceneWidth, int SceneHeight) {
        try {

            root = FXMLLoader.load(getClass().getResource(fxmlURL));
            theStage.setTitle(Title);
            theStage.setScene(new Scene(root, GetScreenWorkingWidth(), GetScreenWorkingHeight()));
            theStage.centerOnScreen();
            theStage.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int GetScreenWorkingWidth() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
    }

    public static int GetScreenWorkingHeight() {
        return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
    }


}
