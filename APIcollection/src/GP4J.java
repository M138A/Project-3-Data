import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;

import java.io.IOException;
import java.util.List;

// lib > code.google.com/p/google-http-java-client
public class GP4J {

  private static final String API_KEY =
      "AIzaSyCPvGEEJL6Y98Kr4JE1hmhlviX7XcHYYwQ";

  private static final String USER_ID = "100174611224494421741";//100174611224494421741 -- > rdam 113856749262391991218
  private static final int MAX_RESULTS = 25;

  static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
  static final JsonFactory JSON_FACTORY = new JacksonFactory();

  /** Feed of Google+ activities. */
  public static class ActivityFeed {

    /** List of Google+ activities. */
    @Key("items")
    private List<Activity> activities;

    public List<Activity> getActivities() {
      return activities;
    }
  }

    public static class ReviewFeed {

        /** List of Google+ activities. */
        @Key("items")
        private List<ReviewFeed> Reviews  ;

        public List<ReviewFeed> getReviews() {
            return Reviews;
        }
    }

  /** Google+ activity. */
  public static class Activity extends GenericJson {

    /** Activity URL. */
    @Key
    private String url;

    public String getUrl() {
      return url;
    }

    /** Activity object. */
    @Key("object")
    private ActivityObject activityObject;

    public ActivityObject getActivityObject() {
      return activityObject;
    }
  }

  /** Google+ activity object. */
  public static class ActivityObject {

    /** HTML-formatted content. */
    @Key
    private String content;

    public String getContent() {
      return content;
    }

    /** People who +1'd this activity. */
    @Key
    private PlusOners plusoners;

    public PlusOners getPlusOners() {
      return plusoners;
    }
  }

  /** People who +1'd an activity. */
  public static class PlusOners {

    /** Total number of people who +1'd this activity. */
    @Key
    private long totalItems;

    public long getTotalItems() {
      return totalItems;
    }
  }

  /** Google+ URL. */
  public static class PlusUrl extends GenericUrl {

    public PlusUrl(String encodedUrl) {
      super(encodedUrl);
    }

    @SuppressWarnings("unused")
    @Key
    private final String key = API_KEY;

    /** Maximum number of results. */
    @Key
    private int maxResults;

    public int getMaxResults() {
      return maxResults;
    }

    public PlusUrl setMaxResults(int maxResults) {
      this.maxResults = maxResults;
      return this;
    }

    /** Lists the public activities for the given Google+ user ID. */
    public static PlusUrl listPublicActivities(String userId) {
      return new PlusUrl(
          "https://www.googleapis.com/plus/v1/people/" + userId + "/activities/public");
    }
  }


    // print stuff
    private static void parseResponse(HttpResponse response) throws IOException {
        ActivityFeed feed = response.parseAs(ActivityFeed.class);

        if (feed.getActivities().isEmpty()) {
            System.out.println("No activities found.");
        } else {
            if (feed.getActivities().size() == MAX_RESULTS) {
                System.out.print("First ");
            }
            System.out.println(feed.getActivities().size() + " messages found:");
            for (Activity activity : feed.getActivities()) {

                System.out.println(activity.get("id") + ":" + activity.getActivityObject().getContent() ); // if null niet showen 2do
                System.out.println("+1's: " + activity.getActivityObject().getPlusOners().getTotalItems()+ "\n\r");
              //  System.out.println("URL: " + activity.getUrl());
               // System.out.println("ID: " + activity.get("id"));
            }
        }
    }


    private static void run() throws IOException {
    HttpRequestFactory requestFactory =
        HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
            @Override
          public void initialize(HttpRequest request) {
            request.setParser(new JsonObjectParser(JSON_FACTORY));
          }
        });
    PlusUrl url = PlusUrl.listPublicActivities(USER_ID).setMaxResults(MAX_RESULTS);
    url.put("fields", "items(id,url,object(content,plusoners/totalItems))");
    HttpRequest request = requestFactory.buildGetRequest(url);
    parseResponse(request.execute());
  }

  public static void main(String[] args) {
    if (API_KEY.startsWith("Enter ")) {
      System.err.println(API_KEY);
      System.exit(1);
    }
    try {
      try {
        run();
        return;
      } catch (HttpResponseException e) {
        System.err.println(e.getMessage());
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
    System.exit(1);
  }
}