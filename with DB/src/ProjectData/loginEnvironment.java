package ProjectData;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Bart on 23-2-2015.
 */
public class loginEnvironment extends JFrame{

    protected static JPasswordField passField;
    protected static JTextField userField;
    protected static JFrame main = new JFrame();
    protected static JPanel Jpane;
    protected static JLabel errorMessageLabel = new JLabel();


    public static void basicEnviromentScreen() {
        main.add(createJPanefield());
        main.setSize(1000,800);
        main.setLayout(new BorderLayout());
        main.add(Jpane, BorderLayout.CENTER);

        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setVisible(true);
        
    }

    public static JPanel createJPanefield() {
        JPanel panefield = new JPanel();
        panefield.setSize(1000, 800);
        Jpane = panefield;
        return panefield;
    }

    /**
     * UsernameLabel *
     * @param x
     * @param y
     * @return
     */
    public static JLabel createUserNameLabel(String text, int x, int y) {
        JLabel uLabel = new JLabel(text);
        uLabel.setBounds(x, y, 100, 20);
        return uLabel;
    }

    /**
     * UsernameField *
     * @param x
     * @param y
     * @return
     */
    public static JTextField createUserNameField(int x, int y) {
        JTextField uField = new JTextField();
        uField.setBounds(x, y, 100, 20);
        userField = uField;
        return uField;
    }

    /**
     * PasswordField *
     * @param x
     * @param y
     * @return
     */
    public static JPasswordField createPassWordField(int x, int y) {
        JPasswordField uPassField = new JPasswordField();
        uPassField.setBounds(x, y, 100, 20);
        passField = uPassField;
        return uPassField;
    }


}
