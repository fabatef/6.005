/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   -empty Graph, a graph with multiple edges and vertices, a graph with one node
    
    @Test //Partition 1: empty graph
    public void emptyGraphToString(){
    	Graph<String> testGraph= emptyInstance();
    	
    	assertEquals("This is an empty graph", testGraph.toString());
    }
    
    @Test //Partition 2: graph with multiple edges and vertices
    public void occupiedGrapToString(){
    	Graph<String> testGraph= emptyInstance();
    	
    	testGraph.set("A", "B", 1);
    	testGraph.set("A", "C", 3);
    	testGraph.set("B", "C", 4);
    	
    	StringBuilder result= new StringBuilder();
    	result.append("A --> B : 1"); result.append(System.getProperty("line.separator"));
    	result.append("A --> C : 3"); result.append(System.getProperty("line.separator"));
    	result.append("B --> C : 4"); result.append(System.getProperty("line.separator"));
    	
    	
    	assertEquals(result.toString(),testGraph.toString());
    }
    
    @Test //Partition 3: graph with one node
    public void oneNodeToString(){
    	Graph<String> testGraph= emptyInstance();
    	testGraph.add("A");
    	
    	//System.out.println(testGraph.toString());
    	
    	assertEquals("A", testGraph.toString());
    }
    	
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //  - vertex with multiple roots and leaves
    //  -vertex with cyclic roots and leaves
    //  - vertex with leaves only
    //  - vertex with roots only
    //  - vertex with no leaves and no roots
    
    @Test // cyclic vertex
    public void testCylicVertex(){
    	Vertex<String> testV= new Vertex<String>("A");
    	testV.addLeaf("B", 2);
    	testV.addLeaf("C", 5);
    	testV.addRoot("C", 3);
    	
    	assertEquals(2, testV.getLeaves().size());
    	assertEquals(1, testV.getRoots().size());
    	assertTrue(testV.getLeaves().keySet().containsAll(Arrays.asList("B","C")));
    	assertTrue(testV.getRoots().keySet().containsAll(Arrays.asList("C")));
    	
    }
    
    @Test // multiple roots and leaves
    public void testMultipleRootsandLeaves(){
    	Vertex<String> testV= new Vertex<String>("A");
    	testV.addLeaf("B", 2);
    	testV.addLeaf("C", 5);
    	testV.addRoot("D", 3);
    	
    	
    	assertEquals(2, testV.getLeaves().size());
    	assertEquals(1, testV.getRoots().size());
    	assertTrue(testV.getLeaves().keySet().containsAll(Arrays.asList("B","C")));
    	assertTrue(testV.getRoots().keySet().containsAll(Arrays.asList("D")));
    	assertTrue(testV.hasRoot("D"));
    	
    }
    
    @Test // has only Roots
    public void testRootsOnly(){
    	Vertex<String> testV= new Vertex<String>("A");
    	testV.addRoot("B", 2);
    	testV.addRoot("C", 5);
    	testV.addRoot("D", 3);
    	
    	
    	assertEquals(0, testV.getLeaves().size());
    	assertEquals(3, testV.getRoots().size());
    	assertTrue(testV.getRoots().keySet().containsAll(Arrays.asList("D", "C", "B")));
    	assertTrue(testV.hasRoot("C"));
    	assertEquals(5, testV.getWeightRoot("C"));  //Check this again
    	
    }
    
    
    
    // TODO tests for operations of Vertex
    
}
