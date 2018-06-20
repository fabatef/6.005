import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//jswing and finish
public class Words {
	
	/**
	 * Reads a text file and converts the content to a string 
	 * @param textFile a text file containing a non empty string of words
	 * @return a string version of the text file
	 */
	public static String textFileToString(File text){
		String line;
		BufferedReader reader;
    	StringBuilder textFile= new StringBuilder("");
    	
    	try {
    		reader = new BufferedReader(new FileReader(text));
			while((line = reader.readLine()) != null){
				textFile.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return textFile.toString();	
	}
	
	/**
	 * Finds the number of sentences in a text file.
	 * @param text a .txt file containing non-empty string of words 
	 * @return the number of sentences in the file.
	 */
	public static int numOfSentences(File text){
		String textFile = textFileToString(text);
		List<String> splitText = Arrays.asList(textFile.split(" "));
		//System.out.println(splitText);
		int count = 0;
		for(String word: splitText){
			if (word.matches("\\p{Alpha}+(\\.|!|\\?)")){
				count += 1;
			}
		}
		return count;
	}
	
	
	/**
	 * Finds a list of the most common words in a text file.
	 * @param text a non-empty string of words
	 * @return a list of words that occur most often in text, comparison is not case-insensitive
	 */
    public static List<String> mostCommonWords(File text){
    	String textFile= textFileToString(text).replaceAll("[\\p{Punct}&&[^'''|'-']]","" ); //filter punctuation;
    	//System.out.println(textFile);
    	List<String> splitText = splitIntoWords(textFile);
    	Map<String, Integer> sortedFrequencyMap = sortFrequencyMap(countOccurrences(splitText));
    	System.out.println(sortedFrequencyMap);
    	List<String> mostCommon = findMostFrequent(sortedFrequencyMap);
    	
    	StringBuilder printOut= new StringBuilder("");
    	
    	for(int i= 1; i<mostCommon.size()-1; i++){
    		printOut.append(", "+ mostCommon.get(i));
    	}
    	
    	if (mostCommon.size()!= 1){
        	System.out.printf("The most frequent words are %s%s and %s. The words appear %d times. There are a total of %d sentences in the file.",
        			mostCommon.get(0), printOut, mostCommon.get(mostCommon.size()-1), sortedFrequencyMap.get(mostCommon.get(0)), numOfSentences(text));	
    	} else {
        	System.out.printf("The most frequent word is %s. The word appears %d times. There are a total of %d sentences in the file.",
        			mostCommon.get(0), sortedFrequencyMap.get(mostCommon.get(0)), numOfSentences(text));
    	}
    	return mostCommon;    	
    }
    
    
	/**
	 * Splits the input text into words using " " as a delimiter.
	 * 
	 * @param text a non-empty string of words
	 * @return returns a list of strings [a_0, a_1...a_k]
	 *         where a_0 + " " + a_1 + " " +....+ " " + a_k 
	 *         equals the original text.
	 */
    public static List<String> splitIntoWords(String text) {
    	return Arrays.asList(text.toLowerCase().split(" "));
    }
    
    /**
     * Counts how many times each word appears in the input list
     * @param words a list of non-empty strings  
     * @return  a mapping of each word in the input
     *           to the number of times it occurs in the list 
     */
    public static Map<String,Integer> countOccurrences(List<String> words) {
    	Map<String,Integer> occurenceMap= new HashMap<String,Integer>();
    	
    	for(int i = 0; i < words.size(); i++){
    		for(int j = 0; j < words.size(); j++){
    			String word = words.get(i);
    			String compare = words.get(j);
    			if(word.equals(compare) && !occurenceMap.containsKey(word)){
    				occurenceMap.put(word, 1);
    			} else if (word.equals(compare) && occurenceMap.containsKey(word)){
    				if (i == j){
    					int newValue= occurenceMap.get(word) + 1;
        				occurenceMap.put(word,newValue);
    				}  				
    			} else{
    				continue;
    			}		
    		} 		
    	}
    	return occurenceMap;	  	    	
    }
    
    /** 
     * Finds the word with the highest frequency count from the input Map.
     * 
     * @param frequencies  a mapping of each word to the number of time it occurs in text
     * @return  a list of strings with the most number of occurrences.
     */
    public static List<String> findMostFrequent(Map<String,Integer> frequencies) {
    	Collection<Integer> frequencyValues = frequencies.values();
    	List<String> mostFrequent = new ArrayList<String>();
    	int maxFrequency = Collections.max(frequencyValues);
    	for(Map.Entry<String, Integer> entry: frequencies.entrySet()){
    		if(entry.getValue() == maxFrequency){
    			mostFrequent.add(entry.getKey());
    		}
    	} 
    	return mostFrequent;
    }      
    
    /**
     * Returns a map sorted by its value in a descending order.
     * @param map the map to be sorted
     * @return sorted map
     */
    public static <K,V extends Comparable<? super V>> Map<String, Integer> sortFrequencyMap(Map<String,Integer> map){
    	List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
    	Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>(){
    		@Override
    		public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer>entry2){
    			return entry2.getValue().compareTo(entry1.getValue());
    		}	
    	});
    	
    	Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    	
    	for(Map.Entry<String, Integer> entry: sortedList){
    		sortedMap.put(entry.getKey(), entry.getValue());
    	}
    	
    	return sortedMap;
    }
    
    public static void main(String[] args) {
    	File testFile= new File("src/blah.txt");
    	mostCommonWords(testFile);

	}
}
