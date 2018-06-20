import java.io.IOException;
import java.net.URL;
import java.util.*;

import twitter.Tweet;
import twitter.TweetReader;

/**
 * Twitter API access.
 */
public class TwitterAPI {
    
    private static final String SAMPLE_SERVER = "http://courses.csail.mit.edu/6.005/ps1_tweets/tweetPoll-topic.py";
    
    // the entire tweet timeline for this example
    private static final List<Tweet> ALL_TWEETS = fetchTimelineAssertSuccess();
    
    // index into the tweet timeline by tweet ID
    private static final Map<Long, Integer> TWEET_INDEX = buildTweetIdIndex(ALL_TWEETS);
    
    private static List<Tweet> fetchTimelineAssertSuccess() {
        try {
            List<Tweet> tweets = TweetReader.readTweetsFromWeb(new URL(SAMPLE_SERVER));
            tweets.sort(Comparator.comparing(Tweet::getId).reversed());
            return Collections.unmodifiableList(tweets);
        } catch (IOException ioe) {
            throw new AssertionError(ioe);
        }
    }
    
    private static Map<Long, Integer> buildTweetIdIndex(List<Tweet> tweets) {
        Map<Long, Integer> index = new HashMap<>();
        for (int ii = 0; ii < tweets.size(); ii++) {
            index.put(tweets.get(ii).getId(), ii);
        }
        return index;
    }
    
    // Warning: bugs ahead!
    
    /**
     * Return at most ten tweets matching the search.
     * 
     * @param search search query
     * @return lowest-tweetId tweets matching the search, limited to ten results.
     *         If search's substring is nonempty, returned tweets must contain the substring.
     *         If search's lastId is nonzero, returned tweets must have IDs less than lastId.
     */
    public static List<Tweet> retrieveTweets(TweetSearch search) {
        // handle lastId
        int start= 0 ;
        if (search.getLastId() > 0) {
            start = TWEET_INDEX.get(search.getLastId()) + 1;
        }
        
        // handle no substring filter: return next 10 tweets
        if (search.getSubstring().isEmpty()) {
            return ALL_TWEETS.subList(start, Math.min(start + 10, ALL_TWEETS.size()));
        }
        
        // handle substring filter: find 10 matching tweets
        ListIterator<Tweet> iterator = ALL_TWEETS.listIterator(start);
        List<Tweet> matching = new ArrayList<>();
        while (iterator.hasNext() && matching.size() < 10) {
            Tweet next = iterator.next();
            if (next.getText().contains(search.getSubstring())) {
                matching.add(next);
            }
        }
        return Collections.unmodifiableList(matching);
    }
}
