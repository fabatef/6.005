/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExtractTest {

    /*
     * 
     * Testing Strategy for getTimeSpan
     * 
     * tweets.size: 0 , 1 , >1
     * When the list contains two tweets posted simultaneously
     * 
     * Testing Strategy for getsersMentioned
     * 
     * tweets.size: 0, 1, >1
     * result.size: 0, 1, >1
     * When a tweet contains invalid twitter mentions
     * When the same user is mentioned in all the tweets, 
     * but with different capitalization schemes
     * When all the tweets contain multiple mentions
     * 
     */
    
    private static Instant d1;
    private static Instant d2;
    private static Instant d3;
    private static Instant d4;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
    private static Tweet tweet3;
    private static Tweet tweet4;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        d1 = Instant.parse("2015-02-18T10:00:00Z");
        d2 = Instant.parse("2015-02-18T11:00:00Z");
        d3 = Instant.parse("2015-02-18T10:00:00Z");
        d4 = Instant.parse("2015-02-18T15:00:00Z");
        
        tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about @riVEst so much, @j_bieber , @_T-I ?", d1);
        tweet2 = new Tweet(2, "bbitdiddle", "@RiveST talk in 30 minutes, @alyssa #hype", d2);
        tweet3 = new Tweet(3, "faaya", " bbitdiddle@mit.edu, who is @rivest @ bbitdidle ?", d3);
        tweet4 = new Tweet(4, "drake", "yolo", d4);
    }
    
    //helper function
    public static Set<String> convertToLowercase(Set<String> mentionedUsers){
    	
    	Set<String> result= new HashSet<String>(Arrays.asList()); 
    	
        for (String user: mentionedUsers){
        	result.add(user.toLowerCase());
        }  
        
       return result;  	
    }
    
    // Test cases for getTimeSpan
    
    @Test //Partition 1: tweets.size=1
    public void testGetTimespanSingleTweet() {
        
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d1, timespan.getEnd());
    }
    
    @Test  // Partition 2: tweets.size()=0
    public void testGetTimespanEmptyList(){ 
    	
    	Timespan timespan = Extract.getTimespan(Arrays.asList());
    	
    	assertEquals(0,timespan.getStart().compareTo(timespan.getEnd()));
    }
    
    @Test // Partition 3: When the list contains two tweets posted simultaneously 
    public void testGetTimespanSimultaneous() {
    	
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1,tweet3));
    	
    	assertEquals(d1, timespan.getStart());
    	assertEquals(d3, timespan.getEnd());
    	
    }
    
    @Test // Partition 4: tweets.size >1
    public void testGetTimespanMultipleTweets() {
    	
    	Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1,tweet2,tweet3));
    	
    	assertEquals(d1, timespan.getStart());
    	assertEquals(d2, timespan.getEnd());
    }
    
    // Test cases for getMentionedUsers
    
    @Test // Partition 1: result.size= 0, tweet.size= 1
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet4));
        
        assertTrue(mentionedUsers.isEmpty());
    }
    
    @Test // Partition 2: tweet.size=0
    public void testGetMentionedUsersEmptyList() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList());
        
        assertTrue(mentionedUsers.isEmpty());
    }
    
    @Test // Partition 3: When the same user is mentioned in all the tweets, but with different capitalization schemes
    public void testGetMentionedUsersSameMention() {
        Set<String> mentionedUsers = convertToLowercase(Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2)));
       
        Set<String> result = new HashSet<String>(Arrays.asList("rivest", "alyssa", "j_bieber", "_t-i"));
        
        assertEquals(result, mentionedUsers);
    }
    @Test // Partition 4: When the tweets contain invalid twitter handles
    public void testGetMentionedUsersInvalidMention() {
        Set<String> mentionedUsers = convertToLowercase(Extract.getMentionedUsers(Arrays.asList(tweet3, tweet2)));
        
    
        Set<String> result = new HashSet<String>(Arrays.asList("rivest", "alyssa"));
        
        assertEquals(result, mentionedUsers);
    }
    
    @Test // Partition 5: tweet.size>1 and multiple mentions in every tweet
    public void testGetMentionedUsersMultipleMention() {
        Set<String> mentionedUsers = convertToLowercase(Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2)));
        
    
        Set<String> result = new HashSet<String>(Arrays.asList("rivest", "alyssa", "j_bieber", "_t-i"));
        
        assertEquals(result, mentionedUsers);
    }
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
