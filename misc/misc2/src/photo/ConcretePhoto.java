/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.net.URL;

/**
 * TODO Specification
 * 
 * <p>PS2 instructions:
 * 
 * This class must implement the required ADT interface and MUST NOT depend on
 * ConcretePhotoAlbum, ConcretePage, ConcreteStyle, or other representations.
 */
public class ConcretePhoto implements Photo {
    
    // TODO Fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO Constructor
    
    @Override
    public Photo withStyle(Style style) {
        throw new RuntimeException("withStyle(Style) unimplemented");
    }
    
    @Override
    public Photo withoutStyle() {
        throw new RuntimeException("withoutStyle() unimplemented");
    }
    
    @Override
    public String toHTML() {
        throw new RuntimeException("toHTML() unimplemented");
    }
    
    // TODO Object contract
    
    // TODO toString
    
}
