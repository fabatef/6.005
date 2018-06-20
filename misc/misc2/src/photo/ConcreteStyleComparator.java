/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.util.Comparator;

import photo.Style.Filter;

/**
 * TODO Specification
 * 
 * <p>PS2 instructions:
 * 
 * This class must implement the required ADT interface and MUST NOT depend on
 * ConcreteStyle or other representations.
 */
public class ConcreteStyleComparator implements Comparator<Style> {

    // TODO Fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * TODO Specification
     */
    public ConcreteStyleComparator(Filter filter) {
        throw new RuntimeException("ConcreteStyleComparator(Filter) unimplemented");
    }
    
    @Override
    public int compare(Style style1, Style style2) {
        throw new RuntimeException("compare(Style, Style) unimplemented");
    }
    
    @Override
    public boolean equals(Object obj) {
        throw new RuntimeException("equals(Object) unimplemented");
    }
    
    @Override
    public int hashCode() {
        throw new RuntimeException("hashCode() unimplemented");
    }
    
    @Override
    public String toString() {
        throw new RuntimeException("toString() unimplemented");
    }
}
