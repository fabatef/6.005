/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * represents a mutable threadsafe MineSweeper Board
 *
 */
public class Board{
	
	//Abstraction function
	//  - board represents a MineSweeper board with dimensions(rep fields: row and column) as specified 
	//    by the client through a text file or through the Board constructor arguments xSize and ySize.
	//Rep Invariant
	//  - the number of rows and columns a board has never changes
	//Rep Exposure
	//  - All fields are private and final.
	//  - All returned values do not carry a reference to the nested list rep of the board.
    // Thread Safety Argument
	//  -All the lists are synchronized. All the methods are synchronized with an instance of a board 
	//    as a lock
	//  -rows and columns are final and immutable
	
	private final List<List<Square>> board;
	private final int rows;
	private final int columns;
	
	
	/**
	 * Returns a MineSweeper board with dimensions and bombs placed as specified in the text file.	 
	 * @param filename the text file containing the input board
	 */
	public Board (File filename){
		
		List<List<String>> collect= decipher(filename);
	
		this.rows= Integer.valueOf(collect.get(0).get(1));
		this.columns=Integer.valueOf(collect.get(0).get(0));
		this.board= new ArrayList<List<Square>>();
		
		try{
			assert(collect.get(0).size()==2);
			assert(collect.size()==rows+1);
			collect.remove(0);
			for(int y=0; y< rows; y++){
				assert(collect.get(y).size()==columns);
				}
			}
		catch(AssertionError e){
			throw new RuntimeException("The file is incorrectly formatted");
		}
		
		
		for(int y=0; y< rows; y++){
			List<Square> row= new ArrayList<Square>();
			for (int x=0; x< columns; x++){
			
				if(Integer.valueOf(collect.get(y).get(x))==1) {
				    row.add(new Square(true));
				}else{
					row.add(new Square(false));
				}		
			}
			this.board.add(row);
		}
		
	}
	
	/**
	 * Instantiates a MineSweeper board where the bombs in each square are generated randomly with probability= 0.25. 	
	 * 
	 * @param xSize : number of columns in the board
	 * @param ySize :  number of rows in the board
	 */
	
	public  Board (int xSize, int ySize){
		this.rows= ySize;
		this.columns=xSize ;
		
		this.board= new ArrayList<List<Square>>();
		
		for(int y=0; y< rows; y++){
			List<Square> row= new ArrayList<Square>();
			for (int x=0; x< columns; x++){
				double probability= Math.random();
				if( probability <= 0.25) {
				    row.add(new Square(true));
				}else{
					row.add(new Square(false));
				}		
			}
			this.board.add(row);
		}
	}
	/**
	 * returns a board rep from the input file 
	 * 
	 * @param filename: the input file
	 * @return Each line of the input file as a List<List<String>>
	 */
	
	private synchronized List<List<String>> decipher(File filename){
		String line;
		BufferedReader reader;
		List<List<String>> collect= new ArrayList<List<String>>();
		try {
			reader = new BufferedReader (new FileReader(filename));
			while ((line= reader.readLine())!= null){
				collect.add(Arrays.asList(line.split(" ")));		
			}
			reader.close();
		} catch (IOException e ) {
			//e.printStackTrace();
			throw new RuntimeException("The file was not found");	
		}
		return collect;
	}
	
	private void checkRep(){
		assert (rows== board.size() && columns==board.get(0).size());
	}
	
	/**
	 * 
	 * @param p location of a square on the board
	 * @return the number of bombs around a square at location Point p(x,y)
	 */

	private synchronized int countBombs(Point p){
		int x= p.x();
		int y= p.y();
		int count=0;
		Point[] neighbors= new Point[] {new Point(x, y-1), new Point(x, y+1), new Point(x-1, y),
				new Point(x+1, y),new Point(x+1, y-1),new Point(x+1, y+1),new Point(x-1, y+1), new Point(x-1, y-1)};
		for(Point point: neighbors){
			try{
				if(board.get(point.y()).get(point.x()).hasBomb()) {
					count+=1;
				}
				}catch(Exception e){
					continue;
				}	
			}	
		checkRep();
		return count;
	}
	
	/**
	 * maps the neighbors of a square at (x,y) on the board to their respective location.
	 * 
	 * @param x : the column in which the square is found
	 * @param y : the row in which the square is found
	 * @return a map where the keys are each of the square's neighbors location and values are the neighbor squares
	 *         themselves 
	 */
	
	private synchronized Map<Point, Square> getNeighbors(int x, int y){
		Map<Point,Square> neighbors= new HashMap<Point, Square>();
		Point[] points= new Point[] {new Point(x, y-1), new Point(x, y+1), new Point(x-1, y),
		new Point(x+1, y),new Point(x+1, y-1),new Point(x+1, y+1),new Point(x-1, y+1), new Point(x-1, y-1)};
		for(Point point: points){
			try{
				neighbors.put(point, board.get(point.y()).get(point.x()));
			}catch(Exception e){
				continue;
			}	
		}	
		checkRep();
		return neighbors;
	}
	
	public synchronized String look(){
		checkRep();
		return this.toString();
	}
	
	/**
	 * recursively digs around a square with none of its neighbors having bombs
	 * @param p the location (x,y) of the square to be recursively dug
	 */
	private synchronized void recurseDig(Point p){
		Map<Point,Square> neighbors= getNeighbors(p.x(), p.y());
		if (countBombs(p)== 0){
			for(Entry<Point,Square> square: neighbors.entrySet()){
				if(square.getValue().isUntouched()){
					square.getValue().dig();
					recurseDig(square.getKey());	
				}
				else{
					//do nothing
				}
			}	
		}
	}
	
	/**
	 * Digs a square at location (x,y) in the board.
	 * 
	 * @param x the column in which the square is found
	 * @param y the row in which the square is found
	 * @return the current state of the board after the dig if the square doesn't have a bomb
	 *         or a 'BOOM!' if the square has a bomb.
	 */
	
	public synchronized String dig(int x, int y){
		
		if(x<0 || y<0 || x>= columns || y>= rows||!board.get(y).get(x).isUntouched()){
			return this.toString();
		}else{
			Square target= board.get(y).get(x);
			if(target.isUntouched()){
				target.dig();
			}
			if(target.hasBomb()){
				target.detonate();
				recurseDig(new Point(x,y));
				checkRep();
				return boom();
			}
			recurseDig(new Point(x,y));			
		}
		
		checkRep();
		return this.toString();
	}
		
	
	/**
	 * flags a square at location (x,y) if the square is untouched. If not, no changes will be made to the board.
	 * 
	 * @param x : the column in which the square is found
	 * @param y : the row in which the square is found
	 * @return a board message updated to reflect the changes(if any) in the state of the square at (x,y).
	 */
		
	public synchronized String flag(int x, int y){
		if(x>=0 && y>=0 && x<columns && y< rows && board.get(y).get(x).isUntouched()){
			Square target= board.get(y).get(x);
			target.flag();
		}
		checkRep();
		return this.toString();
	}
	
	/**
	 * deflags a square at location (x,y) if the square is flagged. If not, no changes will be made to the board.
	 * 
	 * @param x : the column in which the square is found
	 * @param y : the row in which the square is found
	 * @return a board message updated to reflect the changes(if any) in the state of the square at (x,y).
	 */
	
	public synchronized String deflag(int x, int y){
		if(x>=0 && y>=0 && x<columns && y< rows && board.get(y).get(x).isFlagged()){
			Square target= board.get(y).get(x);
			target.deflag();
		}
		return this.toString();
	}

/*	private synchronized String debug(){
		String result= "";
		for (int y=0; y< rows; y++){
			String row= "";
			for (int x=0; x < columns; x++){
				Square target= board.get(y).get(x);
				if (target.hasBomb()){
					row+= "t"+ Integer.toString(countBombs(new Point(x, y)))+ " ";
				}else{
					row+= "f"+ Integer.toString(countBombs(new Point(x, y)))+ " ";
				}
			}
			
			result+= row.substring(0, row.length()-1)+ System.getProperty("line.separator");
		}
		return result;
	} */
	
	/**
	 * returns a String representation of the current state of the board.
	 *  
	 * "-": stands for untouched untouched squares.
	 * "F": stands for flagged squares.
	 * " " (space): stands for dug squares and 0 neighbors that have a bomb.
	 * integer COUNT in range [1-8]: stands for dug squares and COUNT neighbors that have a bomb.
	 */
	@Override
	public synchronized String toString(){
		String result= "";
		for (int y=0; y< rows; y++){
			String row= "";
			for (int x=0; x < columns; x++){
				Square target= board.get(y).get(x);
				Point square= new Point(x,y);
				if(target.isUntouched()){
					row+= "- ";
				}else if(target.isFlagged()){
					row+="F ";
				}else{
					if (target.isDug() && countBombs(square)==0){
						row+= "  ";
					}else{
						row+= Integer.toString(countBombs(square))+ " ";
					}
				}
			}
			result+= row.substring(0, row.length()-1)+ System.getProperty("line.separator");
		}
		checkRep();
		return result;		
	}
	/**
	 * a message sent out when a square containing a bomb is dug
	 * 
	 * @return a String
	 */
	
	public synchronized String boom(){
		checkRep();
		return "BOOM!" + System.getProperty("line.separator");
	}

	/**
	 * A List containing the board's dimension
	 *  
	 * @return List[0]: number of columns in the board. List[1]: number of rows in the board
	 */
	public List<Integer> getDimension(){
		List<Integer> dimension= new ArrayList<Integer>();
		int sizeY= board.size();
		int sizeX= board.get(0).size();
		dimension.add(sizeX);
		dimension.add(sizeY);
		checkRep();
		return dimension;
		
	}
	/**
	 * a message sent out when a user asks for help or inputs an invalid command
	 * 
	 * @return a String
	 */
	
	public synchronized String help(){
		return "Go to 'http://web.mit.edu/6.005/www/fa15/psets/ps4/' to find out more about valid comands"
	            + System.getProperty("line.separator");
	}
    
    
}
