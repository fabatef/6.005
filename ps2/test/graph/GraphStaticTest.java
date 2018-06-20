/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of Graph.
 * 
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {
    
    // Testing strategy
    //   empty()
    //     no inputs, only output is empty graph
    //     observe with vertices()
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }
    
    // Testing strategy
    // - run tests in GraphInstanceTest with integers as labels
    // - run tests in GrpahInstanceTest with
    
// Tests for add with integers
    
    @Test  //Partition 1: The vertex to be added is not in the graph
    public void vertexNotInGraphAdd() {
    	Graph<Integer> testGraph = Graph.empty();  
        assertTrue(testGraph.add(1));
        assertEquals(1, testGraph.vertices().size());  
    }
    
    @Test  //Partition 2: The vertex to be added is already in the graph
    public void vertexInGraphAdd() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	
    	assertFalse(testGraph.add(10));
    	assertEquals(2, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new Integer[] {10, 20})));  
    }
    // Testing strategy for toString() with integers
    //   -empty Graph, a graph with multiple edges and vertices, a graph with one node
    

    
    @Test //Partition 3: graph with one node
    public void oneNodeToString(){
    	Graph<Integer> testGraph= Graph.empty();
    	testGraph.add(10);
    	
    	assertEquals("10", testGraph.toString());
    }
    
//Tests for set 
    
    @Test  //Partition 1: weight =1, source not in graph, target not in graph
    public void setAnEmptyGraph() {
    	Graph<Integer> testGraph = Graph.empty();
    	
    	
    	assertEquals(0, testGraph.set(10,20, 1));
    	//System.out.println(testGraph.vertices());
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new Integer[] {10, 20}))); 
    	assertTrue(testGraph.targets(10).containsKey(20));
    	int weight= testGraph.targets(10).get(20);
    	assertEquals(1, weight); 
    }
        
    @Test  //Partition 2: weight = 0, source in graph, target in graph
    public void setZeroWeight() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10,20, 1);
    	int result= testGraph.set(10, 20, 0);
    	
    	assertEquals(1, result);
    	assertEquals(2,testGraph.vertices().size() );
    }
    
    @Test  //Partition 3: weight = 0, source not in graph, target not in graph
    public void setEmptyGraphZeroWeight() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10,20, 0);
    	int result= testGraph.set(10, 20, 0);
    	
    	assertEquals(0, result);
    	assertEquals(0,testGraph.vertices().size() );
    }
    
    @Test  //Partition 4: weight > 1, source in graph, target in graph, existing edge-weight < weight
    public void setExistingEdgeSmallerWeight() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10,20, 1);
    	int result= testGraph.set(10, 20, 5);
    	
    	assertEquals(1, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new Integer[] {10, 20}))); 
    	assertTrue(testGraph.targets(10).containsKey(20));
    	int weight= testGraph.targets(10).get(20);
    	assertEquals(5, weight); 
    }
    
 // Tests for remove
    
    @Test  //Partition 1: The vertex to be removed is not in the graph
    public void vertexNotInGraphRemove() {
    	Graph<Integer> testGraph = Graph.empty();
    	
        assertFalse(testGraph.remove(10));
        assertEquals(0, testGraph.vertices().size());  
    }
    
    @Test  //Partition 2: The vertex to be removed is already in the graph with zero edges
    public void vertexInGraphRemoveNoEdge() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	
    	assertTrue(testGraph.remove(20));
    	assertEquals(1, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().contains(10));  
    }
    
    @Test  //Partition 3: The vertex to be removed is already in the graph with one edge
    public void vertexInGraphRemoveOneEdge() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	
    	assertTrue(testGraph.remove(10));
    	assertEquals(1, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().contains(20));  
    }
    
//Test for vertices
    
    @Test //Partition 1: no vertices
    public void emptyGraphVertices(){
    	Graph<Integer> testGraph= Graph.empty();
    	
    	assertEquals(0, testGraph.vertices().size());
    }
    
    @Test //Partition 2: one vertex
    public void oneVertex(){
    	Graph<Integer> testGraph= Graph.empty();
    	testGraph.add(10);
    	
    	assertEquals(1, testGraph.vertices().size());
    }
    
    @Test  //Partition 3: nodes > 1
    public void multipleVertices() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	testGraph.set(10, 30, 3);
    	//System.out.println(testGraph.vertices()+"here");
    	
    	assertEquals(3, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new Integer[] {10, 20, 30})));  
    }
    
 //Tests for sources
    
    @Test  //Partition 1:  target in the graph with no sources
    public void noSource() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.add(10);
    	
    	assertEquals(0, testGraph.sources(10).size());  
    }
    
    @Test  //Partition 2:  target not in the graph 
    public void emptyGraphNoSource() {   //should it throw an exception?? 
    	Graph<Integer> testGraph = Graph.empty();
    	
    	assertEquals(0, testGraph.sources(10).size());  
    }
    
    @Test  //Partition 3:  target in the graph with one source
    public void oneSource() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	
    	assertEquals(1, testGraph.sources(20).size());  
    	assertEquals(1, testGraph.sources(20).keySet().size());
    	int edgeWeight = testGraph.sources(20).get(10);
    	assertEquals(1, edgeWeight);
    }
    
 //Tests for targets

    @Test  //Partition 1:  source in the graph with no targets
    public void noTarget() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.add(10);
    	
    	assertEquals(0, testGraph.targets(10).size());  
    }
    
    @Test  //Partition 2:  source not in the graph 
    public void emptyGraphNoTarget() {   //should it throw an exception?? 
    	Graph<Integer> testGraph = Graph.empty();
    	
    	assertEquals(0, testGraph.sources(10).size());  
    }
    
    @Test  //Partition 3:  source in the graph with one target
    public void oneTarget() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(10, 20, 1);
    	
    	assertEquals(1, testGraph.targets(10).size());  
    	assertEquals(1, testGraph.targets(10).keySet().size());
    	int edgeWeight = testGraph.targets(10).get(20);
    	assertEquals(1, edgeWeight);
    }
    
    @Test  //Partition 4:  source in the graph with multiple targets
    public void multipleTargets() {
    	Graph<Integer> testGraph = Graph.empty();
    	testGraph.set(20, 10, 1);
    	testGraph.set(20, 30, 2);
    	
    	int edgeWeightAB = testGraph.targets(20).get(10);
    	int edgeWeightCB = testGraph.targets(20).get(30);
    	
    	assertEquals(2, testGraph.targets(20).size());  
    	assertEquals(2, testGraph.targets(20).keySet().size());
    	assertEquals(1, edgeWeightAB);
    	assertEquals(2, edgeWeightCB);
    }
    
    
}
