package expressivo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Immutable data type.
 * An implementation of Expression representing nonnegative integers and floating-point numbers
 */

class Num implements Expression{
	
	private final double number;
	
	public Num(double n){
		
		this.number = n;		
	}
	
    // Abstraction function:
    //   represents a non-negative integer and floating point number
    // Representation invariant:
	//    number >=0
    // Safety from rep exposure:
    //   All fields are private, final and immutable.
	
	private void checkRep(){
		assert(number>=0);
	}
	
	@Override
	public String toString(){
		checkRep();
		return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Num)) { return false;}
		
		Num e= (Num) obj;
		checkRep();
		return e.number == number;
	}
	
	@Override
	public int hashCode(){
		checkRep();
		return new Double(number).hashCode();
	}
	
	@Override
	public Expression differentiate(String wrt){
		checkRep();
		return new Num(0);
	}
	
	
	@Override
	public double evaluate(Map<String, Double> envt){
		checkRep();
		return number;
	}
	
	@Override
	public boolean justNums(Map<String, Double> envt){
		checkRep();
		return true;
	}

	@Override
	public Expression simplify(Map<String, Double> envt) {
		checkRep();
		return new Num(number); 
		
	}
	
	
	
	
}