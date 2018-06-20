/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the static methods of Commands.
 */
public class CommandsTest {

    // Testing strategy 
	//
    //testing differentiate
	//     - when the expression is just a number  wrt some variable
	//     - when the expression is just a variable  wrt itself and a different variable
	//     - when the expression is a combination of a number and a variable wrt itself and a different variable
	//     - when the expression is a combination of expressions involving multiple variables
    //     - when invalid variable is passed
    //     - when invalid expression is passed
	//  testing strategy for simplify()
	//     - when the environment contains all the variables in the expression
	//     - when the environment contains some of the variables in the expression
	//     - when the environment contains none of the variables in the expression
	//     - when the expression is made of numbers only
	//     - when invalid strings are passed in the hashmap
	//     - when invalid expressions are passed
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    


    // testing differentiate()
    

    //Partition 1:  when the expression is just a number  wrt some variable
    @Test
    public void testNumDiff(){
     	
    	assertEquals(Expression.number(1).toString(), Commands.differentiate("x", "x"));
    }
    
    //Partition 2: when the expression is just a variable  wrt itself and a different variable
    @Test
    public void testVarDiff(){
    	    	
    	assertEquals(Expression.number(1).toString(), Commands.differentiate("X", "X"));
    	assertEquals(Expression.number(0).toString(), Commands.differentiate("X","x"));
    }
    
    //Partition 3: when the expression is an add combination of a number and a variable wrt itself and a different variable
    @Test
    public void testAddVarNumDff(){

    	assertEquals(Expression.add(Expression.number(1), Expression.number(0)).toString(),
    			     Commands.differentiate("X+1.02","X"));
    	assertEquals(Expression.add(Expression.number(0), Expression.number(0)).toString(), 
    			     Commands.differentiate("X+1.02","x"));
    }
    
  //Partition 4: when the expression is an add combination of a number and a variable wrt itself and a different variable
    @Test
    public void testMulVarNumDff(){
    	Expression comp1= Expression.variable("X");
    	Expression comp2= Expression.number(1.02);
    	
    	Expression r01= Expression.multiply(comp1, Expression.number(0));
    	Expression r02= Expression.multiply(comp2,Expression.number(1));
    	Expression result0= Expression.add(r01,r02);
    	
    	Expression r11= Expression.multiply(comp1, Expression.number(0));
    	Expression r12= Expression.multiply(comp2,Expression.number(0));
    	Expression result1= Expression.add(r11,r12);
    	
    	
    	assertEquals(result0.toString(), Commands.differentiate("(X*(1.02))","X"));
    	assertEquals(result1.toString(), Commands.differentiate("X*1.02","xo"));
    }
    
    //Partition 5: when the expression is a multiply combination of an expressions involving an mul and var
    @Test
    public void testMultipleVarsDff(){
    	Expression comp1= Expression.add(Expression.variable("X"),Expression.number(1.02));
    	Expression comp2= Expression.add(Expression.variable("y"), Expression.number(2.040));
    
    	// ((X+1.02)*(y+2.040))
    	
    	
    	Expression r01= Expression.multiply(comp1, Expression.add(Expression.number(0), Expression.number(0)));
    	Expression r02= Expression.multiply(comp2,Expression.add(Expression.number(1), Expression.number(0)));
    	Expression result0= Expression.add(r01,r02);
    	
    	Expression r11= Expression.multiply(comp1, Expression.add(Expression.number(1), Expression.number(0)));
    	Expression r12= Expression.multiply(comp2, Expression.add(Expression.number(0), Expression.number(0)));
    	Expression result1= Expression.add(r11,r12);
    	
    	
    	assertEquals(result0.toString(), Commands.differentiate("((X+1.02)*(y+2.040))", "X"));
    	assertEquals(result1.toString(), Commands.differentiate("((X+1.02)*(y+2.040))","y"));
    }
    
    //Partition 6: when invalid variable is passed
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidVar() {
    	Commands.differentiate("X+1.02","");
    }
   
 //Partition 7: when invalid expression is passed
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidExp() {
    	Commands.differentiate("X90+1.02","x");
    }
    

//Testing simplify()
    
    @Test
    //Partition 1: when the environment contains all the variables in the expression
    public void testAllVarsInEnvt(){
    
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("X", 4.0);
    	envt.put("y", 1.0);
    
    	// ((X+1)*(y+2))
    	
    	assertEquals(Expression.number(15).toString(), Commands.simplify("((X+1)*(y+2))", envt));
    }
    
    @Test
    //Partition 2: when the environment contains some of the variables in the expression 
    public void testSomeVarsInEnvt(){
    	Expression comp= Expression.multiply(Expression.variable("y"), Expression.number(3));
    	
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("X", 4.0);
    	
    	Map<String, Double> envt1= new HashMap<String,Double>();
    	envt1.put("y", 2.0);
    	
    	// ((X+(y*3))
    	String result= Expression.add(Expression.number(4),comp)
    			       .toString();
    	String result2= Expression.add(Expression.variable("X"), Expression.number(6))
    			       .toString();
    	
    	assertEquals(result, Commands.simplify("X+(y*3)",envt));
    	assertEquals(result2,Commands.simplify("X+y*3",envt1));
    }
    
    @Test
    //Partition 3: when the environment contains none of the variables in the expression 
    public void testNoVarsInEnvt(){
    	Expression comp= Expression.multiply(Expression.variable("y"), Expression.number(2));
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("z", 4.0);
    	envt.put("xx", 3.0);
    	
    	// ((X+(y*2))
    	String result= Expression.add(Expression.variable("X"),comp).toString(); 
    	
    	assertEquals(result, Commands.simplify("(X+(y*2))",envt));
    
    }
    
    @Test
    //Partition 4: when the expression has no variables 
    public void testNoVarsInExp(){
    	
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("z", 4.0);
    	envt.put("xx", 3.0);
    	
    	// ((2+(3*2))
    	String result= Expression.number(8).toString(); 
    	
    	assertEquals(result, Commands.simplify("2+3*2",envt));    
    }
    
    //Partition 6: when invalid variable is passed in the map
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidVarinEnvt() {
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("", 4.0);
    	envt.put("x x", 3.0);
    	
    	Commands.simplify("X+1.02",envt);
    }
   
 //Partition 7: when invalid expression is passed
    
    @Test(expected=IllegalArgumentException.class)
    public void testInvalidExpression() {
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("z", 4.0);
    	envt.put("xx", 3.0);
    	
    	Commands.simplify("X+1.02))",envt);
    }
    
}
