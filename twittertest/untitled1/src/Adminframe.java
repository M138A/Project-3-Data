import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import twitter4j.*;
import java.util.List;
import java.io.*;
import org.jfree.*;

public class Adminframe extends JFrame {

    public static void main(String[] args) {
        Adminframe frameTabel = new Adminframe();
    }

    public static JTextArea inputTextArea = new JTextArea("Twitter naam");
    public static JTextArea outputTextArea = new JTextArea();
    public static JButton zoekButton = new JButton("Zoek");
    public static JButton saveButton = new JButton("Save");
    public static JButton dbButton = new JButton("DB");



    JLabel welcome = new JLabel(" Welcome Back, Admin");
    JPanel panel = new JPanel();

    Adminframe(){
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


        //panel.add();

        // locations
        welcome.setBounds(75, 75,200 , 150);
        inputTextArea.setBounds(1, 1, 150, 60);
        zoekButton.setBounds(151, 1, 75, 60);
        outputTextArea.setBounds(350, 1, 900, 900);
        saveButton.setBounds(226, 1, 75, 60);

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
        });

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
        dbButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String tweets = outputTextArea.getText();
                try {

                } catch () {

                }
            }
        });

        //ending
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }//end admin frame

}
