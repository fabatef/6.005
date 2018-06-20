/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


/**
 * TODO: Description
 */
public class SquareTest {		
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    //testing dig
    public void testDig(){
    	Square test= new Square(true);
    	test.dig();
    	assertTrue(test.isDug());
    }
    
    @Test
    //testing flag
    public void testFlag(){
    	Square test= new Square(true);
    	test.flag();
    	assertTrue(test.isFlagged());	
    }
    
    @Test
    //testing deflag
    public void testDeFlag(){
    	Square test= new Square(true);
    	test.deflag();
    	assertTrue(test.isUntouched()); 	
    }
    
    @Test
    //testing hasBomb and detonate
    public void testHasBombandDetonate(){
    	Square test= new Square(true);    	
    	assertTrue(test.hasBomb());	
    	test.detonate();
    	assertFalse(test.hasBomb());
    }
    
    
}
    
    