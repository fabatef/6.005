import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CumulativeAverage {
	
    
    /**
     * Compute cumulative averages for example data.
     * @param args unused
     */
    public static void main(String[] args) {
        
        List<Integer> data = Arrays.asList(1,2,3);
        List<Double> averages= printCumulativeAverages(data);
        System.out.println(averages);
        
    }
    
    public static List<Double> printCumulativeAverages( List<Integer> data){
        
        int n = 0;
        int total = 0;
        double average = 0;
        List<Double> averages= new ArrayList<>();
         
        for (int value : data) {
            n += 1;
            total += value;
            average = ((double)total) / n;
            averages.add(average);         
        }
        return averages;
    }
}
