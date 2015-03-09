import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;

public class weerInfo {
    double gemid;
    double getGemid() {
        return gemid;
    }
    String Descriptionfinal;
    String getDescrip(){
        return Descriptionfinal;
    }




    public weerInfo () throws IOException, JSONException {
        OpenWeatherMap owm = new OpenWeatherMap(""); // Open weather constructor
        CurrentWeather cwd = owm.currentWeatherByCityName("Rotterdam, NL"); // zoekt naar Rotterdam / pakt huidige temp info rotterdam
        //
        // Temperatuur API
        //
        if (cwd.getMainInstance().hasMaxTemperature() && cwd.getMainInstance().hasMinTemperature())
        { // als geldige max/min temp. bekend zijn :
            double max = cwd.getMainInstance().getMaxTemperature(); // max temp
            double min = cwd.getMainInstance().getMinTemperature(); // min temp
            double maxC = ((max - 32)*5)/9; // magic
            double minC = ((min - 32)*5)/9;// magic
            double mid = (maxC + minC) /2;// magic
            String description = cwd.getRawResponse();
            Descriptionfinal = "\n\r" + description.substring(89, 103);
            gemid = (Math.floor((mid)*10)/10.0);
           // System.out.println("Temperatuur:(min) " + Math.floor((minC)*10)/10.0 + "/ (max)" + Math.ceil((maxC)*10)/10.0  + "\'C");
            System.out.println(String.valueOf(Math.floor((gemid)*10)/10.0) + "\'C" + Descriptionfinal);
        }
    }
}