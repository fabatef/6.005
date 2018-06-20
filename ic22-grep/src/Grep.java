import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import minesweeper.Square;

/** Search web pages for lines containing a string. */
public class Grep {
    public static void main(String[] args) throws Exception {
        
        // substring to search for
        String substring = "specification";
        
        // URLs to search
        String[] urls = new String[] {
                "http://web.mit.edu/6.005/www/fa15/psets/ps0/",
                "http://web.mit.edu/6.005/www/fa15/psets/ps1/",
                "http://web.mit.edu/6.005/www/fa15/psets/ps2/",
                "http://web.mit.edu/6.005/www/fa15/psets/ps3/",
        };
        
        // list for accumulating matching lines
        List<Item> matches = Collections.synchronizedList(new ArrayList<>());
        
        // queue for sending lines from producers to consumers
        BlockingQueue<Item> queue = new LinkedBlockingQueue<Item>();
        
        Thread[] downloaders = new Thread[urls.length]; // one downloader per URL
        Thread[] searchers = new Thread[1]; // TODO use multiple searchers
        
        for (int ii = 0; ii < searchers.length; ii++) { // create & start searching threads
            Thread searcher = searchers[ii] = new Thread(new Searcher(queue, substring, matches));
            searcher.start();
        }
        
        for (int ii = 0; ii < urls.length; ii++) { // create & start downloading threads
            Thread downloader = downloaders[ii] = new Thread(new Downloader(urls[ii], queue));
            downloader.start();
        }
        
        for (Thread downloader : downloaders) { // wait for downloaders to stop
            downloader.join();
        }
        
        // stop searching threads somehow
        // ...
        // ...
        
        for (Thread searcher : searchers) { // wait for searchers to stop
            searcher.join();
        }
        
        for (Item match : matches) {
            System.out.println(match);
        }
        System.out.println(matches.size() + " lines matched");
    }
}

class Downloader implements Runnable {
    
	private final BlockingQueue<Item> queue;
	private final String href;
	
    Downloader(String href, BlockingQueue<Item> queue) {
        this.href= href;
        this.queue= queue;
    }

    public void run() {
        
        // open an input stream from the URL
        
		
        try {
        	URL url= new URL(href);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			int lineNum= 0;
			
			while(!queue.isEmpty()){
				String line= in.readLine();
				Item item= queue.put(new Text(href, lineNum,line));  //???
				lineNum++;
				}
		} catch (IOException|InterruptedException  ioe) {
			// TODO Auto-generated catch block
			//throw new(.....);
			ioe.printStackTrace();
		} 
    }
}

class Searcher implements Runnable {
    private final BlockingQueue<Item>queue;
    private final String substring;
    private final List<Item> outputMatches;
    
    
    
    public Searcher(BlockingQueue<Item> sQueue, String sstring,List<Item> matches ) {
    	this.queue= sQueue;
    	this.substring= sstring;
    	this.outputMatches= matches;
    }
        // TODO construct your Searcher here!
    public void run() {
		try {
			while(!queue.isEmpty()){
				Item item= queue.take();
				if(item.text().contains(substring)){
					outputMatches.add(item);
				}
            }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }
}


/** Represents single item of work. */
interface Item {
    /** @return the filename */
    public String filename();
    /** @return the line number */
    public int lineNumber();
    /** @return the text */
    public String text();
}

class Text implements Item {
    private final String filename;
    private final int lineNumber;
    private final String text;
    
    public Text(String filename, int lineNumber, String text) {
        this.filename = filename;
        this.lineNumber = lineNumber;
        this.text = text;
    }
    
    public String filename() {
        return filename;
    }
    
    public int lineNumber() {
        return lineNumber;
    }
    
    public String text() {
        return text;
    }
    
    @Override public String toString() {
        return filename + ":" + lineNumber + " " + text;
    }
}




private void updateBoard(){
	wipeCountBoard();
	for (int y=0; y<rows; y++){
		for (int x=0; x<columns; x++){
			Square target= board.get(y).get(x);
			if (x==0){
				if(y==0){ //top left corner square
					if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
					if(board.get(y+1).get(x+1).hasBomb()) target.updateCount(); //look diagonally down right
					if(board.get(y).get(x+1).hasBomb()) target.updateCount(); // look right
				}else if(y==rows-1){ //bottom left corner square
					if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
					if(board.get(y-1).get(x+1).hasBomb()) target.updateCount();//look diagonally up right
					if(board.get(y).get(x+1).hasBomb()) target.updateCount();// look right
				}else{//left edge squares
					if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
					if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
					if(board.get(y-1).get(x+1).hasBomb()) target.updateCount();//look diagonally up right
					if(board.get(y+1).get(x+1).hasBomb()) target.updateCount(); //look diagonally down right
					if(board.get(y).get(x+1).hasBomb()) target.updateCount();// look right	
				}
			}
			
			else if(y==0){
				if(x==columns-1){//top right corner square
					if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
					if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
					if(board.get(y+1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left down
					}
				else{//top edge squares
					if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
					if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
					if(board.get(y).get(x+1).hasBomb()) target.updateCount(); // look right
					if(board.get(y+1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left down	
					if(board.get(y+1).get(x+1).hasBomb()) target.updateCount(); //look diagonally right down
				}
			}
			
			else if(y==rows-1){
				if(x==columns-1){ //bottom right corner square
					if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
					if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
					if(board.get(y-1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left up	
				}
				
				else{ //bottom edge squares
					if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
					if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
					if(board.get(y).get(x+1).hasBomb()) target.updateCount(); // look right
					if(board.get(y-1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left up
					if(board.get(y-1).get(x+1).hasBomb()) target.updateCount();//look diagonally up right	
				}
			}
			
			else if(x==columns-1){
				if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
				if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
				if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
				if(board.get(y-1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left up
				if(board.get(y+1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left down						
			}
			
			else{
				if(board.get(y-1).get(x).hasBomb()) target.updateCount(); //look up
				if(board.get(y+1).get(x).hasBomb()) target.updateCount();  //look down
				if(board.get(y).get(x-1).hasBomb()) target.updateCount(); //look left
				if(board.get(y).get(x+1).hasBomb()) target.updateCount(); // look right
				if(board.get(y-1).get(x+1).hasBomb()) target.updateCount();//look diagonally up right
				if(board.get(y+1).get(x+1).hasBomb()) target.updateCount(); //look diagonally down right
				if(board.get(y-1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left up
				if(board.get(y+1).get(x-1).hasBomb()) target.updateCount(); //look diagonally left down	
				
			}
		}
	}	
} 
