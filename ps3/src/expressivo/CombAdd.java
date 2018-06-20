package expressivo;

import java.util.Map;

/**
 * Immutable data type.
 * 
 * An implementation of Expression representing a polynomial expression gotten by adding
 * two Expression objects.
 */

class CombAdd implements Expression{
	
	private final Expression exp1;
	private final Expression exp2;
	
	
    // Abstraction function:
    //   represents an Expression formed by the addition of two other Expression Objects
    // Representation invariant:
    //   operator never changes from its initialized value '+'
	//   exp1 and exp2 are always some instances of Expression
    // Safety from rep exposure:
    //   All the fields are private, final and immutable.
	
	private void checkRep(){
		assert(exp1 instanceof Expression && exp2 instanceof Expression);	
	}
	
	public CombAdd (Expression x, Expression y){
		this.exp1 = x;
		this.exp2 = y;
		checkRep();
	}
	
	
	@Override
	public Expression differentiate(String wrt){
		checkRep();
		return new CombAdd(exp1.differentiate(wrt), exp2.differentiate(wrt));
	}
 
	/**
	 * @return format of the string returned is  (expression1 + expression2)
	 */
	@Override
	public String toString(){
		checkRep();
		return "("+ exp1.toString() + "+" + exp2.toString() + ")";		
	}
	
	@Override
	public boolean equals(Object obj){
		
		if (!(obj instanceof CombAdd)){ return false;}
		
		CombAdd e= (CombAdd) obj;
		
		checkRep();
		return this.exp1.equals(e.exp1) && this.exp2.equals(e.exp2);
		
	}
	
	@Override 
	public int hashCode(){
		checkRep();
		return exp1.hashCode() + exp2.hashCode();
	}
	
	@Override
	public boolean justNums(Map<String, Double> envt){
		checkRep();
		return exp1.justNums(envt) && exp2.justNums(envt);
	}
	
	@Override
	public double evaluate(Map<String, Double> envt){
		checkRep();
		if(exp1.justNums(envt) && exp2.justNums(envt)){
		checkRep();
		return exp1.evaluate(envt) + exp2.evaluate(envt);
		} else{
			checkRep();
			throw new UnsupportedOperationException("The expression can't be evaluated");
		}
	}

	@Override
	public Expression simplify(Map<String, Double> envt) {
		
		if(exp1.justNums(envt)&&exp2.justNums(envt)){
			double result= exp1.evaluate(envt) + exp2.evaluate(envt);
			checkRep();
			return new Num(result);
		}else{
			checkRep();
			return new CombAdd(exp1.simplify(envt), exp2.simplify(envt));
		}
	}
	
}
