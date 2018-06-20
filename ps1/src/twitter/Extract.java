/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
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
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */

    public static Timespan getTimespan(List<Tweet> tweets) {
    	
    	Instant defaultIns= Instant.parse("2015-02-18T10:00:00Z");
    	
    	if (tweets.isEmpty()) { return new Timespan (defaultIns,defaultIns);} //return 0 as a time interval
    	
    	Instant start= tweets.get(0).getTimestamp(); //initializing the start time
    	Instant end= tweets.get(0).getTimestamp(); //initializing the end time
    
    	
        for(Tweet tweet: tweets ){
        	
        	Instant tweetTime= tweet.getTimestamp();
        	
        	if (tweetTime.isBefore(start)){ //if the current tweetTime is before the initialized start, update start 
        		start= tweetTime;
           	} else if (tweetTime.isAfter(end)){ //if the current tweetTime is after the initialzied end, update end
           		end= tweetTime;
           	}
        }
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    
    //am I not supposed to write test cases that don't satisfy the spec. empty lists?
    //what to do if there are punctuation marks right next to a twitter handle
    
    //helper function
    public static List<Character> charList(String x){
    	
    	List<Character> result= new ArrayList<Character>();
    	
    	for (char c : x.toCharArray()) {
      	  result.add(c);
      	}
    	return result; 
    }
    
    
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	
    	//String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
    	//List<Character> validChars = charList(valid);
	
    	Set<String> twitterHandles = new HashSet<String>();
    	//List<String> atContainingWords= new ArrayList<String>();
    	
    	for (Tweet tweet: tweets){
    		String userTweet= tweet.getText().toLowerCase();
    		String[] splitTweets= userTweet.split(" ");
    		for (String word: splitTweets){
    			if (word.startsWith("@") && word.length()!=1){
    				twitterHandles.add(word.substring(1));	
    			}
    		}	
    	}
    	
    /**	for (String word: atContainingWords){
    			int splitMarker= word.lastIndexOf("@");
    			String prefix= word.substring(0, splitMarker);
    			String suffix= word.substring(splitMarker, word.length());
    			int endMarker = suffix.length();
    			
        		for (char a: charList(suffix)){
        			if (validChars.contains(a)){
     						continue;
     				} else{
   					
     					endMarker= suffix.indexOf(a);
    					break;
        			}
        		}
    			
        		if (endMarker==0){
    					continue;
    				} else{
    					String trash= suffix.substring(endMarker);
    					if (!validChars.containsAll(charList(trash)) && !validChars.containsAll(charList(prefix))){
    						twitterHandles.add(word.substring(splitMarker,endMarker));
    					}
    					else{
    						continue;
    						}
    				}
    		}**/
    	
    	return twitterHandles;
    }
}



