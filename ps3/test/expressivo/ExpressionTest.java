/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   testing strategy for toString()
	//     - when the expression is just a number
	//     - when the expression is just a variable
	//     - when the expression is a combination of a number and a variable
	//     - when the expression is a combination of expressions involving multiply and add
	//   testing strategy for equals() and hashcode()
	//      - two expressions that are just variables
	//      - two expressions that are just numbers
	//      - two expressions with the same sequence of operation, num and var appearance 
	//        parenthesized differently
	//  testing strategy for differentiate()
	//     - when the expression is just a number  wrt to different variables
	//     - when the expression is just a variable  wrt itself and a different variable
	//     - when the expression is a combination of a number and a variable wrt itself and a different variable
	//     - when the expression is a combination of expressions involving multiple variables
	//  testing strategy for simplify()
	//     - when the environment contains all the variables in the expression
	//     - when the environment contains some of the variables in the expression
	//     - when the environment contains none of the variables in the expression
	//     - when the expression is made of numbers only
	//     - when the environment is empty
	//     - when the expression is invalid
	
	
	    
	
	
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
// testing parse
    
    @Test
    public void testParse(){
    	Expression test= Expression.parse("(1.0+x)");
    	assertEquals(Expression.add(Expression.number(1), Expression.variable("x")),test);
    	Expression test2= Expression.parse("(1)*(x)");
    	assertEquals(Expression.multiply(Expression.number(1), Expression.variable("x")),test2);
    	Expression test3= Expression.parse("1*(1.00+x)");
    	assertEquals(Expression.multiply(Expression.number(1), Expression.add(Expression.number(1)
    			     , Expression.variable("x"))),test3);
    	
    }
    
//testing toString()
    
    //Partition 1: when the expression is just a number
    @Test
    public void testNumExptoString(){
    	Expression test= Expression.number(1.001);
    	
    	assertTrue(test.toString().equals(String.valueOf(1.001)));
    }
    
    //Partition 2: when the expression is just a variable
    @Test
    public void testVarExptoString(){
    	Expression test= Expression.variable("X");
    	
    	assertTrue(test.toString().equals("X"));
    }
    
    //Partition 3: when the expression is an add combination of num and var
    @Test
    public void testAddVarNumtoString(){
    	Expression comp1= Expression.variable("X");
    	Expression comp2= Expression.number(1.02);
    	
    	Expression test= Expression.add(comp1, comp2);
    	
    	//System.out.println(test.toString());
    	
    	assertTrue(test.toString().equals("(X+"+ String.valueOf(1.02)+")"));
    }
    
    //Partition 4: when the expression is a multiply combination of num and var
    @Test
    public void testMulVarNumtoString(){
    	Expression comp1= Expression.variable("X");
    	Expression comp2= Expression.number(1.001);
    	
    	Expression test= Expression.multiply(comp1, comp2);
    	
    	//System.out.println(test.toString());
    	
    	assertTrue(test.toString().equals("(X*"+String.valueOf(1.001)+")"));
    }
    
    //Partition 5: when the expression is a multiply combination of an expressions involving an mul and var
    @Test
    public void testMulExpWithVartoString(){
    	    	
    	Expression comp1= Expression.multiply(Expression.variable("X"), Expression.number(1.001));
    	
    	Expression test= Expression.multiply(comp1, Expression.variable("x"));
    	System.out.println(test.toString());
    	
    	assertTrue(test.toString().equals("((X*"+String.valueOf(1.001)+")*x)"));
    }
    
    
    //Partition 5: when the expression is a multiply combination of two expressions involving an add/mul
    @Test
    public void testMulExpstoString(){
    	    	
    	Expression comp1= Expression.multiply(Expression.variable("X"), Expression.number(1.001));
    	Expression comp2= Expression.add(Expression.variable("Y"), Expression.number(100));
    	
    	Expression test= Expression.multiply(comp1, comp2);
    	
    	
    	
    	assertTrue(test.toString().equals("((X*1.001)*(Y+100))"));
    }
    
    
    //testing equals() and hashCode()
    
    //partition 1: equality of two variables
    
    @Test
    public void testTwoVarsEquals(){
    	Expression test= Expression.variable("x");
    	Expression test1= Expression.variable("X");
    	
    	assertTrue(test.equals(test));
    	assertFalse(test.equals(test1)); 
    	
    }
    
    //partition 2: equality of two numbers
    
    @Test
    public void testTwoNumsEquals(){
    	Expression test= Expression.number(1.0000);
    	Expression test1= Expression.number(1);
    	
    	assertTrue(test.equals(test));
    	assertTrue(test.equals(test1));  
    	assertTrue(test.hashCode()==test1.hashCode());
    	
    }
    
    //partition 3: expressions parenthesized differently
    
    @Test
    public void testParenEquals(){
    	Expression test= Expression.parse("(1.0+x)");
    	Expression test2= Expression.parse("(1)+(x)");
    	Expression test3= Expression.parse("1.00+x");
     	
    	
    	assertTrue(test.equals(test2));
    	assertTrue(test2.equals(test3));
    	assertTrue(test.equals(test3));
    	assertTrue(test.hashCode()==test2.hashCode());
    	assertTrue(test2.hashCode()==test3.hashCode());
    	assertTrue(test.hashCode()==test3.hashCode());
    	
    	
    }
   
    
    //partition 3: expressions parenthesized differently
    
    @Test
    public void testParenEquals2(){
    	Expression test= Expression.parse("1+(x+3)");
    	Expression test2= Expression.parse("(1+x)+3");
    	
    	
    	assertFalse(test.equals(test2));    	
    	
    }
       
    
  //testing differentiate
    
    //Partition 1:  when the expression is just a number  wrt some variable
    @Test
    public void testNumDiff(){
    	Expression test= Expression.number(1.02);
  	  
    	assertEquals(Expression.number(0), test.differentiate("x"));
    }
    
    //Partition 2: when the expression is just a variable  wrt itself and a different variable
    @Test
    public void testVarDiff(){
    	Expression test= Expression.variable("X");
    	
    	assertEquals(Expression.number(1), test.differentiate("X"));
    	assertEquals(Expression.number(0), test.differentiate("x"));
    }
    
    //Partition 3: when the expression is an add combination of a number and a variable wrt itself and a different variable
    @Test
    public void testAddVarNumDff(){
    	Expression comp1= Expression.variable("X");
    	Expression comp2= Expression.number(1.02);
    	
    	Expression test= Expression.add(comp1, comp2);
    	
    	
    	assertEquals(Expression.add(Expression.number(1), Expression.number(0)),test.differentiate("X"));
    	assertEquals(Expression.add(Expression.number(0), Expression.number(0)), test.differentiate("x"));
    }
    
  //Partition 4: when the expression is an add combination of a number and a variable wrt itself and a different variable
    @Test
    public void testMulVarNumDff(){
    	Expression comp1= Expression.variable("X");
    	Expression comp2= Expression.number(1.02);
    	Expression test= Expression.multiply(comp1, comp2);
    	
    	Expression r01= Expression.multiply(comp1, Expression.number(0));
    	Expression r02= Expression.multiply(comp2,Expression.number(1));
    	Expression result0= Expression.add(r01,r02);
    	
    	Expression r11= Expression.multiply(comp1, Expression.number(0));
    	Expression r12= Expression.multiply(comp2,Expression.number(0));
    	Expression result1= Expression.add(r11,r12);
    	
    	
    	assertEquals(result0,test.differentiate("X"));
    	assertEquals(result1, test.differentiate("x"));
    }
    
    //Partition 5: when the expression is a multiply combination of an expressions involving an mul and var
    @Test
    public void testMultipleVarsDff(){
    	Expression comp1= Expression.add(Expression.variable("X"),Expression.number(1.02));
    	Expression comp2= Expression.add(Expression.variable("y"), Expression.number(2.040));
    
    	// ((X+1.02)*(y+2.040))
    	Expression test= Expression.multiply(comp1, comp2 );
    	
    	Expression r01= Expression.multiply(comp1, Expression.add(Expression.number(0), Expression.number(0)));
    	Expression r02= Expression.multiply(comp2,Expression.add(Expression.number(1), Expression.number(0)));
    	Expression result0= Expression.add(r01,r02);
    	
    	Expression r11= Expression.multiply(comp1, Expression.add(Expression.number(1), Expression.number(0)));
    	Expression r12= Expression.multiply(comp2, Expression.add(Expression.number(0), Expression.number(0)));
    	Expression result1= Expression.add(r11,r12);
    	
    	
    	assertEquals(result0,test.differentiate("X"));
    	assertEquals(result1, test.differentiate("y"));
    }
    
//testing Simplify
    
    @Test
    //Partition 1: when the environment contains all the variables in the expression
    public void testAllVarsInEnvt(){
    	Expression comp1= Expression.add(Expression.variable("X"),Expression.number(1));
    	Expression comp2= Expression.add(Expression.variable("y"), Expression.number(2));
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("X", 4.0);
    	envt.put("y", 1.0);
    
    	// ((X+1)*(y+2))
    	Expression test= Expression.multiply(comp1, comp2 ); 
    	
    	assertEquals(Expression.number(15), test.simplify(envt));
    }
    
    @Test
    //Partition 2: when the environment contains some of the variables in the expression 
    public void testSomeVarsInEnvt(){
    	Expression comp= Expression.multiply(Expression.variable("y"), Expression.number(2));
    	
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("X", 4.0);
    	
    	Map<String, Double> envt1= new HashMap<String,Double>();
    	envt1.put("y", 2.0);
    	
    	// ((X+(y*2))
    	Expression test= Expression.add(Expression.variable("X"),comp); 
    	
    	assertEquals(Expression.add(Expression.number(4),comp), test.simplify(envt));
    	assertEquals(Expression.add(Expression.variable("X"), Expression.number(4)),test.simplify(envt1));
    }
    
    @Test
    //Partition 3: when the environment contains none of the variables in the expression 
    public void testNoVarsInEnvt(){
    	Expression comp= Expression.add(Expression.variable("y"), Expression.number(2));
    	
    	
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("z", 4.0);
    	envt.put("xx", 3.0);
    	
    	// ((X+(y*2))
    	Expression test= Expression.add(Expression.variable("X"),comp);
    	
    	assertEquals(test, test.simplify(envt));
    
    }
    
    @Test
    //Partition 4: when expression contains no variables 
    public void testNoVarsInExp(){
    	Expression comp= Expression.multiply(Expression.number(3), Expression.number(2));
    	
    	Map<String, Double> envt= new HashMap<String,Double>();
    	envt.put("z", 4.0);
    	envt.put("xx", 3.0);
    	
    	// ((2+(3*2))
    	Expression test= Expression.add(Expression.number(2),comp); 
    	
    	assertEquals(Expression.number(8), test.simplify(envt));    
    }
    
    @Test
    //Partition 5: when the environment is empty 
    public void testEmptyEnvt(){
    	Expression comp= Expression.add(Expression.variable("y"), Expression.number(2));
    	
    	Map<String, Double> envt= new HashMap<String,Double>();

    	
    	// ((X+(y*2))
    	Expression test= Expression.add(Expression.variable("X"),comp); 
    	
    	assertEquals(test, test.simplify(envt));    
    }
    
    @Test(expected=IllegalArgumentException.class)
	//Partition 6: when the expression is invalid
    public void testInvalidExpression() {
    	Expression comp= Expression.add(Expression.variable("y/*/"), Expression.number(2));
    	
    	Map<String, Double> envt= new HashMap<String,Double>();


    	Expression test= Expression.add(Expression.variable("X"),comp); 
    	System.out.println(test.simplify(envt));

    }
    
  
  
  
  
    
}
