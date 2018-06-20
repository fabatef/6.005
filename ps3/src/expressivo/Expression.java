/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import java.util.List;
import java.util.Map;
import java.util.Stack;



import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;


/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 */
public interface Expression {
	// Expression = Num(double: X) + Var(String: y) + CombMul(Expression: left, Expression: right)
	//              + CombAdd(Expression: left, Expression: right)
	
	/**
	 * Creates a number  
	 * 
	 * @param x the value of the number to be represented as an Expression, must be >=0
	 * @return a new number Expression object
	 */
	public static Expression number(double x){  
		return new Num(x);
	}
	
	/**
	 * Creates a variable  
	 * 
	 * @param x a non-empty string of letters to be represented as an Expression
	 * @return a new variable Expression object
	 */
	public static Expression variable(String x){  
		return new Var(x);
	}
	
	
	/**
	 * Creates a polynomial representing the multiplication of two expressions. This multiplication is
	 * not associative or commutative.
	 * 
	 * @param x1 first expression in the multiplication
	 * @param x2 second expression in the multiplication
	 * 
	 * @return a new Expression object representing the polynomial multiplication of its parameters
	 */
	public static Expression multiply(Expression x1, Expression x2){
		return new CombMul(x1, x2);
	}
	
	/**
	 * Creates a polynomial representing the addition of two expressions. This addition is
	 * not associative or commutative.
	 * 
	 * @param x1 first expression in the addition
	 * @param x2 second expression in the addition
	 * 
	 * @return a new Expression object representing the polynomial addition of its parameters
	 */
	public static Expression add(Expression x1, Expression x2){ //
		return new CombAdd(x1, x2);
	}
	
    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param obj any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
	public static Expression parse(String input) {
		try{
			CharStream stream = new ANTLRInputStream(input);
			ExpressionLexer lexer = new ExpressionLexer(stream);
			lexer.reportErrorsAsExceptions();
			TokenStream tokens = new CommonTokenStream(lexer);

			// Make a parser whose input comes from the token stream produced by the lexer.
			ExpressionParser parser = new ExpressionParser(tokens);
			parser.reportErrorsAsExceptions();

			 
			MakeExpression expMaker = new MakeExpression();
			new ParseTreeWalker().walk(expMaker, parser.root());
			
			return expMaker.getExpression();
			}
		catch(Exception e){
			
			throw new IllegalArgumentException("Invalid inputs");
		}
	}
	
    /**
     * Calculates the differentiated value of an expression
     * 
     * @param wrt: the differentiation takes place with respect to this string input
     * @return a new differentiated Expression object 
     */
    public Expression differentiate(String wrt);  
    
    /**
     * Creates a simplified version of the expression. The expression if fully simplified only when 
     * all its components are numbers. Otherwise, the variables in the expression that are also present
     * in the environment are replaced with their respective values. If the replaced numbers happen to be 
     * in the same grouping(formed by multiply or add), they will be simplified. 
     * 
     * @param envt a hashmap containing a mapping between variables and their respective values
     * @return a simplified Expression object
     * @throws IllegalArgumentException if the expression is invalid
     */
    public  Expression simplify(Map<String, Double> envt); 
    
    
    /**
     * Evaluates the value of an Expression with no variables
     * 
     * @param envt a hashmap containing a mapping between variables and their respective values
     * @return the simplified number Expression object after the variables in the expression 
     *         have been replaced with their respective values in envt
     * @throws UnsupportedOperation Exception for Expressions containing variables
     */
    public double evaluate(Map<String, Double> envt);
    
    /**
     * Checks if the Expression has no variables in it
     * 
     * @param envt a hashmap containing a mapping between variables and their respective values 
     * @return true if the Expression has no variables in it after they have been replaced with their
     *         respective values in envt
     */
    public boolean justNums(Map<String, Double> envt);
    
}

/** Make an Expression value from a parse tree. */
class MakeExpression implements ExpressionListener {
    private Stack<Expression> stack = new Stack<>();
    // Invariant: stack contains the Expression value of each parse subtree 
    // that has been fully-walked so far, but whose parent 
    // has not yet been exited by the walk. The stack is ordered 
    // by recency of visit, so that the top of the stack is the 
    // Expression for the most recently walked subtree.
    //
    // At the start of the walk, the stack is empty, because 
    // no subtrees have been fully walked.
    //
    // Whenever a node is exited by the walk, the Expression values of 
    // its children are on top of the stack, in order with the last
    // child on top.  To preserve the invariant, we must pop 
    // those child Expression values from the stack, combine them with the
    // appropriate Expression producer, and push back an Expression value 
    // representing the entire subtree under the node.
    //
    // At the end of the walk, after all subtrees have been walked 
    // and the root has been exited, only the entire tree satisfies 
    // the invariant's "fully walked but parent not yet exited" property,
    // so the top of the stack is the Expression of the entire parse tree.
    
    /**
     * Returns the Expression constructed by this object.
     * Requires that this object has completely walked over a Expression parse tree 
     * using ParseTreeWalker.
     * @return Expression for the Expression parse tree that was walked
     */
    public Expression getExpression() {
        return stack.get(0);
    }
    
    @Override public void exitRoot(ExpressionParser.RootContext context) {
        // do nothing, root has only one child so its value is 
        // already on top of the stack
    }

    @Override public void exitSum(ExpressionParser.SumContext context) {  
        // matched product ('+' product)* 
        List<ExpressionParser.ProductContext> products = context.product();
        assert stack.size() >= products.size();
        
        // the pattern above always matches at least 1 product
        assert products.size() >= 1;  
        
        // pop the last child product from the stack 
        Expression exp = stack.pop();
       
        
        // pop the older child product and add the sum on the stack
        for (int i = 1; i < products.size(); ++i) {
        	
           exp = new CombAdd(stack.pop(), exp);
        }
        
        // the result is this subtree's Sum
        stack.push(exp);
    }
    
    @Override public void exitProduct (ExpressionParser.ProductContext context) {   //??
        // matched addend ('*' addend)*
        List<ExpressionParser.AddendContext> addends = context.addend();
        assert stack.size() >= addends.size();
        
        // the pattern above always matches at least 1 addend
        assert addends.size() >= 1;  
        
        // pop the last addend from the stack 
        Expression exp = stack.pop();
        
        
        // pop the older child addend and add the product on the stack
        for (int i = 1; i < addends.size(); ++i) {
            exp = new CombMul(stack.pop(), exp);
        }
        
        // the result is this subtree's Product
        stack.push(exp);
    }

    @Override public void exitAddend(ExpressionParser.AddendContext context) {
    	if (context.NUMBER() != null) {
            // matched the NUMBER alternative
            double num = Double.valueOf(context.NUMBER().getText());
            Expression exp = Expression.number(num);
            stack.push(exp);
        } else if(context.ALPHA() != null){
        	// matched the ALPHA alternative
        	Expression exp= new Var(context.ALPHA().getText());
        	stack.push(exp);
        } else {
            // matched the '(' sum ')' / '(' product ')' alternative
            // do nothing, because sum/product's value is already on the stack
        }
    }
    

    // don't need these here, so just make them empty implementations
    @Override public void enterRoot(ExpressionParser.RootContext context) { }
    @Override public void enterSum(ExpressionParser.SumContext context) { }
    @Override public void enterAddend(ExpressionParser.AddendContext context) { }
	@Override public void enterProduct(ExpressionParser.ProductContext context) { } 

    @Override public void visitTerminal(TerminalNode terminal) { }
    @Override public void enterEveryRule(ParserRuleContext context) {
    	//System.err.println("entering" + context.getText()+ ", stack is" + stack);
    }
    @Override public void exitEveryRule(ParserRuleContext context) {
    	//System.err.println("exiting"+ context.getText()+ ", stack is" + stack);
    }
    @Override public void visitErrorNode(ErrorNode node) { }

}








