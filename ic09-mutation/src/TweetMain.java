import java.util.List;

import twitter.Tweet;

public class TweetMain {
    
    /**
     * Print some recent (as of a few months ago) tweets.
     * @param args unused
     */
    public static void main(String[] args) {
        //TweetSearch search = new TweetSearch();
        
        // print recent tweets
        //doSearch(search);
        
        // print recent tweets filtered by topic
        for (String topic : new String[] { "comet", "physics" }) {
        	TweetSearch search = new TweetSearch();
        	//reduced the scope of search to prevent us from skipping over tweets
            search.setSubstring(topic);
            doSearch(search);
        }
    }
    
    /**
     * TODO: I'm pretty sure this should have a specification
     */
    public static void doSearch(TweetSearch search) {
        // find the first 10 matching tweets
        List<Tweet> found = TwitterAPI.retrieveTweets(search);
        //retrieveTweets uses subString(a partial view of ALL TWEETS) on ALLTWEETS list which means...
        
        // now find up to 50 total
        for (int ii = 1; ii < 5; ii++) {
            search.setLastId(found.get(found.size()-1).getId());
            List<Tweet> more = TwitterAPI.retrieveTweets(search);
            if (more.isEmpty()) { break; }
            found.addAll(more);
            //addAll over here is adding stuff to found but also back into ALLTWEETS
            // this results in repeated tweets appearing when we call this function
        }
        
        System.out.println("Searched for \"" + search.getSubstring() + "\", found " + found.size());
        for (Tweet tweet : found) {
            System.out.println("    " + tweet.getText().replaceAll("\n", "\u21B5"));
        }
    }
}
