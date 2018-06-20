import java.util.Arrays;


public class Find {
    
    /**
     * Find the first occurrence of x in sorted array a.
     * 
     * @param x value to find
     * @param a array sorted in increasing order,
     *              a[0] &lt;= a[1] &lt;= ... &lt;= a[n-1]
     * @return lowest i such that a[i]==x, or -1 if no such i
     */
    public static int find(int x, int[] a) {
        return findLinear(x, a);
    }
    
    /**
     * TODO: specification
     */
    private static int findLinear(int x, int[] a) {
        
    	for (int i= 0; i < a.length; ++i){
        	if (a[i]==x){
        		return i;
        	}		
        }
    	return -1;
    }
    private static int findBinary(int x, int[] a) {
        int middle = a.length / 2;
        if (x < a[middle]) {
            return findBinary(x, Arrays.copyOfRange(a, 0, middle));
        } else if (x > a[middle]) {
            return (middle+1) + findBinary(x, Arrays.copyOfRange(a, middle+1, a.length));
        } else {
            return middle;
        }
    }
}
