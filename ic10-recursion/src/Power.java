
public class Power {
    /**
     * @param n
     * @param k >= 0
     * @return n^k
     */
    public static long power(int n, int k) {
        System.out.println("power(" + n + "," + k + ") = ?");        
        long result =1 ;
        
        if (k==0){
        	result= 1;
        } else if (k%2==0){
        	long nToTheKoverTwo= power(n, k/2);
        	result= nToTheKoverTwo*nToTheKoverTwo;
        } else if (k%2==1){
        	result= power(n, k-1)* n;
        }
        
        System.out.println("power(" + n + "," + k + ") = " + result);
        return result;
    }
    
    public static void main(String[] args) {
        power(2, 32);
    }
}
