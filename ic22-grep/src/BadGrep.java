import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BadGrep {
	//for one thread: 1000 lines
	//for threads greater than one: fails with wrong answers
	//                            : NullPointerExceptions
	//                            : IndexOutOfBoundsExceptions
	//Thread safety violations: using an unsynchronized list to store inputs
	//                        : using an unsynchronized list to store the outputs
	//                        : number of matches is global
    
    private static final int NUMBER_OF_THREADS = 2;
    
    private static int numMatches = 0;
    
    public static void main(String[] args) throws InterruptedException {
        
        // substring to search for
        String substring = "use";
        
        // build a list of lines to search
        List<String> inputLines = Collections.synchronizedList(new ArrayList<String>());
        for (int ii = 0; ii < 1000; ++ii) {
            inputLines.add("don't use a list for this");
            inputLines.add("it's not threadsafe");
        }
        
        // list for accumulating matching lines
        List<String> outputMatches = Collections.synchronizedList(new ArrayList<>());
        
        Thread[] searchers = new Thread[NUMBER_OF_THREADS]; // use multiple consumers
        
        for (int ii = 0; ii < searchers.length; ii++) { // create consumers
            searchers[ii] = new Thread(new Runnable() {
                public void run() {
                    while (inputLines.size() > 0) {
                        int i = (int) (Math.random() * inputLines.size());
                        String line = inputLines.get(i); //race conditions
                        inputLines.remove(i); //race conditions
                        if (line.contains(substring)) {
                            outputMatches.add(line);
                            ++numMatches;
                        }
                    }
                }
            });
        }
        
        for (Thread consumer : searchers) { // start all the threads
            consumer.start();
        }
        
        for (Thread consumer : searchers) { // wait for them all to stop
            consumer.join();
        }
        
        System.out.println(numMatches + " lines matched");
    }
}
