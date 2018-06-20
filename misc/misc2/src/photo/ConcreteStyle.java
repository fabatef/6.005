/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.util.Map;

/**
 * TODO Specification
 * 
 * <p>PS2 instructions:
 * 
 * This class must implement the required ADT interface and MUST NOT depend on
 * ConcreteStyleComparator or other representations.
 */
public class ConcreteStyle implements Style {
    
    // TODO Fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    @Override
    public boolean isOriginal() {
        throw new RuntimeException("isOriginal() unimplemented");
    }
    
    @Override
    public Map<String, String> getCSSFilterFunctions() {
        throw new RuntimeException("getCSSFilterFunctions() unimplemented");
    }
    
    @Override
    public String toCSS() {
        throw new RuntimeException("toCSS() unimplemented");
    }
    
    @Override
    public Style with(Filter filter, int value) {
        throw new RuntimeException("with(Filter, int) unimplemented");
    }
    
    @Override
    public Style without(Filter filter) {
        throw new RuntimeException("without(Filter) unimplemented");
    }
    
    @Override
    public Style multiplied(double factor) {
        throw new RuntimeException("multiplied(double) unimplemented");
    }
    
    // TODO Object contract
    
    // TODO toString
    
}
