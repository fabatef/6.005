/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

/**
 * represents a square on a MineSweeper board
 */


public class Square{
	private String state;
	private boolean bomb;

	
	//Abstraction function
	//   -represents the state of a square in a MineSweeper board
	//Rep Invariant
	//   -Square must be in one of its three states at any given time
	//Safety from rep exposure
	//   -All fields are private. 
	//   -The return value from all of its methods is a boolean with no variable assignment
	//Thread Safety Argument
	//    - All the methods in the class are synchronized with an instance of square as a lock
	
	/**
	 * instantiates an untouched square on the board
	 * 
	 * @param hasBomb true if the square contains a bomb
	 */
	public Square(boolean hasBomb){
		this.state= "untouched";
		this.bomb= hasBomb;
		checkRep();
	}
	
	private synchronized void checkRep(){
		boolean oneOf= false;
		String [] states= new String[] {"dug", "flagged", "untouched"};
		for(String s: states){
			oneOf= state.equals(s) ? true:false;
			if(oneOf){
				break;
			}
		}
		assert(oneOf);
	}
	
	/**
	 * changes the state of the square to dug
	 */
	
	public synchronized void dig(){
		this.state= "dug";
	}
	
	/**
	 * changes the state of the square to flagged
	 */
	public synchronized void flag(){
		this.state= "flagged";
	}
	
	/**
	 * changes the state of the square back to untouched
	 */
	public synchronized void deflag(){
		this.state= "untouched";
	}
	
	/**
	 * removes a bomb from the square
	 */
	public synchronized void detonate(){ 
		this.bomb=false;
	}
	
	/**
	 * @return true if the square is dug. False otherwise
	 */
	public synchronized boolean isDug(){
		checkRep();
		return state.equals("dug");
	}
	
	/**
	 * @return true if the square is flagged. False otherwise
	 */
	public synchronized boolean isFlagged(){
		checkRep();
		return state.equals("flagged");
	}
	
	/**
	 * @return true if the square is untouched. False otherwise
	 */	
	public synchronized boolean isUntouched(){
		checkRep();
		return state.equals("untouched");
	}
	
	/**
	 * @return true if the square contains a bomb. False otherwise
	 */
	public synchronized boolean hasBomb(){
		checkRep();
		return bomb;
	}

	
}