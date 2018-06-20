package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class MySocialNetworkTest {

    /*
     * Testing strategy: for guessFollows
     *  
     *  tweets.size : 0, 1, >1
     *  result.size: 0, 1, >1
     *  tweet.getAuthor doesn't @mention anyone
     *  tweet.getAuthor @mentions multiple users and shares # with multiple people
     *  tweet.getAuthor doesn't share a hashtag with anyone
     *  tweet.getAuthor @mentions himself 
     *  
     *  
     */
	
    private static Instant d1;
    private static Instant d2;
    private static Instant d3;
    private static Instant d4;
    private static Instant d5;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
    private static Tweet tweet3;
    private static Tweet tweet4;
    private static Tweet tweet5;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        d1 = Instant.parse("2015-02-18T10:00:00Z");
        d2 = Instant.parse("2015-02-18T11:00:00Z");
        d3 = Instant.parse("2015-02-18T10:00:00Z");
        d4 = Instant.parse("2015-02-18T15:00:00Z");
        d5 = Instant.parse("2015-02-18T16:00:00Z");
        
        tweet1 = new Tweet(1, "alyssa", "is it #yolo reasonable to talk about @riVEst so much, @j_bieber , @_T-I ?", d1);
        tweet2 = new Tweet(2, "bbitdiddle", " #yolo @RiveST talk in 30 minutes, @alyssa #hype", d2);
        tweet3 = new Tweet(3, "faaya", " bbitdiddle@mit.edu, who is @rivest #xd ?", d3);
        tweet4 = new Tweet(4, "drake", " #yolo . @drake over here hyped for the @rIveST talk #hype", d4);
        tweet5 = new Tweet(5, "drake", "@faaya #xMen", d4);
    }
    
    //helper function
    public static Set<String> convertToLowercase(Set<String> mentionedUsers){
    	
    	Set<String> result= new HashSet<String>(Arrays.asList()); 
    	
        for (String user: mentionedUsers){
        	result.add(user.toLowerCase());
        }  
        
       return result;  	
    }
    
        
    @Test // Partition 1: When tweets.size= 0 and result.size = 0 
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue(followsGraph.isEmpty());
    }
    
    @Test // Partition 2: When tweets.size=1, result.size= 1 and the author @mentions himself
    
    public void testGuessFollowsGraphUserMentionsHimself() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4));
        
        String author4 = tweet4.getAuthor(); 
        Set<String> result4= new HashSet<String>();
        result4.addAll(Arrays.asList(new String[] {"rivest"}));
        
        assertTrue(convertToLowercase(followsGraph.get(author4)).containsAll(result4));
        assertFalse(followsGraph.isEmpty());
        assertEquals(1, followsGraph.size()); 
        
    }
    @Test // Partition 3: When an author doesn't share a hashtag with people in the list 
          //  and when multiple people share hashtags with others in the list
    
    public void testGuessFollowsGraphUserMentionsMultiple() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet1, tweet2, tweet3));
       
        String author4 = tweet4.getAuthor(); 
        Set<String> result4= new HashSet<String>();
        result4.addAll(Arrays.asList(new String[] {"rivest", "alyssa", "bbitdiddle"}));
        
        String author2 = tweet2.getAuthor(); 
        Set<String> result2= new HashSet<String>();
        result2.addAll(Arrays.asList(new String[] {"rivest", "alyssa", "drake"}));
        
        String author3 = tweet3.getAuthor(); 
        Set<String> result3= new HashSet<String>();
        result3.addAll(Arrays.asList(new String[] {"rivest"}));
        
        String author1 = tweet1.getAuthor(); 
        Set<String> result1= new HashSet<String>();
        result1.addAll(Arrays.asList(new String[] {"rivest", "j_bieber", "_t-i", "drake", "bbitdiddle"}));
        
        
        assertTrue(convertToLowercase(followsGraph.get(author1)).containsAll(result1));
        assertTrue(convertToLowercase(followsGraph.get(author3)).containsAll(result3));
        assertTrue(convertToLowercase(followsGraph.get(author2)).containsAll(result2));
        assertTrue(convertToLowercase(followsGraph.get(author4)).containsAll(result4));
        assertFalse(followsGraph.isEmpty());
        assertEquals(4, followsGraph.size()); 
        
    }
    // added partition: when there are tweets by the same author
    public void testGuessFollowsGraphTweetsBySameAuthor() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet5, tweet2, tweet3));
       
        String author4 = tweet4.getAuthor(); 
        Set<String> result4= new HashSet<String>();
        result4.addAll(Arrays.asList(new String[] {"rivest", "alyssa", "bbitdiddle", "faaya"}));
        
        String author2 = tweet2.getAuthor(); 
        Set<String> result2= new HashSet<String>();
        result2.addAll(Arrays.asList(new String[] {"rivest", "alyssa", "drake"}));
        
        String author3 = tweet3.getAuthor(); 
        Set<String> result3= new HashSet<String>();
        result3.addAll(Arrays.asList(new String[] {"rivest"}));
        
         
        assertTrue(convertToLowercase(followsGraph.get(author3)).containsAll(result3));
        assertTrue(convertToLowercase(followsGraph.get(author2)).containsAll(result2));
        assertTrue(convertToLowercase(followsGraph.get(author4)).containsAll(result4));
        assertFalse(followsGraph.isEmpty());
        assertEquals(3, followsGraph.size()); 
        
    }
    
}
   