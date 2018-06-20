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
 * An implementation of Graph where a graph is mutable weighted directed graph with labeled vertices.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<Vertex<L>>();
    
    // Abstraction function:
    //   represents a weighted directed graph were each vertex represents itself and all
    //   the edges it draws from itself to another node and the edges it gets from some source
    //   node to itself.
    // Representation invariant:
    //   no repeated nodes in Vertices
    // Safety from rep exposure:
    //   All fields are private and a direct reference to the mutable type List<Vertex> vertices is never
    //   returned by any of the methods
    
    public ConcreteVerticesGraph (){
    	checkRep();
    }
    
    private void checkRep(){
      	for(Vertex<L> vertex: vertices){
    		int count=0;
    		if(this.isInGraph(vertex)){
    				count+=1;	
    		}
    		assert(count==1);
    		assert(vertex!= null);
    	}
    	
    }
    //helper method
    private boolean isInGraph(Vertex<L> v){
    	for (Vertex<L> node: vertices){
    		if(v.getLabel().equals(node.getLabel())){
    			return true;
    		}
    	}
    	return false;
    }
    
    
    @Override public boolean add(L vertex) {
    	List<L> nodesInG= new ArrayList<L>();
    	for (Vertex<L> v: vertices){
    		nodesInG.add(v.getLabel());
    	}
    	
    	if (nodesInG.contains(vertex)){
    		checkRep();
    		return false;
    	} else{
    		vertices.add(new Vertex<L>(vertex));
    		checkRep();
    		return true;
    	}
    }
    

    
    @Override public int set(L source, L target, int weight) {
    	
    	Vertex<L> sourceNode= new Vertex<L>(source);
    	Vertex<L> targetNode= new Vertex<L>(target);
    	
    	for (Vertex<L> v : vertices){
    		if(v.getLabel().equals(source)){
    			sourceNode= v; 			
    			
    		} if (v.getLabel().equals(target)){
    			targetNode= v;
    		}
    	}
    	
    	if (weight > 0 && sourceNode.hasLeaf(targetNode.getLabel())){
    		int edgeWeight= sourceNode.getWeightLeaf(targetNode.getLabel());
    		sourceNode.addLeaf(target, weight);
    		targetNode.addRoot(source, weight);
    		checkRep();
    		return edgeWeight;
    		}else if (weight==0 && sourceNode.hasLeaf(targetNode.getLabel()) ){
    			int edgeWeight= sourceNode.getWeightLeaf(targetNode.getLabel());
    			
    			sourceNode.removeLeaf(target);
    			targetNode.removeRoot(source);
    		
    			checkRep();
    			return edgeWeight;
    		}else{
    			if(weight>0){
    				if(!vertices.contains(sourceNode)&&!vertices.contains(targetNode)){
    		
    					vertices.add(sourceNode);
    					vertices.add(targetNode);
    				}else if(!vertices.contains(sourceNode)){
    					vertices.add(sourceNode);
    				}else if(!vertices.contains(targetNode)){
    					vertices.add(targetNode);
    				}
    				sourceNode.addLeaf(target, weight);
    				targetNode.addRoot(source, weight);
    			}
        		checkRep();
    			return 0;

    	}
    }
    
    @Override public boolean remove(L vertex) {
    	List<Vertex<L>> toBeRemoved= new ArrayList<Vertex<L>>();
		Set<L> vsRoots= new HashSet<>();
    	Set<L> vsLeaves= new HashSet<>();
    	for (Vertex<L> v: vertices){
    		if (v.getLabel().equals(vertex)){
    			toBeRemoved.add(v);
    			vsRoots= v.getRoots().keySet();
    			vsLeaves= v.getLeaves().keySet();
    			}
    		}
    	for (Vertex<L> v: vertices){
    		for(L lable: vsRoots){
    			if (v.getLabel().equals(lable)){
    				v.removeLeaf(vertex);
    			}
    		}
    		
    		for(L lable: vsLeaves){
    			if (v.getLabel().equals(lable)){
    				v.removeRoot(vertex);
    			}
    		}	
    	}

    	checkRep();
    	return vertices.removeAll(toBeRemoved);
    	}
    	
  

    
    @Override public Set<L> vertices() {
    	Set<L> nodesInG= new HashSet<>();
    	for (Vertex<L> v: vertices){
    		nodesInG.add(v.getLabel());
    	}
    	checkRep();
    	return Collections.unmodifiableSet(nodesInG);
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	for (Vertex<L> v: vertices){
    		if (v.getLabel().equals(target)){
    			checkRep();
    			return v.getRoots();
    		}
    	}
    	checkRep();
    	return Collections.emptyMap();
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	for (Vertex<L> v: vertices){
    		if (v.getLabel().equals(source)){
    			checkRep();
    			return v.getLeaves();
    		}
    	}
    	checkRep();
    	return Collections.emptyMap();
    	
    	}
    
    /**
     * @return string representation of a graph with the following form if the graph is not empty:
     *         source --> target : edgeWeight  
     *         if the graph is empty returns "This is an empty graph"
     * */
    @Override public String toString(){
    	StringBuilder result= new StringBuilder();
    	Set<String> toBeAppended= new HashSet<String>();
    	if (vertices.size() > 1){
    		for(Vertex<L> v: vertices){
    			List<String> edges= Arrays.asList(v.toString().split(System.getProperty("line.separator")));
    			toBeAppended.addAll(edges);

    		}
    		for(String edge: toBeAppended){
    			result.append(edge);
    			result.append(System.getProperty("line.separator"));
    		}
    		checkRep();
    		return result.toString();
    	} else if (vertices.size()== 1){
    		checkRep();
    		return vertices.get(0).toString();
    	} else {
    		checkRep();
    		return "This is an empty graph";
    	}
    
    }
}
    



/**
 * Mutable.
 * 
 * A vertex is a node which may or may not have leaves and may or may not have roots. If either a root
 * or a leaf exists then the vertex is connected to it by a weighted edge.
 * 
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
	private final L node;
	private final Map<L, Integer> leaves;
	private final Map<L, Integer> roots; 
	
    
    // Abstraction function:
    //   represents a node in the graph. roots represent all the parents of the node and
	//   leaves represent all of the children of the node
    // Representation invariant:
    //  values for the keys in leaves and roots must all be >0 
	// Safety from rep exposure:
    //   All fields are private and no direct references to mutable types leaves and roots are 
    //   returned by any of the methods
    
 
	public Vertex(L N){
		this.node= N;
		this.leaves= new HashMap<>();
		this.roots= new HashMap<>();
	}
	
    
    // checkRep
	private void checkRep(){
		for (L lable: leaves.keySet()){
			assert(leaves.get(lable)>0);
		}
		for (L lable: roots.keySet()){
			assert(roots.get(lable)>0);
		}
	}

    
    //observers
	/**
	 * 
	 * @return the label of the vertex
	 */
	public L getLabel(){
		checkRep();
		return node;
	}
	
	// mutators 
	
	/**
	 * adds a vertex as a leaf of this Vertex object
	 * 
	 * @param v : the label of the vertex to be added as a leaf 
	 * @param weight: the weight of the edge between the vertices. Must be greater than zero
	 */
	public void addLeaf(L v, int weight){
		checkRep();
		leaves.put(v, weight );
	}
	
	/**
	 * adds a vertex as a root of this Vertex object
	 * 
	 * @param v : the label of the vertex to be added as a root 
	 * @param weight: the weight of the edge between the vertices. Must be greater than zero.
	 */
    
	public void addRoot(L v, int weight){
		checkRep();
		roots.put(v, weight);
	}
	
	
	/**
	 * removes a leaf 
	 * 
	 * @param v : the label of the vertex to be removed from the collection of leaves of this.vertex
	 */
	
	public void removeLeaf(L v){
		checkRep();
		leaves.remove(v);
	}
	
	/**
	 * removes a root 
	 * 
	 * @param v : the label of the vertex to be removed from the collection of roots of this.vertex
	 */
	
	public void removeRoot(L v){
		checkRep();
		roots.remove(v);
	}
	//observers
	/**
	 * gets the weight of the edge from this.vertex to its leaf
	 * 
	 * @param v : the label of leaf vertex
	 * @return weight of the edge
	 */
	public int getWeightLeaf(L v){
		checkRep();
		return leaves.get(v);		
	}
	
	/**
	 * gets the weight of the edge from a root to this.vertex
	 * 
	 * @param v : the root vertex
	 * @return weight of the edge
	 */
	
	public int getWeightRoot(L v){
		checkRep();
		return roots.get(v);
	}
	
	/**
	 * checks if V is a root of this.vertex
	 * 
	 * @param v: the vertex object to be checked
	 * @return true if v is the root of this.vertex , false if it's not.
	 */
	
	public boolean hasRoot(L v){
		checkRep();
		return roots.containsKey(v);
	}
	
	/**
	 * checks if V is a leaf of this.vertex
	 * 
	 * @param v: the vertex object to be checked
	 * @return true if v is the leaf of this.vertex , false if it's not.
	 */
	
	public boolean hasLeaf(L v){
		checkRep();
		return leaves.containsKey(v);
	}
	
	/**
	 * get all the roots of this.vertex and the weight of the edge associated with them.
	 * 
	 * @return a map where the key is a root vertex and the value is the weight of the edge from
	 *         that root to this.vertex.
	 */
	public Map<L,Integer> getRoots(){
		checkRep();
		return new HashMap<>(roots);
	}
	
	/**
	 * get all the leaves of this.vertex and the weight of the edge associated with them.
	 * 
	 * @return a map where the key is a leaf vertex and the value is the weight of the edge from
	 *         this.vertex to that leaf.
	 */
	
	public Map<L, Integer>getLeaves(){
		checkRep();
		return new HashMap<>(leaves);
	}
	
    /**
     * @return string representation of an edge with the following form if this.vertex has leaves/roots or both:
     *         this.getLable() --> leaf.getLable() : edge-weight
     *         root.getLable() --> this.getLable() : edge-weight
     *         if this.vertex has no roots or leaves, it returns the label of the the vertex.
     * */
	
    @Override public String toString(){
    	StringBuilder result= new StringBuilder();
    	if (leaves.size() != 0 && roots.size() != 0){
    		for (L lable: leaves.keySet()){
    			result.append(this.getLabel()); result.append(" --> "); result.append(lable);
    			result.append(" : "); result.append(leaves.get(lable));
    			result.append(System.getProperty("line.separator"));
    		}
    		for (L lable: roots.keySet()){
    			result.append(lable); result.append(" --> "); result.append(this.getLabel());
    			result.append(" : "); result.append(roots.get(lable));
    			result.append(System.getProperty("line.separator"));    			
    		}
    		checkRep();
    		return result.toString();
    		
    	} else if (leaves.size() != 0 ){
    		for (L lable: leaves.keySet()){
    			result.append(this.getLabel()); result.append(" --> "); result.append(lable);
    			result.append(" : "); result.append(leaves.get(lable));
    			result.append(System.getProperty("line.separator"));
    		}
    		checkRep();
    		return result.toString();
    		
    	} else if (roots.size() != 0){
    		for (L lable: roots.keySet()){
    			result.append(lable); result.append(" --> "); result.append(this.getLabel());
    			result.append(" : "); result.append(roots.get(lable));
    			result.append(System.getProperty("line.separator"));    			
    		}
    		checkRep();
    		return result.toString();
    	} else {
    		checkRep();
    		result.append(this.getLabel());
    		return result.toString();
    	}
    }
    @Override
    public boolean equals(Object obj){
    	if (! (obj instanceof Vertex<?>) ){
    		return false;
    	}
    	Vertex<?> that= (Vertex<?>) obj;
    	
    	return this.getLabel().equals(that.getLabel());
    }
    @Override
    public int hashCode(){
    	return 1;
    }
}



