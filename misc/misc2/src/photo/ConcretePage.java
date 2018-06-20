/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.util.Iterator;

/**
 * TODO Specification
 * 
 * <p>PS2 instructions:
 * 
 * This class must implement the required ADT interface and MUST NOT depend on
 * photo or style representations.
 */
public class ConcretePage implements Page {
    
    // TODO Fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    // TODO Constructor
    
    @Override
    public PhotoAlbum album() {
        throw new RuntimeException("album() unimplemented");
    }

    @Override
    public int rows() {
        throw new RuntimeException("rows() unimplemented");
    }

    @Override
    public int photos(int row) throws IndexOutOfBoundsException {
        throw new RuntimeException("photos(int) unimplemented");
    }

    @Override
    public Iterator<Photo> iterator() {
        throw new RuntimeException("iterator() unimplemented");
    }

    @Override
    public String getTitle() {
        throw new RuntimeException("getTitle() unimplemented");
    }

    @Override
    public String setTitle(String title) {
        throw new RuntimeException("setTitle() unimplemented");
    }

    @Override
    public void insert(Photo photo, int row, int index) {
        throw new RuntimeException("insert(Photo, int, int) unimplemented");
    }

    @Override
    public Photo remove(int row, int index) {
        throw new RuntimeException("remove(int, int) unimplemented");
    }

    @Override
    public String toHTML() {
        throw new RuntimeException("toHTML() unimplemented");
    }
    
    // TODO toString
    
}
