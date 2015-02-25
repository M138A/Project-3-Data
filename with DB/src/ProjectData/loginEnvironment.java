package ProjectData;

import javax.swing.*;
import javax.swing.text.Document;

/**
 * Created by Bart on 23-2-2015.
 */
public class loginEnvironment extends JFrame{

    protected static JPasswordField passField;
    protected static JTextField userField;
    protected static JFrame main = new JFrame();
    protected static JPanel Jpane = new JPanel();
    protected static JLabel errorMessageLabel = new JLabel();


    public static void basicEnviromentScreen() {
        main.add(Jpane);
        main.setSize(300, 200);
        main.setLayout(null);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setVisible(true);
        
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
     * UsernameLabel *
     * @param x
     * @param y
     * @return
     */
    public static JLabel createUserNameLabel(int x, int y) {
        JLabel uLabel = new JLabel("username");
        uLabel.setBounds(x, y, 100, 20);
        return uLabel;
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

    /**
     * PasswordLabel
     * @param x
     * @param y
     * @return
     */
    public static JLabel createPasswordLabel(int x, int y) {
        JLabel pLabel = new JLabel("password");
        pLabel.setBounds(x, y, 100, 20);
        return pLabel;
    }
}
