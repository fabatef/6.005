/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add a constructor to this class or change
 * the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy for add
	//   - vertex:  already in the graph , not in the graph
	//   - Graph: mutated by set, mutated by add
	//   
	// Testing strategy for set
	//   - weight: 0, >0 , > existing edge-weight, < existing edge-weight, ==existing edge-weight
	//   - source node: in the graph, not in the graph
	//   - target node: in the graph, not in the graph
	//   - Graph: mutated by add, mutated by set
	//
	// Testing strategy for remove
	//   - vertex: in the graph with --> 0 , 1 , >1 edges
	//           : not in the graph 
	//           : the vertex to be removed is in a cycle 
	//   - Graph: mutated by set, mutated by add
	// 
	// Testing strategy for vertices
	//   - number of vertices in the graph: 0, 1 , >1
	//   - Graph: mutated by set, mutated by add
	//
	// Testing strategy for sources
	//   - target: in the graph with --> 0, 1, >1 source vertices
	//           : not in the graph
	//   - Graph: mutated by set, mutated by add
	// 
	// Testing strategy for targets
	//   - source: in the graph with --> 0, 1, >1 target vertices
	//           : not in the graph
	//   - Graph: mutated by set, mutated by add
	
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
// Tests for add
    
    @Test  //Partition 1: The vertex to be added is not in the graph
    public void vertexNotInGraphAdd() {
    	Graph<String> testGraph = emptyInstance();
        assertTrue(testGraph.add("A"));
        assertEquals(1, testGraph.vertices().size());  
    }
    
    @Test  //Partition 2: The vertex to be added is already in the graph
    public void vertexInGraphAdd() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	
    	assertFalse(testGraph.add("A"));
    	assertEquals(2, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"})));  
    }
    
//Tests for set
    
    @Test  //Partition 1: weight =1, source not in graph, target not in graph
    public void setAnEmptyGraph() {
    	Graph<String> testGraph = emptyInstance();
    	
    	
    	assertEquals(0, testGraph.set("A","B", 1));
    	//System.out.println(testGraph.vertices());
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	assertEquals(1, weight); 
    }
        
    @Test  //Partition 2: weight = 0, source in graph, target in graph
    public void setZeroWeight() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A","B", 1);
    	int result= testGraph.set("A", "B", 0);
    	
    	assertEquals(1, result);
    	assertEquals(2,testGraph.vertices().size() );
    }
    
    @Test  //Partition 3: weight = 0, source not in graph, target not in graph
    public void setEmptyGraphZeroWeight() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A","B", 0);
    	int result= testGraph.set("A", "B", 0);
    	
    	assertEquals(0, result);
    	assertEquals(0,testGraph.vertices().size() );
    }
    
    @Test  //Partition 4: weight > 1, source in graph, target in graph, existing edge-weight < weight
    public void setExistingEdgeSmallerWeight() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A","B", 1);
    	int result= testGraph.set("A", "B", 5);
    	
    	assertEquals(1, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	assertEquals(5, weight); 
    }
    
    @Test  //Partition 5: weight > 1, source in graph, target in graph, existing edge-weight > weight
    public void setExistingEdgeBiggerWeight() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A","B", 5);
    	int result= testGraph.set("A", "B", 1);
    	
    	assertEquals(5, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	assertEquals(1, weight); 
    }
    
    @Test  //Partition 6: weight = 1, source in graph, target in graph, existing edge-weight == weight
    public void setExistingEdgeEqualWeight() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A","B", 5);
    	int result= testGraph.set("A", "B", 5);
    	
    	assertEquals(5, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	//System.out.println(testGraph.toString());
    	assertEquals(5, weight); 
    }
    
    @Test  //Partition 7: weight > 1, source not in graph, target in graph
    public void setSourceNotInGraph() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.add("B");
    	int result= testGraph.set("A", "B", 5);
    	
    	assertEquals(0, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	assertEquals(5, weight); 
    }
    
    @Test  //Partition 8: weight > 1, source in graph, target not in graph
    public void setTargetNotInGraph() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.add("A");
    	int result= testGraph.set("A", "B", 5);
    	
    	assertEquals(0, result);
    	assertEquals(2,testGraph.vertices().size() );
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A", "B"}))); 
    	assertTrue(testGraph.targets("A").containsKey("B"));
    	int weight= testGraph.targets("A").get("B");
    	assertEquals(5, weight); 
    }
    
 // Tests for remove
    
    @Test  //Partition 1: The vertex to be removed is not in the graph
    public void vertexNotInGraphRemove() {
    	Graph<String> testGraph = emptyInstance();
    	
        assertFalse(testGraph.remove("A"));
        assertEquals(0, testGraph.vertices().size());  
    }
    
    @Test  //Partition 2: The vertex to be removed is already in the graph with zero edges
    public void vertexInGraphRemoveNoEdge() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	
    	assertTrue(testGraph.remove("B"));
    	assertEquals(1, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().contains("A"));  
    }
    
    @Test  //Partition 3: The vertex to be removed is already in the graph with one edge
    public void vertexInGraphRemoveOneEdge() {
    	//how can i make a graph object with pre-existing vertices and edges?
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	
    	assertTrue(testGraph.remove("A"));
    	assertEquals(1, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().contains("B"));  
    }
    
    @Test  //Partition 4: The vertex to be removed is already in the graph with >1 edge
    public void vertexInGraphRemoveMultipleEdges() {
    	
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	testGraph.set("A", "C", 3);
    	
    	assertTrue(testGraph.remove("A"));
    	assertEquals(2, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"B", "C"}))); 
    	assertEquals(0, testGraph.sources("B").size());
    	assertEquals(0, testGraph.sources("C").size());
    }
    
    @Test  //Partition 5: The vertex to be removed is already in the graph with >1 edge
    public void vertexInGraphWithCycles() {
    	
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	testGraph.set("A", "C", 3);
    	testGraph.set("B", "C", 2);
    	
    	assertTrue(testGraph.remove("A"));
    	assertEquals(2, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"B", "C"})));  
    	assertEquals(0, testGraph.sources("B").size());
    	assertEquals(1, testGraph.sources("C").size());
    }
    
//Test for vertices
    
    @Test //Partition 1: no vertices
    public void emptyGraphVertices(){
    	Graph<String> testGraph= emptyInstance();
    	
    	assertEquals(0, testGraph.vertices().size());
    }
    
    @Test //Partition 2: one vertex
    public void oneVertex(){
    	Graph<String> testGraph= emptyInstance();
    	testGraph.add("A");
    	
    	assertEquals(1, testGraph.vertices().size());
    }
    
    @Test  //Partition 3: nodes > 1
    public void multipleVertices() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	testGraph.set("A", "C", 3);
    	//System.out.println(testGraph.vertices()+"here");
    	
    	assertEquals(3, testGraph.vertices().size());
    	assertTrue(testGraph.vertices().containsAll(Arrays.asList(new String[] {"A","B", "C"})));  
    }
    
 //Tests for sources
    
    @Test  //Partition 1:  target in the graph with no sources
    public void noSource() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.add("A");
    	
    	assertEquals(0, testGraph.sources("A").size());  
    }
    
    @Test  //Partition 2:  target not in the graph 
    public void emptyGraphNoSource() {   //should it throw an exception?? 
    	Graph<String> testGraph = emptyInstance();
    	
    	assertEquals(0, testGraph.sources("A").size());  
    }
    
    @Test  //Partition 3:  target in the graph with one source
    public void oneSource() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	
    	assertEquals(1, testGraph.sources("B").size());  
    	assertEquals(1, testGraph.sources("B").keySet().size());
    	int edgeWeight = testGraph.sources("B").get("A");
    	assertEquals(1, edgeWeight);
    }
    
    @Test  //Partition 4:  target in the graph with multiple sources
    public void multipleSources() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	testGraph.set("C", "B", 2);
    	
    	int edgeWeightAB = testGraph.sources("B").get("A");
    	int edgeWeightCB = testGraph.sources("B").get("C");
    	
    	assertEquals(2, testGraph.sources("B").size());  
    	assertEquals(2, testGraph.sources("B").keySet().size());
    	assertEquals(1, edgeWeightAB);
    	assertEquals(2, edgeWeightCB);
    }
    
 //Tests for targets

    @Test  //Partition 1:  source in the graph with no targets
    public void noTarget() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.add("A");
    	
    	assertEquals(0, testGraph.targets("A").size());  
    }
    
    @Test  //Partition 2:  source not in the graph 
    public void emptyGraphNoTarget() {   //should it throw an exception?? 
    	Graph<String> testGraph = emptyInstance();
    	
    	assertEquals(0, testGraph.sources("A").size());  
    }
    
    @Test  //Partition 3:  source in the graph with one target
    public void oneTarget() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("A", "B", 1);
    	
    	assertEquals(1, testGraph.targets("A").size());  
    	assertEquals(1, testGraph.targets("A").keySet().size());
    	int edgeWeight = testGraph.targets("A").get("B");
    	assertEquals(1, edgeWeight);
    }
    
    @Test  //Partition 4:  source in the graph with multiple targets
    public void multipleTargets() {
    	Graph<String> testGraph = emptyInstance();
    	testGraph.set("B", "A", 1);
    	testGraph.set("B", "C", 2);
    	
    	int edgeWeightAB = testGraph.targets("B").get("A");
    	int edgeWeightCB = testGraph.targets("B").get("C");
    	
    	assertEquals(2, testGraph.targets("B").size());  
    	assertEquals(2, testGraph.targets("B").keySet().size());
    	assertEquals(1, edgeWeightAB);
    	assertEquals(2, edgeWeightCB);
    }
    
    
    
    
    
    
}
