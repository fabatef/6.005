/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
	//   - graph with a single bridge word for the input string words
	//   - graph with no bridge word present for the input string words
	//   - graph with multiple bridge words with different weights 

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test 
    public void noBridges() throws IOException{
    	File test= new File("test/poet/linear-multiple-bridges.txt");
    	
    	GraphPoet x= new GraphPoet(test);   	
    	String out= x.poem("Test the quick system right now");
    	
	    	assertEquals("Test the quick system right now", out);
    }
    
    @Test //self loop on beat
    public void oneBridgeWithSelfLoop() throws IOException{
    	File test= new File("test/poet/linear-multiple-bridges.txt");
    	
    	GraphPoet x= new GraphPoet(test);   	
    	String out= x.poem("The system to beat is rr jj kk ");
    	
    	assertEquals("The quick system to beat beat is rr jj kk", out);
    }
    
    
    @Test
    public void multipleBridges() throws IOException{
    	File test= new File("test/poet/linear-multiple-bridges.txt");
    	
    	GraphPoet x= new GraphPoet(test);   	
    	String out= x.poem("To the system function we go");
    	
    	assertEquals("To beat the quick system function we go", out);
    }
    
}
    
   

