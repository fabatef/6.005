/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class FilterTest {

    /*
     *Testing strategy for writtenBy
     *
     *tweet.size: 0 , 1, >1
     *result.size: 0 , 1, >1
     *
     *Testing strategy for inTimeSpan
     *
     *tweet.size: 0, 1 , >1 
     *result.size: 0, 1 , >1 
     *when tweet.getTimeStamp is equal to timespan.getStart 
     *when tweet.getTimeStamp is equal to timespan.getEnd
     *when timespan's start==end
     *
     *Testing strategy for containing
     *
     *tweet.size: 0 , 1, >1
     *words.size: 0 , 1, >1
     *result.size: 0 , 1, >1
     *When a word is in the list of tweets, but with different capitalization schemes
     *repeated search words
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
        tweet2 = new Tweet(2, "bbitdiddle", "@RiveST the talk is in 30 minutes, @alyssa #hype", d2);
        tweet3 = new Tweet(3, "drake", " bbitdiddle@mit.edu, who is @rivest ?", d3);
        tweet4 = new Tweet(4, "drake", "yolo. HYPED FOR THE TALK", d4);
    }
       
    
    //Test cases for writtenBy
    
    @Test // Partition 1: tweet.size= 1, result.size= 1 (When one tweet is writtenby user)
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1), "alyssa");
        
        assertFalse(writtenBy.isEmpty());
        assertEquals(1, writtenBy.size());
        assertTrue(writtenBy.contains(tweet1));
    }
    @Test // Partition 2: tweet.size>1, result.size= 0 (When none of the tweets are writtenby user)
    public void testWrittenByNoTweetsFromUser() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "drake");
        
        assertTrue(writtenBy.isEmpty());
        assertEquals(0, writtenBy.size());
    }
    @Test // Partition 3: result.size>1 (When multiple tweets are written by the user)
    public void testWrittenByMultipleTweetsFromUser() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3, tweet4), "drake");
        
        assertFalse(writtenBy.isEmpty());
        assertEquals(2, writtenBy.size()); 
        assertTrue(writtenBy.get(0)==tweet3 && writtenBy.get(1)==tweet4); //checking the order of appearance
    }
    @Test // Partition 4: tweet.size=0 
    public void testWrittenByEmptyTweetList() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(), "drake");
        
        assertTrue(writtenBy.isEmpty());
    }  
    
    
    //Test cases for inTimeSpan
    
    @Test// Partition 1: (tweet.size> 1 , result.size > 1) When all the tweets are in the given timespan
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testDateStart = Instant.parse("2015-02-18T09:00:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertTrue(inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals(2, inTimespan.size());  //checking if it only has two elements
    }
    @Test // Partition 2: (result.size= 0 )When none of the tweets are in the given time span
    public void testInTimespanNoResult() {
        Instant testDateStart = Instant.parse("2015-02-18T16:00:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T23:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        
        assertTrue(inTimespan.isEmpty());
    }
    
    @Test // Partition 3: When some of the tweets are in the given time span
    public void testInTimespanSomeTweets() {
        Instant testDateStart = Instant.parse("2015-02-18T10:59:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T15:01:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertEquals(2, inTimespan.size());
        assertTrue(inTimespan.containsAll(Arrays.asList(tweet2, tweet4)));
    }
    
    @Test // Partition 4: When some tweets are made exactly at the timespan start time.
    public void testInTimespanAtStart() {
        Instant testDateStart = Instant.parse("2015-02-18T10:00:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T10:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertEquals(2, inTimespan.size());
        assertTrue(inTimespan.containsAll(Arrays.asList(tweet1,tweet3)));
    }
        
    
    @Test // Partition 5: When some tweets are made exactly at the timespan end time.
    public void testInTimespanAtEnd() {
        Instant testDateStart = Instant.parse("2015-02-18T15:00:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T15:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3, tweet4), new Timespan(testDateStart, testDateEnd));
        
        assertFalse(inTimespan.isEmpty());
        assertTrue(inTimespan.contains(tweet4));
        assertEquals(1, inTimespan.size());
    }
    
    
    @Test// Partition 6: tweet.size= 0 , result.size= 0 
    public void testInTimespanEmptyTweetList() {
        Instant testDateStart = Instant.parse("2015-02-18T09:00:00Z");
        Instant testDateEnd = Instant.parse("2015-02-18T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(), new Timespan(testDateStart, testDateEnd));
        
        assertTrue(inTimespan.isEmpty());
    }
    
    @Test// added Partition 7: timespan's start==end
    public void testInTimespanTimespanStartEqualEnd() {
        Instant testDateStart = Instant.parse("2015-02-18T09:00:00Z");
        Instant testDateEnd = testDateStart;
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testDateStart, testDateEnd));
        
        assertTrue(inTimespan.isEmpty());
       ;
    }
    //Test cases for containing
    
    
    @Test //Partion 1:(result.size >1 , word.size> 1, tweets.size>1) When all the tweets contain the words in the list.
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("talk", "is"));
        
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1, tweet2)));
        
    }
    @Test //Partition 2: (result.size= 0) When none of the words are contained in the list of tweets
    public void testContainingNone() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("yolo", "hyped"));
        
        assertTrue(containing.isEmpty());
    }
    
    @Test //Partition 3: When a word is in the list of tweets, but with different capitalization schemes
    public void testContainingCaseCheck() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4), Arrays.asList("tALk", "Is"));
        
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1, tweet2)));
    }
    
    @Test //added partition: repeated search words
    public void testContainingRepeatedSearchWords() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4), Arrays.asList("tALk", "Is", "is","talk"));
        assertFalse(containing.isEmpty());
        assertTrue(containing.containsAll(Arrays.asList(tweet1, tweet2, tweet3, tweet4)));
        assertEquals(4, containing.size());
    }
    
    @Test //Partition 4: tweet.size= 0
    public void testContainingEmptyTweetList() {
        List<Tweet> containing = Filter.containing(Arrays.asList(), Arrays.asList("tALk", "Is"));
        
        assertTrue(containing.isEmpty());

    }
    @Test //Partition 5: word.size= 0 
    public void testContainingEmptyWord() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3, tweet4), Arrays.asList());
        
        assertTrue(containing.isEmpty());
    }
    

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}
