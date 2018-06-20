/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph where a graph is a mutable weighted directed graph with labeled vertices.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   represents a weighted directed graph
    // Representation invariant:
    //   List<Edge> edges does not contain repeated edge elements
    //   Set<String> vertices does not contain repeated node labels. (checked by Set by default)
    // Safety from rep exposure:
    //   All the fields are private and final. Mutable type Set<String> vertices is returned with an 
    //   unmodifiable wrapper.
    
    
    public ConcreteEdgesGraph(){
    	checkRep();
    }
    
    //checkRep
    private void checkRep(){
    	for(Edge<L> e: edges){
    		int count=0;
    		for(Edge<L> edge: edges){
    			if(e.isSameEdge(edge) && (e.getWeight() == edge.getWeight())){
    				count+=1;
    			}
    		}
    		assert(count==1);
    	}    	
    }
    
    @Override public boolean add(L vertex) {
    	return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
    	
    	Edge<L> setEdge= new Edge<L>(source,target,weight);
    	List<Edge<L>> compare = new ArrayList<Edge<L>>();
    	
    	for (Edge<L> edge: edges){
    		if(setEdge.isSameEdge(edge)){
    			compare.add(edge);
    		}
    	}
    	
    	if(compare.size()==0){
    		if (vertices.size() != 0 || weight >0){  //if the graph is empty and the weight is 0, don't add the edge or the nodes
    			edges.add(setEdge);
    			vertices.addAll(Arrays.asList(source,target));
    		}
    		checkRep();
    		return 0;
      	}else{
    		Edge<L> existingEdge= compare.get(0);
			edges.remove(existingEdge);
    		if(weight > 0){	
    			edges.add(new Edge<L>(source,target,weight));
    		}
    		
    		checkRep();
    		return existingEdge.getWeight();
    	}   	
    }
    
    @Override public boolean remove(L vertex) {
    	List<Edge<L>> toBeRemoved= new ArrayList<Edge<L>>();
    	for (Edge<L> edge: edges){
    		if(edge.getSource().equals(vertex) || edge.getTarget().equals(vertex)){
    			toBeRemoved.add(edge);
    		}
    	}
    	edges.removeAll(toBeRemoved);
    	checkRep();
    	return vertices.remove(vertex);
     }
    
    @Override public Set<L> vertices() {
    	checkRep();
        return Collections.unmodifiableSet(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	List<Edge<L>> targetEdges= new ArrayList<Edge<L>>();
    	Map<L,Integer> result= new HashMap<L,Integer>();
    	for (Edge<L> edge: edges){
    		if(edge.getTarget().equals(target)){
    			targetEdges.add(edge);
    		}
    	}
    	for (Edge<L> edge: targetEdges){
    		result.put(edge.getSource(), edge.getWeight());
    	}
    	checkRep();
    	return result;
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	List<Edge<L>> sourceEdges= new ArrayList<Edge<L>>();
    	Map<L,Integer> result= new HashMap<L,Integer>();
    	for (Edge<L> edge: edges){
    		if(edge.getSource().equals(source)){
    			sourceEdges.add(edge);
    		}
    	}
    	for (Edge<L> edge: sourceEdges){
    		result.put(edge.getTarget(), edge.getWeight());
    	}
    	checkRep();
    	return result;
    }
    
    /**
     * @return string representation of a graph with the following form if the graph is not empty:
     *         source --> target : edgeWeight
     *         if the graph is empty returns "This graph is empty"
     * */
     
    @Override public String toString(){
    	if (vertices.size() > 1){
    		StringBuilder result= new StringBuilder();
    		for (Edge<L> edge: edges){
    			result.append(edge.toString());
    			result.append(System.getProperty("line.separator"));
    		}
    		checkRep();
    		return result.toString();
    	} else if (vertices.size()==1){
    		StringBuilder result= new StringBuilder();
    		List<L> nodes= new ArrayList<L>(vertices);
    		result.append(nodes.get(0));
    		return result.toString();
    	}
    	else {
    		checkRep();
    		return "This is an empty graph";
    	}
    }
    
}

/**
 * An immutable type representing an edge in a weighted directed graph. 
 * An edge has a source node, where it originates, and a target node, where it ends. It has a weight which has a
 * non-zero positive integer value. 
 * This class is internal to the rep of ConcreteEdgesGraph.
 *                
 */
class Edge<L> {
    
    //fields
	private final L source;
	private final L target;
	private int weight; 
    
    // Abstraction function:
    //   represents a weighted edge directed from a source node to a target node.
    // Representation invariant:
    //   -the weight of the edge must be >= 0
    // Safety from rep exposure:
    //   -All fields are private, final and immutable.
    
	
	public Edge (L sourceNode, L targetNode, int edgeWeight){
		this.source= sourceNode;
		this.target= targetNode;
		this.weight= edgeWeight;
		
	}
    

    private void checkRep(){
    	assert(this.weight >= 0); 
    }
	//observers
    
    /**
     * @return  an integer value of the weight of an Edge from source to target
     * 
     */
	public int getWeight(){
		checkRep();
		return weight;
	}
	
	/**
	 * @return a String representation of the source node
	 */
	public L getSource(){
		checkRep();
		return source;
	}
	
	/**
	 * @return a String representation of the target node
	 */
	public L getTarget(){
		checkRep();
		return target;
	}
	/**
	 * @param  an edge object to be compared to this 
	 * @return returns true if the source and target node of both edges are the same. False otherwise 
	 */
	public boolean isSameEdge(Edge<L> that){
		checkRep();
		return ((this.getSource().equals(that.getSource())) && (this.getTarget().equals(that.getTarget())));
	}
	
	   /**
     * @return string representation of an edge of the form:
     *         source --> target : weight
     */
	@Override public String toString(){
		checkRep();
		return this.getSource() + " --> " + this.getTarget() + " : " + this.getWeight();
	}
    
    
}
