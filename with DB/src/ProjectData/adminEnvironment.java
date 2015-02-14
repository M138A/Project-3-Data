package ProjectData;

import javax.swing.*;

/**
 * Created by MarkGame on 14-2-2015.
 */
public class adminEnvironment {
    authorizedUser currentUser = null;
    public adminEnvironment(authorizedUser user){
        currentUser = user;
        showScreen();
    }
    private void showScreen()
    {
        JFrame x = new JFrame();
        x.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        x.add(new JLabel("Welkom " + currentUser.username));
        x.setVisible(true);
        x.setSize(300,300);

    }
}
