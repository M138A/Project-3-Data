
import facebook4j.*;

public class FB4J {

// lib > http://facebook4j.org
// facebook authentication moet elke 2 uur refreshed worden
    // zie facebook4j.properties
    // https://developers.facebook.com/tools/explorer/145634995501895/?method=GET&path=me%3Ffields%3Did%2Cname&version=v2.2
    // verniew access token
public static void main(String[] args) throws FacebookException {

    // Facebook factory
    Facebook facebook = new FacebookFactory().getInstance();

    // list met 50'' comments ''
    ResponseList<Post> feeds = facebook.getFeed("313850611958467",/* < dit
       verwijst naar een OPENBARE  facebook pagina van rotterdam centraal
        */  new Reading().limit(50));
        // for loop 50x         ^
        for (int i = 0; i < feeds.size(); i++) {
            Post post = feeds.get(i);
            String message = post.getMessage();
            // Print console test
            System.out.println(message +"\n\r");

            ///////////////////////
        }           
    }
}