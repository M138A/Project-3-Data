package Data_Project;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class weerInfo {

    private double gemid;
    private String Descriptionfinal;
    private String translate;
    private String image;



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
            Descriptionfinal = cwd.getWeatherInstance(0).getWeatherDescription();

            //Descriptionfinal =  description.substring(89, 107); // dit moet anders

            gemid = (Math.floor((mid)*10)/10.0); // < klopt geen fuck van vlgens mij
           // System.out.println("Temperatuur:(min) " + Math.floor((minC)*10)/10.0 + "/ (max)" + Math.ceil((maxC)*10)/10.0  + "\'C");
            System.out.println(String.valueOf(Math.floor((gemid) * 10) / 10.0) + "\'C " + Descriptionfinal);
            //getWeatherConditionImg(getDescrip());
        }
    }

    public String getTranslate() throws Exception {
        Translate.setClientId("buildinganalytics_1");
        Translate.setClientSecret("o5oXm3zRYKidc/Xb3L9FD8TYwmvcfgoWBhWIw6Y0Vc8=");
        translate = Translate.execute(getDescrip(), Language.ENGLISH, Language.DUTCH);
        return translate;
    }

    /**
     * TODO
     * @param x
     */
    public String getWeatherConditionImg(String x) {
        ArrayList<String> Thunderstorm = new ArrayList<String>();
        Thunderstorm.add("thunderstorm");
        ArrayList<String> Rain = new ArrayList<String>();
        Rain.add("drizzle");
        Rain.add("light rain");
        ArrayList<String> Snow = new ArrayList<String>();
        Snow.add("snow");
        Snow.add("sleet");
        ArrayList<String> Mist = new ArrayList<String>();
        Mist.add("mist");
        Mist.add("haze");
        ArrayList<String> Clouds = new ArrayList<String>();
        Clouds.add("clouds");
        ArrayList<String> Sunny = new ArrayList<String>();
        Sunny.add("clear sky");
        Sunny.add("sun");

        for (String s : Thunderstorm) if (s.contains(x)) return "Images/15.png";
        for (String s : Rain) if (s.contains(x)) return "Images/12.png";
        for (String s : Snow) if (s.contains(x)) return "Images/19.png";
        for (String s : Mist) if (s.contains(x)) return "Images/11.png";
        for (String s : Clouds) if (s.contains(x)) return "Images/8.png";
        for (String s : Sunny) if (s.contains(x)) return "Images/1.png";
        return null;
    }

    public double getGemid() {
        return gemid;
    }

    public String getDescrip(){
        return Descriptionfinal;
    }

    public String getImage() {
        return getWeatherConditionImg(getDescrip());
    }
}