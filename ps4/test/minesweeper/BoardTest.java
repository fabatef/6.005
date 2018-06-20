/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


/**
 * tests the methods of Board on a deterministic Mine sweeper Board object
 */
public class BoardTest {	 
    
    //testing strategy for flag
	//    -flag an untouched square on the board
	//    -flag an illegal square entry on the board
	//    -flag a flagged square on the board
	//    -flag a dug square on the board
	//testing strategy for deflag
	//    -deflag a legal flagged square on the board
	//    -deflag an illegal square entry on the board
	//    -deflag a dug square on the board
	//    -deflag an untouched square on the board
	//testing strategy for dig
	//    -dig a legal untouched square on the board
	//    -dig a flagged square on the board
	//    -dig an illegal square entry on the board
	//    -dig a square with no neighbors having bombs.
	//    -dig a square with some neighbors having bomb.
	//testing strategy for look
	//    - look at a newly instantiated board
	//    - look at a board after a dig/deflag/flag
	
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    //testing the input text file
    
    //partition 1: incorrectly formatted file
    @Test(expected=RuntimeException.class)
    public void testIncorrectlyFormattedFile() {
    	File test= new File("test/minesweeper/incorrectformat.txt");
    	new Board(test); 	
    }
    
    //partition 2: nonexistent file
    @Test(expected=RuntimeException.class) 
    public void testNonexistentFile() {
    	File test= new File("test/minesweeper/meh.txt");
    	new Board(test); 	
    }
    
    //testing flag
    
    @Test
    //partition 1: flag an untouched square on the board
    public void testFlagUntouchedSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "F - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.flag(0, 0));
    }
    
    @Test
    //partition 2: flag an illegal square entry on the board
    public void testFlagIllegalSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.flag(4, 3));
    }
    
    @Test
    //partition 3: flag a flagged square on the board
    public void testFlagFlaggedSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - F -" + System.getProperty("line.separator");
    	testBoard.flag(2, 2);
    	
    	assertEquals(expected, testBoard.flag(2, 2));
    }
    
    @Test
    //partition 4: flag a dug square on the board
    public void testFlagDugSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - 1 -" + System.getProperty("line.separator");
    	testBoard.dig(2, 2);
    	
    	assertEquals(expected, testBoard.flag(2, 2));
    }
    
	//testing deflag
    
    @Test
    //partition 1: deflag a flagged square on the board
    public void testDeflagFlaggedSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	testBoard.flag(0, 0);
    	
    	assertEquals(expected, testBoard.deflag(0, 0)); 
    }
    
    @Test
    //partition 2: deflag an illegal square entry on the board
    public void testDeflagIllegalSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.deflag(-1, -1));
    }
    
    @Test
    //partition 3: deflag an untouched square on the board
    public void testDelagUntouchedSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.deflag(2, 2));
    }
    
    @Test
    //partition 4: deflag a dug square on the board
    public void testDeflagDugSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - 1 -" + System.getProperty("line.separator");
    	testBoard.dig(2, 2);
    	
    	assertEquals(expected, testBoard.deflag(2, 2));
    }
    
	//testing dig
    
    @Test
    //partition 1: dig an untouched square(containing a bomb) with none of its neighbors having bombs. 
    public void testDigUntouchedBSquareNeighborsNoBombs(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "       " + System.getProperty("line.separator")+
    			         "       " + System.getProperty("line.separator")+
    			         "       " + System.getProperty("line.separator");
    	String expected1= testBoard.boom();
    	
    	assertEquals(expected1, testBoard.dig(2, 1));
    	assertEquals(expected, testBoard.look());
    }
    
    @Test
    //partition 2: dig an untouched square(containing a bomb) with some of its neighbors having bombs.
    public void testDigUntouchedBSquareNeighborsBomb(){  
    	File test= new File("test/minesweeper/testboard1.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - - -" + System.getProperty("line.separator")+
    			         "- - 1 - -" + System.getProperty("line.separator")+
    			         "- - - - -" + System.getProperty("line.separator");
    	String expected1= testBoard.boom();
    	
    	assertEquals(expected1, testBoard.dig(2, 1));
    	assertEquals(expected, testBoard.look());
    }
    
    @Test //??
    //partition 3: dig an untouched square(has no bomb) with none of its neighbors having bombs.
    public void testDigUntouchedNBSquareNeighborsNoBombs(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "  1 - -" + System.getProperty("line.separator")+
    			         "  1 - -" + System.getProperty("line.separator")+
    			         "  1 - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.dig(0, 1));
    }
    
    @Test
    //partition 4: dig an untouched square(has no bomb) with some of its neighbors having bombs.
    public void testDigUntouchedNBSquareNeighborsBombs(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - 1 -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.dig(2, 2));
    }
    
    @Test 
    //partition 5: dig a flagged square on the board
    public void testDigFlagged(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "F - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	testBoard.flag(0, 0);
    	
    	assertEquals(expected, testBoard.dig(0, 0));
    }
    
    @Test
    //partition 6: dig an illegal square entry on the board
    public void testDigIllegalSquare(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.dig(-4, 2));
    }
    
	//testing strategy for look
	//    - look at a newly instantiated board
	//    - look at a board after a dig/deflag/flag
    
    @Test
    //partition 1: look at a newly instantiated board
    public void testLookNewBoard(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expected= "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator")+
    			         "- - - -" + System.getProperty("line.separator");
    	
    	assertEquals(expected, testBoard.look());
    }
    
    @Test //???
    //partition 6: look at a board after a dig/deflag/flag 
    public void testLookAfterDigDeflagFlag(){  
    	File test= new File("test/minesweeper/testboard.txt");
    	Board testBoard= new Board(test);
    	String expectedDigNoBomb= "- - - -" + System.getProperty("line.separator")+
		                          "- - - -" + System.getProperty("line.separator")+
		                          "- - 1 -" + System.getProperty("line.separator");
      	String expectedDigBomb="       " + System.getProperty("line.separator")+
		                       "       " + System.getProperty("line.separator")+
		                       "       " + System.getProperty("line.separator");
     	String expectedFlag= "- - - -" + System.getProperty("line.separator")+
                             "- F - -" + System.getProperty("line.separator")+
                             "- - - -" + System.getProperty("line.separator");
     	String expectedDeFlag= "- - - -" + System.getProperty("line.separator")+
                               "- - - -" + System.getProperty("line.separator")+
                               "- - - -" + System.getProperty("line.separator");
    	
    	testBoard.flag(1, 1);
    	assertEquals(expectedFlag, testBoard.look());
    	testBoard.deflag(1, 1);
    	assertEquals(expectedDeFlag, testBoard.look());
    	testBoard.dig(2, 2);
    	assertEquals(expectedDigNoBomb, testBoard.look());
    	testBoard.dig(2, 1);
    	assertEquals(expectedDigBomb, testBoard.look());    	
    }
    
    
    
    
    
    
}
