/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
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
    	
    	assertEquals("A", testGraph.toString());
    }
    
    //testing strategy for edge:
    // -test all the methods of Edge on an Edge object.
    @Test
    public void testValidEdge(){
    	Edge<String> testEdge1= new Edge<String>("A","B", 1);
    	Edge<String> testEdge2= new Edge<String>("A","B", 4);
    	Edge<String> testEdge3= new Edge<String>("C","D", 5);
    	
    	assertEquals(1, testEdge1.getWeight());   	
    	assertEquals("B", testEdge1.getTarget());
    	assertEquals("A", testEdge1.getSource());
    	assertEquals("A --> B : 1", testEdge1.toString());
    	assertTrue(testEdge1.isSameEdge(testEdge2));
    	assertFalse(testEdge1.isSameEdge(testEdge3));
    	
    }
    	
    }
     

    

