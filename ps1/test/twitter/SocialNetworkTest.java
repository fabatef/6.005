/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class SocialNetworkTest {

    /*
     * Testing strategy: for guessFollows
     *  
     *  tweets.size : 0, 1, >1
     *  result.size: 0, 1, >1
     *  tweet.getAuthor doesn't @mention anyone
     *  tweet.getAuthor @mentions multiple users
     *  tweet.getAuthor @mentions himself
     *  
     *  Testing strategy: for influencers
     *  
     *  followsGraph.size: 0, 1, >1
     *  result.size: 0 ,1, >1
     *  when users have the same number of followers
     *  
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
        tweet3 = new Tweet(3, "faaya", " bbitdiddle@mit.edu, who is @rivest ?", d3);
        tweet4 = new Tweet(4, "drake", "yolo. @drake over here hyped for the @rIveST talk", d4);
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
        
        for (String user: followsGraph.get(author4)){
        	assertTrue(user.equalsIgnoreCase("rivest"));
        }
        
        assertFalse(followsGraph.isEmpty());
        assertEquals(1, followsGraph.size()); 
        
    }
    @Test // Partition 3: When tweets.size > 1, result.size> 1 and the author @mentions multiple users
    
    public void testGuessFollowsGraphUserMentionsMultiple() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet1, tweet2));
       
        String author4 = tweet4.getAuthor(); 
        Set<String> result4= new HashSet<String>();
        result4.addAll(Arrays.asList(new String[] {"rivest"}));
        
        String author2 = tweet2.getAuthor(); 
        Set<String> result2= new HashSet<String>();
        result2.addAll(Arrays.asList(new String[] {"rivest", "alyssa"}));
        
        String author1 = tweet1.getAuthor(); 
        Set<String> result1= new HashSet<String>();
        result1.addAll(Arrays.asList(new String[] {"rivest", "j_bieber", "_t-i"}));
        
        assertTrue(followsGraph.get(author1).containsAll(result1));
        assertTrue(followsGraph.get(author2).containsAll(result2));
        assertTrue(followsGraph.get(author4).containsAll(result4));
        assertFalse(followsGraph.isEmpty());
        assertEquals(3, followsGraph.size()); 
        
    }
   
    

    @Test //Partition 1: When followsGraph.size = 0 and result.size= 0
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue(influencers.isEmpty());
        
    }
    
    @Test //Partition 2: When followsGraph.size=1 and result.size> 1 
    public void testInfluencersSingle() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
       
        Set<String> alyssaFollows= new HashSet<String>();
        alyssaFollows.addAll(Arrays.asList(new String[] {"rivest", "j_bieber", "_t-i"}));
        followsGraph.put("alyssa", alyssaFollows);
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        
        assertFalse(influencers.isEmpty());
        assertTrue(influencers.get(influencers.size()-1).equalsIgnoreCase("alyssa"));
        assertEquals(4, influencers.size());
    }
    
    @Test //Partition 3: When followsGraph.size>1 and result.size> 1 and some users have the same number of followers
    public void testInfluencersMultiple() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
       
        Set<String> alyssaFollows= new HashSet<String>();
        alyssaFollows.addAll(Arrays.asList(new String[] {"rivest", "j_bieber","_t-i"}));
        followsGraph.put("alyssa", alyssaFollows);
        
        Set<String> drakeFollows= new HashSet<String>();
        drakeFollows.addAll(Arrays.asList(new String[] {"rivest"}));
        followsGraph.put("drake", drakeFollows);
        
        
        Set<String> benFollows= new HashSet<String>();
        benFollows.addAll(Arrays.asList(new String[] {"rivest", "alyssa"}));
        followsGraph.put("ben", benFollows);
        
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
       
        
        assertFalse(influencers.isEmpty());
        assertTrue(influencers.get(0).equalsIgnoreCase("rivest"));
        assertTrue(influencers.get(1).equalsIgnoreCase("alyssa")); 
        assertEquals(6, influencers.size());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}
