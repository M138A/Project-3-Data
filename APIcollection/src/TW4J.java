import twitter4j.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class TW4J extends JFrame {

    public static void main(String[] args) {
        TW4J frameTabel = new TW4J();
    }

    public static JTextArea inputTextArea = new JTextArea("Twitter naam");
    public static JTextArea outputTextArea = new JTextArea();
    public static JButton zoekButton = new JButton("Zoek");
    public static JButton saveButton = new JButton("Save");
    public static JButton dbButton = new JButton("DB");
    public static JButton htButton = new JButton("#");



    JLabel welcome = new JLabel(" Welcome Back, Admin");
    JPanel panel = new JPanel();

    TW4J(){
        // frame/window zelf
        super("Welcome");
        setSize(1200,600); // size
       //setLocation(x,y); // locatie van window
        setLocationRelativeTo(null); // center
        panel.setLayout(null); // layout type geen

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
        panel.add(dbButton);
        panel.add(htButton);


        //panel.add();

        // locations
        welcome.setBounds(75, 75,200 , 150);
        inputTextArea.setBounds(1, 1, 150, 60);
        zoekButton.setBounds(151, 1, 75, 60);
        outputTextArea.setBounds(350, 1, 900, 900);
        saveButton.setBounds(226, 1, 75, 60);
        htButton.setBounds(1,75,75,60);

        getContentPane().add(panel);

        // event listeners
        // zoek

        zoekButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = inputTextArea.getText();
                // input > twitternaam > return timelijn/tweets naar output

                try {
                    panel.remove(welcome);
                    outputTextArea.setText(" ");
                    Twitter latestTweetChecker = new TwitterFactory().getInstance();
                    List<Status> statuses = latestTweetChecker.getUserTimeline(inp);
                    outputTextArea.append("Showing " + " " + inp + " " + "timeline.\r\n \r\n");
                    for (Status status : statuses) {
                        outputTextArea.append(status.getUser().getName() + ":" +
                                  status.getText() + "\r\n");
                    }
                }catch (TwitterException te) {
                    te.printStackTrace();
                    outputTextArea.append("Failed : " + te.getMessage());
                    System.exit(0);
                }
            }
        });//zoek button
    //#button
        htButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String inp = inputTextArea.getText();
                // input > twitternaam > return timelijn/tweets naar output

                try {
                    panel.remove(welcome);  //clean up
                    outputTextArea.setText(" "); // cleanup
                    twitter4j.Twitter twitter =  TwitterFactory.getSingleton();
                    Query query = new Query(inp);
                    QueryResult result = twitter.search(query);
                    for (Status status : result.getTweets()) {// print uit
                        outputTextArea.append("@" + status.getUser().getScreenName() + " : " + status.getText() + " : " + status.getGeoLocation()+ "\n\r");
                    }
                }catch (TwitterException te) {
                    te.printStackTrace();
                    outputTextArea.append("Failed : " + te.getMessage());
                    System.exit(0);
                }
            }
        });//# button
        // save txt
        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Writer writer = null;
                String tweets = outputTextArea.getText();
                try {
                    panel.remove(welcome);
                    writer = new FileWriter("C:\\Users\\Lappie\\Desktop\\test.txt");
                    writer.write(tweets); // schrijft content van output/tweets naar ^
                } catch (IOException e2) {
                    e2.printStackTrace();
                } finally {
                    if (writer != null) try { writer.close(); } catch (IOException ignore) {}
                }
            }
        });


        //ending
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }//end admin frame

}
