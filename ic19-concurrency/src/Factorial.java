import java.math.BigInteger;

public class Factorial {
    
    /**
     * Computes n! and prints it on standard output.
     * @param n must be >= 0
     */
    private static void computeFact(final int n) {
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= n; ++i) {
            System.out.println("working on fact " + n);
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println("fact(" + n + ") = " + result);
    }
    
    //local variables are not shared among threads.. System.out / system.in are shared by
    // all threads in the same process
    
    public static void main(String[] args) {
    	//option 1: create an anonymous class that implements Fact in a new thread
    	
    	new Thread( new Runnable(){   
    		public void run(){        
    			computeFact(99);
    		}
    	}).start();
       // Thread.yield(); //simulate possible interleavings that may happen
    	computeFact(100);
    		
    	
    	
    	//option 2: using lambda
    	
    	//new Thread(() -> computeFact(99)).start(); 
    	//new Thread(() -> computeFact(100)).start();
    	
    	//option 3: static class that implements Runnable
    	
    	//new Thread( new Fact(99)).start();
    	//new Thread( new Fact(100)).start();
    	
        
    }
    static class Fact implements Runnable { 
    	
    	private final int value;
    	
    	public Fact(int value){
    		this.value= value;
    	}
    	@Override
    	public void run(){
    		computeFact(value);
    	}
    }
    
}

