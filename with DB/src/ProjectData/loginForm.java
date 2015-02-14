package ProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 * Created by Mark on 14-2-2015.
 * This class generates and shows the login form.
 */
public class loginForm extends JFrame {
    private static JPasswordField passField;
    private static JTextField userField;
    private static JFrame main = new JFrame();
    private static JLabel errorMessageLabel = new JLabel();

    public static void main(String[] args) {
        generateGUI();
    }

    private static void generateGUI() {

        main.setSize(300, 200);
        main.setLayout(null);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpContent(main.getContentPane());
        main.setVisible(true);
        errorMessageLabel.setForeground(Color.red);
        errorMessageLabel.setBounds(75,100,150,50);

    }

    private static void setUpContent(Container pane) {
        pane.add(createUserNameLabel(20, 20));
        pane.add(createUserNameField(150, 20));
        pane.add(createPasswordLabel(20, 50));
        pane.add(createPassWordField(150, 50));
        pane.add(createSubmitButton(75, 80));
        pane.add(errorMessageLabel);

    }

    private static JTextField createUserNameField(int x, int y) {
        JTextField uField = new JTextField();
        uField.setBounds(x, y, 100, 20);
        userField = uField;
        return uField;
    }

    private static JLabel createUserNameLabel(int x, int y) {
        JLabel uLabel = new JLabel("username");
        uLabel.setBounds(x, y, 100, 20);
        return uLabel;
    }

    private static JPasswordField createPassWordField(int x, int y) {
        JPasswordField uPassField = new JPasswordField();
        uPassField.setBounds(x, y, 100, 20);
        passField = uPassField;
        return uPassField;
    }

    private static JLabel createPasswordLabel(int x, int y) {
        JLabel pLabel = new JLabel("password");
        pLabel.setBounds(x, y, 100, 20);
        return pLabel;
    }

    private static JButton createSubmitButton(int x, int y) {
        JButton sButton = new JButton("Login");
        sButton.setBounds(x, y, 100, 20);
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorizedUser user = new authorizedUser();
                if(user.checkCredentials(userField.getText(), String.valueOf(passField.getPassword()) ))
                {
                    close();
                }
                else{
                    errorMessageLabel.setText("Bad credentials");
                }

            }
        });
        return sButton;
    }
    private static void close()
    {
        main.setVisible(false);
    }
}
