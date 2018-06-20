import java.math.BigInteger;
import java.util.*;

/**
 * Compute primes in a single thread.
 */
public class SeqentialPrimeSearch {
    
    public static void main(String[] args) {
        int numberOfDigits = 2;
        BigInteger low  = new BigInteger("1000000000000000000000000".substring(0, numberOfDigits));
        BigInteger high = new BigInteger("9999999999999999999999999".substring(0, numberOfDigits));
        System.out.println("searching all " + numberOfDigits + "-digit numbers...");
        Set<BigInteger> primes = primesBetween(low, high);
        System.out.println("found " + primes.size() + " primes");
    }
    
    /**
     * @return all prime numbers p such that low <= p <= high.
     * (all primes are found with very high probability but not certainty).
     */
    public static Set<BigInteger> primesBetween(BigInteger low, BigInteger high) {
        SortedSet<BigInteger> primes = new TreeSet<>();
        addPrimesBetween(low, high, primes);
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
}


//Thread safety 
// - make sure that a local variables are not passed to multiple threads with in the method call
//    before concluding thread safety
// - private fields within a class are not considered confined since they can be shared among instances
//   of the class
// - an immutable reference to a mutable object does not guarantee thread safety(by means of immutability)
// - an immutable private field needs to be declared final to guarantee thread safety
// - 
// - 
// - 
// - 
// - 
