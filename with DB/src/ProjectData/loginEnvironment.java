package ProjectData;

import javax.swing.*;

/**
 * Created by Bart on 23-2-2015.
 */
public class loginEnvironment extends JFrame{

    protected static JPasswordField passField;
    protected static JTextField userField;
    protected static JFrame main = new JFrame();
    protected static JLabel errorMessageLabel = new JLabel();

    
    public static JTextField createUserNameField(int x, int y) {
        JTextField uField = new JTextField();
        uField.setBounds(x, y, 100, 20);
        userField = uField;
        return uField;
    }

    public static JLabel createUserNameLabel(int x, int y) {
        JLabel uLabel = new JLabel("username");
        uLabel.setBounds(x, y, 100, 20);
        return uLabel;
    }

    public static JPasswordField createPassWordField(int x, int y) {
        JPasswordField uPassField = new JPasswordField();
        uPassField.setBounds(x, y, 100, 20);
        passField = uPassField;
        return uPassField;
    }

    public static JLabel createPasswordLabel(int x, int y) {
        JLabel pLabel = new JLabel("password");
        pLabel.setBounds(x, y, 100, 20);
        return pLabel;
    }
    
}
