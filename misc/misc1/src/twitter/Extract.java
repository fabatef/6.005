/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant tweet_start= tweets.get(0).getTimestamp();
    	Instant tweet_end= tweets.get(0).getTimestamp();
        for(int i=0; i< tweets.size(); i++ ){
        	if (tweets.get(i).getTimestamp().isBefore(tweet_start)){
        		tweet_start= tweets.get(i).getTimestamp();
           	} else if (tweets.get(i).getTimestamp().isAfter(tweet_end)){
           		tweet_end= tweets.get(i).getTimestamp();
           	}
        }
        return new Timespan(tweet_start, tweet_end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The @ cannot be immediately preceded by an alphanumeric or
     *         underscore character (A-Z, a-z, 0-9, _). For example, an email
     *         address like bitdiddle@mit.edu does not contain a mention of the
     *         username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> mentioned = new HashSet<String>();
    	for(Tweet tweet: tweets){
    		String[] tweet_words= tweet.getText().split(" ");
    		char at = '@';
    		for (String word: tweet_words){
    			if(word.charAt(0)==(at)){
    				System.out.println(word.substring(1));
    				mentioned.add(word.substring(1));
      			}
    		}

    	}
    	return mentioned;
    }

}
