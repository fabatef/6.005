package expressivo;

import java.util.Map;

/**
 * Immutable data type.
 * 
 * An implementation of Expression representing a variable where a variable is a
 * case-sensitive nonempty string of letters
 */
class Var implements Expression{
	
	private final String variable;
	
	// Abstraction function:
    //   represents a variable 
    // Representation invariant:
	//    variable.length() > 0
	//    variable must only contain letters.
	// Safety from rep exposure:
    //   All fields are private, final and immutable.
	
	private void checkRep(){
		try {
			assert(variable.length()>0);
			assert(variable.matches("[A-Za-z]+"));
		}
		catch(AssertionError e){
			throw new IllegalArgumentException();
		}
	}
	
	public Var(String v){
		this.variable= v;
		checkRep();
	}
	
	@Override
	public String toString(){
		checkRep();
		return variable; 
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Var)) { return false;}
		
		Var e= (Var) obj;
		checkRep();
		return e.toString().equals(variable);
	}
	
	@Override
	public int hashCode(){
		checkRep();
		return variable.hashCode();
	}
		
	@Override
	public Expression differentiate(String wrt){
		if (wrt.equals(variable)) { 
			checkRep();
			return new Num(1);
			} else{
				checkRep();
				return new Num(0);
		}
	}
		
	@Override
	public boolean justNums(Map<String,Double> envt){
		checkRep();
		return envt.containsKey(variable);		
	}
	
	@Override  
	public double evaluate(Map<String,Double> envt){
		if(this.justNums(envt)){
		checkRep();
		return envt.get(variable);
		} else {
			throw new UnsupportedOperationException("The expression can't be evaluated");
		}
	}

	@Override
	public Expression simplify(Map<String, Double> envt) {
		if (this.justNums(envt)){
			checkRep();
			return new Num(envt.get(variable));
		} else{
			checkRep();
			return this;
		}
	}
}
