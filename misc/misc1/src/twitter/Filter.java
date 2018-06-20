/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all tweets in the list whose author is username.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
    	List<Tweet> all_tweets = new ArrayList<Tweet>();
    	for (Tweet tweet: tweets){
    		if(tweet.getAuthor()== username){
    			all_tweets.add(tweet);
    		}
    	}
    	return all_tweets;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param timespan
     *            timespan
     * @return all tweets in the list that were sent during the timespan.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
    	List<Tweet> tweetsInSpan = new ArrayList<Tweet>();
    	Instant startTime= timespan.getStart();
    	Instant endTime= timespan.getEnd();
    	for (Tweet tweet: tweets){
    		if (tweet.getTimestamp().isAfter(startTime) && tweet.getTimestamp().isBefore(endTime)){
    			tweetsInSpan.add(tweet);
    		}
    	}
    	return tweetsInSpan;
    }

    /**
     * Search for tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. Words must be
     *            nonempty and must not contain spaces.
     * @return all tweets in the list such that the tweet text (when represented
     *         as a sequence of nonempty words bounded by space characters and
     *         the ends of the string) includes *all* the words found in the
     *         words list, in any order. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
    	List<Tweet> tweetsContaining= new ArrayList<Tweet>();
    	for (Tweet tweet: tweets){
    		String[] tweetWords= tweet.getText().split(" ");
    		if (Arrays.asList(tweetWords).containsAll(words)){
    			tweetsContaining.add(tweet);
    		}
    	}
    	return tweetsContaining;
    }

}









