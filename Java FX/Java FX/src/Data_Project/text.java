package Data_Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MarkGame on 18-3-2015.
 */
public class text {
    public int checkString(String x) {

        ArrayList<String> goodWords = new ArrayList<String>();
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("positief.txt")));
            while (bfr.readLine() != null) {
                goodWords.add(bfr.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < goodWords.size(); i++) {
            if (x.contains(goodWords.get(i))) {
                return 1;
            }
        }
        return -1;

    }
}
