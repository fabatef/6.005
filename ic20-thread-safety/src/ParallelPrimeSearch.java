import java.math.BigInteger;
import java.util.*;

/**
 * Compute primes in multiple threads.
 */
public class ParallelPrimeSearch {
    
    //
    // Thread safety argument:
    //    -numberOfDigits: is confined
	//    -low: the variable low is confined(because its a local variable and immutable)
	//        : the value of low is confined because its immutable ?
	//    -lowForThisThread: is not confined, the value is immutable, okay to share because
	//                     : it's never reassigned
	//    -primes: is confined (local variable) for addPrimesBetween. It's not confined for 
	//           : primesBetween since it's shared b/n threads and it's mutable. However it's
	//           : a thread safe data type 
	//    - 
    //
    
    public static void main(String[] args) {
        final int numberOfDigits = 6;
        final BigInteger low  = new BigInteger("1000000000000000000000000".substring(0, numberOfDigits));
        BigInteger high = new BigInteger("9999999999999999999999999".substring(0, numberOfDigits));
        System.out.println("searching all " + numberOfDigits + "-digit numbers...");
        SortedSet<BigInteger> primes = primesBetween(low, high);
        System.out.println("found " + primes.size() + " primes, from " + primes.first() + " to " + primes.last());
    }
    
    private static int NUMBER_OF_THREADS = 4;
    
    /**
     * @return all prime numbers p such that low <= p <= high.
     * (all primes are found with very high probability but not certainty).
     */
    public static SortedSet<BigInteger> primesBetween(final BigInteger low, final BigInteger high) {
        SortedSet<BigInteger> primes = Collections.synchronizedSortedSet(new TreeSet<>());
        
        // partition the [low, high] range into one part for each thread
        BigInteger[] divisorAndRemainder = high.subtract(low).divideAndRemainder(BigInteger.valueOf(NUMBER_OF_THREADS));
        BigInteger interval = divisorAndRemainder[0];
        BigInteger remainder = divisorAndRemainder[1];
        
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < NUMBER_OF_THREADS; ++i) {
            final BigInteger lowForThisThread = low.add(interval.multiply(BigInteger.valueOf(i)));
            final BigInteger highForThisThread = low.add(interval.multiply(BigInteger.valueOf(i).add(BigInteger.ONE)));
        	
            Thread thread = new Thread( new Runnable(){   
        		public void run(){        
        			addPrimesBetween(lowForThisThread,highForThisThread, primes);
        		}
        	});  
            
            thread.start();
            threads.add(thread);
        	
        }
        

        
        // handle the remainder in the main thread
        BigInteger lowLeftover = high.subtract(remainder);
        BigInteger highLeftover = high;
        addPrimesBetween(lowLeftover, highLeftover, primes);
        
        waitForAllThreadsToFinish(threads);
        
        return primes;
    }
    
    /*
     * Modifies primes set by adding all prime numbers p such that low <= p <= high.
     * (all primes are found with very high probability but not certainty).
     */
    private static void addPrimesBetween(BigInteger low, BigInteger high, Set<BigInteger> primes) {   
        BigInteger prime;
        
        // find the first prime >= low
        if (low.compareTo(BigInteger.ONE) <= 0) {
            prime = BigInteger.valueOf(2);
        } else {
            prime = low.subtract(BigInteger.ONE).nextProbablePrime();
        }
        
        while (prime.compareTo(high) <= 0) {
            primes.add(prime);
            prime = prime.nextProbablePrime();
        }
    }
    
    /*
     * Returns after all threads in the collection have exited.
     */
    private static void waitForAllThreadsToFinish(Collection<Thread> threads) {
        // wait for all the threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ie) {
                assert false; // should never happen
            }
        }        
    }
}
