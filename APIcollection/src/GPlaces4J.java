
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Review;

import java.util.ArrayList;
import java.util.List;

// lib > https://github.com/windy1/google-places-api-java
// werkt niet atm - java.lang.NoSuchFieldError: INSTANCE - dependency probleem in xml ?
public class GPlaces4J {
    public static void main(String[] args) {

        GooglePlaces client = new GooglePlaces("AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg");
        ArrayList<Place> places = (ArrayList<Place>) client.getPlacesByQuery("Euromast", GooglePlaces.MAXIMUM_RESULTS);
        int review = 0;
        List<Review> l1 = null;
        for (int i = 0; i < places.size(); i++) {
            Place me = places.get(i);
            System.out.println(me.getRating());
        }
    }
}

