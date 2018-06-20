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

/*
 * TODO: your testing strategies for these methods should go here
 */

public class ExtractTest {
    
    private static Instant d1;
    private static Instant d2;
    
    private static Tweet tweet1;
    private static Tweet tweet2;
    
    @BeforeClass
    public static void setUpBeforeClass() {
        d1 = Instant.parse("2015-02-18T10:00:00Z");
        d2 = Instant.parse("2015-02-18T11:00:00Z");
        
        tweet1 = new Tweet(0, "alyssa", "is it reasonable to talk about rivest so much?", d1);
        tweet2 = new Tweet(1, "bbitdiddle", "riv@est talk in 30 @minutes #hype", d2);
    }
    
    @Test
    public void testGetTimespanTwoTweets() {
        
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals(d1, timespan.getStart());
        assertEquals(d2, timespan.getEnd());
    }
    
    @Test
    public void testGetMentionedUsersOneMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet2));
        Set<String> users2= new HashSet<String>(Arrays.asList("minutes"));
        assertEquals(users2, mentionedUsers );
    }
    
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1));
        
        assertTrue(mentionedUsers.isEmpty());
    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}
