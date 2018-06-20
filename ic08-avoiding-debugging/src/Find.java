import java.util.Arrays;

public class Find {
    
    /**
     * Find the first occurrence of x in sorted array a.
     * 
     * @param x value to find
     * @param a array sorted in increasing order,
     *              a[0] &lt;= a[1] &lt;= ... &lt;= a[n-1]
     * @return any i such that a[i]==x, or -1 if no such i
     */
    public static int find(int x, int[] a) {
        return findLinear(x, a);
    }
    
    /**
     * @see find()
     */
    public static boolean isSorted(int[] a){
    	
    	for (int i= 0; i < a.length-1; i++ ){
    		if (a[i] > a[i+1]){
    			return false;
    		}
    	}
    	return true;
    }
    private static int findLinear(int x, int[] a) {
    	
    	assert isSorted(a) : "the list is not sorted"; 
       
        for (int i = 0; i < a.length ; ++i) {            
            if (x == a[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @see find()
     */
    private static int findBinaryRecursive(int x, int[] a) {
        assert isSorted(a) : "the list is not sorted"; 
        int middle = a.length / 2;
        if (x < a[middle]) {
            return findBinaryRecursive(x, Arrays.copyOfRange(a, 0, middle));
        } else if (x > a[middle]) {
            return (middle+1) + findBinaryRecursive(x, Arrays.copyOfRange(a, middle+1, a.length));
        } else {
            return middle;
        }
    }
    

    /**
     * @see find()
     */
    private static int findBinaryIterative(int x, int[] a) {

        int low = 0;
        int high = a.length-1;
     
        while (true) {
            // Loop invariant: as we shrink the range of possibility where x could occur, 
            // it should always be true that:
            //    if x occurs in the array at some index i, then
            //         low <= i <= high
            //
            
            /* TODO #1: assert the loop invariant here.  (Hint: you can use findLinear().)
            
            
            /* TODO #2: split [lowIndex,highIndex] range in half and make the loop
             * invariant true again.
             */
            
            /* TODO #3: make the loop invariant true before the loop starts. */
        }
    }
}
