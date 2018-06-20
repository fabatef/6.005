/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input sentence, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   represents an affinity graph where vertices are non-empty, case insensetive strings
    // Representation invariant:
    //   vertices in graph are non-empty, non newline strings
    // Safety from rep exposure:
    //   all fields are private and final. direct references to the mutable type graph are never
    //   returned.
    
    private void checkRep(){
    	for (String node: graph.vertices()){
    		assert(!node.equals(System.getProperty("line.separator")));
    		assert(!(node.equalsIgnoreCase("")));
    	}
    }
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	
    		String line;
    		StringBuilder mass= new StringBuilder();

    		BufferedReader reader = new BufferedReader (new FileReader(corpus));

    		while ((line= reader.readLine())!= null){
    			mass.append(line);
    			mass.append(" ");		
    		}	

    		//remove empty strings
    		List<String> words= new ArrayList<String>(Arrays.asList(mass.toString().toLowerCase().split(" ")));
    		
    		List<String> toBeRemoved= new ArrayList<String>();
    		for (String word: words){
    			if(word.equals("")){
    				toBeRemoved.add(word);
    			}
    		}
    		words.removeAll(toBeRemoved);
    		
    		for(int i=1; i<words.size();i++){
    			int defaultWeight= graph.set(words.get(i-1), words.get(i), 1);
    			if (defaultWeight !=0){
    				graph.set(words.get(i-1), words.get(i), defaultWeight+1);
    			}
    			else{
    				graph.set(words.get(i-1), words.get(i), 1); 
    			}
    		}
    		
    		checkRep();
    		reader.close();
   
    }
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	List<String> inputList= Arrays.asList(input.toLowerCase().split(" "));  // case insensitive version of the input string
    	List<String> original= Arrays.asList(input.split(" "));  // the original string
    
    	StringBuilder result= new StringBuilder();
    	Set<String> nodes= graph.vertices();
    
    	
    	
    	for (int i= 0; i<inputList.size() - 1; i++){
    		if (!(nodes.contains(inputList.get(i)))){  // if the input word is not in the graph, add it into the 
    			result.append(original.get(i));         // stringBuilder to be returned
    			result.append(" ");
    			
    		
    		}else{
    			
    			Set<String> targets= graph.targets(inputList.get(i)).keySet(); //if the word is in the graph, get all the targets of that word
    			Map<String, Integer> legible= new HashMap<String, Integer>();  // stores all possible bridge words
    			
    			for(String target: targets){
    				int weight= 0;
    				if(graph.targets(target).keySet().contains(inputList.get(i+1))){ // if the next word is in the targets of the set of targets from the first word
    					weight+= graph.targets(inputList.get(i)).get(target);        // compute the total weight of the path and add the target word into the map of
    					weight+= graph.targets(target).get(inputList.get(i+1));      // legible bridge words
    					legible.put(target, weight);
    					
    				}else{
    					continue;
    				}			
    			}
    			
    			// check
    			String bridge= "";
    			int maxWeight= 0;
    			for (String b : legible.keySet()){    // choose the bridge word with the maximum weight
    				if (maxWeight < legible.get(b)){
    					maxWeight= legible.get(b);
    					bridge= b;	
    				}
    			}
    			
    			result.append(original.get(i));  // append the original word
    			result.append(" ");
    			result.append(bridge);    // append the bridge word
    			if (!bridge.equals("")){ // preventing double spaces in the case where there are no legible bridge words
    				result.append(" ");
    			}
    			
    		}
    		
    		
    	}
    	
    	result.append(original.get(original.size()-1)); //append the last element
    	checkRep();
    	return result.toString();
    	
    }
}
