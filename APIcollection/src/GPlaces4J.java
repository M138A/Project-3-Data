
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;

import java.util.List;
// lib > https://github.com/windy1/google-places-api-java
// werkt niet atm - java.lang.NoSuchFieldError: INSTANCE - dependency probleem in xml ?
public class GPlaces4J {


    public static void main(String[] args) {

        GooglePlaces client = new GooglePlaces( "AIzaSyALbXTMU7FfHrYpokHmOYpvJBsXUioQYlg" );
        List<Place> places = client.getNearbyPlaces(51.925216,4.469301, 50000, GooglePlaces.MAXIMUM_RESULTS);

        for (int i = 0; i < places.size(); i++) {
            Place review = places.get(i);
          //  String message = review.getReviews();
          String message = String.valueOf(review.getReviews());

            // Print console test
          System.out.println(message +"\n\r");
        }

    }
}

