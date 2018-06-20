/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

/**
 * TODO Specification
 * 
 * <p>PS2 instructions:
 * 
 * This class must implement the required ADT interface and MUST NOT depend on
 * photo or style representations.
 */
public class ConcretePhotoAlbum implements PhotoAlbum {
    
    // TODO Fields
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    @Override
    public Page addPage(String title) {
        throw new RuntimeException("addPage(String) unimplemented");
    }
    
    @Override
    public ListIterator<Page> iterator() {
        throw new RuntimeException("iterator() unimplemented");
    }
    
    @Override
    public void toHTML(File directory) throws IOException {
        throw new RuntimeException("toHTML(File) unimplemented");
    }

    @Override
    public void undo() throws UndoRedoException {
        throw new RuntimeException("undo() unimplemented");
    }

    @Override
    public void redo() throws UndoRedoException {
        throw new RuntimeException("redo() unimplemented");
    }
    
    // TODO toString
    
}
