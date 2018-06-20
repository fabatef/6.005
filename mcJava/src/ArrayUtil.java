

public class ArrayUtil {

    /**
     * Returns the index of the largest element in an array.
     * @param input a nonempty array of unique elements
     */
    public static int argmax(int[] input) {
    	int max= 0;
    	for(int i= 0; i< input.length; i++){
    		max= input[i] > input[max] ? i: max;
    	}
    	return max;
    	
    }
}
