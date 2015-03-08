import org.json.JSONException;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Lappie on 2/25/2015.
 */
public class Temperatuur extends JFrame {

    private JPanel rootPanel;
    private JButton button1;
    private JTextArea textArea1;
    private JPanel GraphTemp;
    private JTable table1;


    public Temperatuur() throws IOException, JSONException {
        super("Temperatuur");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);
        setVisible(true);
        setSize(180, 75);

        // huidige temp
        new weerInfo();
        weerInfo info = new weerInfo();
        textArea1.append(String.valueOf(info.getGemid())+ "'C" +String.valueOf(info.getDescrip()));
        textArea1.setEditable(false);


        }
    }


