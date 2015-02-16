package resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import twitter4j.*;
import java.util.List;


public class Adminframe extends JFrame {

    public static void main(String[] args) {
        Adminframe frameTabel = new Adminframe();
    }

    public static JTextArea inputTextArea = new JTextArea("Twitter naam");
    public static JTextArea outputTextArea = new JTextArea();
    public static JButton zoekButton = new JButton("Zoek");
    public static JButton saveButton = new JButton("Save");



    JLabel welcome = new JLabel(" Welcome Back, Admin");
    JPanel panel = new JPanel();

    Adminframe(){
        super("Welcome");
        setSize(800,600); // size
       //setLocation(400,280); // locatie van window
        setLocationRelativeTo(null); // center
        panel.setLayout(null); // layout type

        //input
        inputTextArea.setBackground(Color.DARK_GRAY);
        inputTextArea.setForeground(Color.gray);

        //output
        outputTextArea.setEditable(false);
        outputTextArea.setForeground(Color.gray);
        outputTextArea.setBackground(Color.DARK_GRAY);

        // add stuff
        panel.add(welcome);
        panel.add(inputTextArea);
        panel.add(outputTextArea);
        panel.add(zoekButton);
        panel.add(saveButton);

        // locations
        welcome.setBounds(75, 75,200 , 150);

        inputTextArea.setBounds(1, 1, 150, 60);
        zoekButton.setBounds(151, 1, 75, 60);
        outputTextArea.setBounds(400, 1, 400, 600);
        saveButton.setBounds(226, 1, 75, 60);


        getContentPane().add(panel);

        // event listeners

        zoekButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = inputTextArea.getText();
                // input > twitternaam > return tweets naar output...

                try {
                    // The factory instance is re-useable and thread safe.
                    Twitter twitter = TwitterFactory.getSingleton();
                    List<Status> statuses = twitter.getHomeTimeline();
                    //System.out.println("Showing home timeline.");
                    outputTextArea.setText("Showing home timeline. \n" );
                    for (Status status : statuses) {
                        System.out.println(status.getUser().getName() + ":" +
                                status.getText());
                        outputTextArea.setText(status.getUser().getName() + ":" +
                                status.getText());
                    }
                    }catch (TwitterException te) {
                    te.printStackTrace();
                    // System.out.println("Failed : " + te.getMessage());
                    outputTextArea.setText("Failed : " + te.getMessage());
                    System.exit(0);
                }

                    // String str = outputTextArea.getText();
                    //  outputTextArea.setText(inp); // print in vak output
                    //  System.out.println(str);  // print in console

            }
        });


        //end
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
