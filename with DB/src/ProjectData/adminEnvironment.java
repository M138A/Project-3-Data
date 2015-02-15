package ProjectData;

import javax.swing.*;

/**
 * Created by MarkGame on 14-2-2015.
 */
public class adminEnvironment {
    //the authorized user, which will be set in the constructor
    authorizedUser currentUser = null;

    /**
     * Link the logged in user to this session
     *
     * @param user which is set in adminEnvironment line 51
     */
    public adminEnvironment(authorizedUser user) {
        currentUser = user;
        showScreen();
    }

    /**
     * Shows the administrator screen
     */
    private void showScreen() {
        JFrame x = new JFrame();
        x.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        x.add(new JLabel("Welkom " + currentUser.getUsername()));
        x.setVisible(true);
        x.setSize(300, 300);
    }
}
