package ProjectData;

import java.awt.*;

/**
 * Created by bart on 18-2-2015.
 */
public class analistEnvironment extends loginEnvironment {


    public static void addDataToPanel() {
        Jpane.add(createUserNameLabel("AnalistEnviroment", 100, 40));
        Jpane.setOpaque(true);
        Jpane.setBackground(Color.RED);

    }


}
