package ProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Groep 3 on 14-2-2015.
 * This class generates and shows the login form.
 */
public class loginForm extends loginEnvironment {

    public static void main(String[] args) {

        generateGUI();
    }


    public static void generateGUI() {
        JFrame main = new JFrame();
        main.setSize(300, 200);
        main.setLayout(null);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUpContent(main.getContentPane());
        main.setLocationRelativeTo(null);
        main.setVisible(true);
        errorMessageLabel.setForeground(Color.red);
        errorMessageLabel.setBounds(75,100,150,50);

    }

    public static void setUpContent(Container pane) {
        pane.add(createUserNameLabel(20, 20));
        pane.add(createUserNameField(150, 20));
        pane.add(createPasswordLabel(20, 50));
        pane.add(createPassWordField(150, 50));
        pane.add(createSubmitButton(75, 80));
        pane.add(errorMessageLabel);

    }
    

    public static JButton createSubmitButton(int x, int y) {
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
    public static void close()
    {
        
        main.setVisible(false);
    }
}
