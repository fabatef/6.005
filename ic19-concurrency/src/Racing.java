import java.util.*;

public class Racing {

    private static final int NUMBER_OF_THREADS = 2;
    private static final int NUMBER_OF_OPERATIONS = 500;
    
    public static void main(String[] args) {
        // TODO: choose one of these data structures
        final Set<Integer> set = new HashSet<>();
        //final List<Integer> list = new ArrayList<>();
        //final List<Integer> list = new LinkedList<>();
        //final Map<Integer,Integer> map = new HashMap<>();
        
        System.out.println(NUMBER_OF_THREADS
                            + " threads do "
                            + NUMBER_OF_OPERATIONS
                            + " mutations each...");
        List<Thread> threads= new ArrayList<>();
        
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            final int threadNumber = i; // only final local variables can be seen by inner classes
                                        // like the Runnable below
            Thread t = new Thread(new Runnable() {
                public void run() {
                    System.out.println("thread " + threadNumber + " is starting work");
                    
                    for (int j = 0; j < NUMBER_OF_OPERATIONS; ++j) {
                        int value = j* threadNumber* 1_000;
                        //System.out.println(value);  // uncomment this to mask the race condition
                        
                        set.add(value);
                    }
                    
                    System.out.println(set.size());
                    System.out.println("thread " + threadNumber + " is done");
                    
                    // TODO: print some part of the final data structure (e.g. its size) 
                    //       to see if it's correct
                }
            });
            
            threads.add(t); // the threads in the list are not using the arraylist. The arraylist
                            //  simply holds a reference  to them.
            t.start(); // don't forget to start the thread!
            }
        
        for (Thread t: threads){
        	try{
        		t.join(); //waits for the thread t to finish
        	}
        	catch( InterruptedException e){
        		e.printStackTrace();
        	}
        }
        
        //at this point all the threads have stopped
        
//        int count= 0;   //rep invariant of this list object is broken
//        for (int value: list){ count++;}
//        System.out.println("counted"+ count);
        
        int count= 0;   //rep invariant of this hashset is broken. The precondition was broken( can't use these
                        // data types on multiple threads.
        for (int value: set){ count++;}
        System.out.println("counted"+ count);
    }
}
