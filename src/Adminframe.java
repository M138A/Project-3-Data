import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Adminframe extends JFrame {

    public static void main(String[] args) {
        Adminframe frameTabel = new Adminframe();
    }

    JLabel welcome = new JLabel("Admin frame");
    JPanel panel = new JPanel();

    Adminframe(){
        super("Welcome");
        setSize(300,200);
        setLocation(500,280);
        panel.setLayout(null);

        welcome.setBounds(70, 50, 150, 60);

        panel.add(welcome);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
