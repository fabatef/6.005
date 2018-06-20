/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.io.File;
import java.io.IOException;
import java.util.ListIterator;

/**
 * A mutable photo album.
 * 
 * <ul>
 * <li>A photo album has zero or more pages of zero or more photos.
 * <li>Certain operations on photo albums and their pages are <em>undoable</em>,
 *     see {@link #undo} and {@link #redo}.
 * </ul>
 * 
 * <p>PS2 instructions:
 * 
 * This is a required ADT interface. It defines static creator methods, which
 * you must implement here, and instance methods, which you must implement in
 * the concrete implementation(s).
 * 
 * You MUST NOT weaken the provided specifications, and you MUST NOT change the
 * method signatures (names, parameter types, return types, and exceptions). You
 * MAY strengthen the provided specifications, and you MAY also add additional
 * methods, but you MUST NOT expose your rep when you write your strengthened
 * specs or write specs for your additional methods.
 */
public interface PhotoAlbum extends Iterable<Page> {
    
    ///////////////
    // Problem 3 //
    ///////////////
    
    /**
     * Create a new empty photo album.
     * 
     * @return a new album with no pages of photos
     */
    public static PhotoAlbum empty() {
        return new ConcretePhotoAlbum();
    }
    
    /**
     * Add an empty page to the end of this photo album.
     * 
     * <em>Undoable</em> (Problem 5).
     * 
     * @param title title of the page, must be a valid page title
     * @return the new page
     */
    public Page addPage(String title);
    
    /**
     * Return a list iterator over pages in this album.
     * 
     * The returned iterator may not support the optional <code>add</code>,
     * <code>remove</code>, or <code>set</code> operations, in which case they
     * will throw UnsupportedOperationException.
     * 
     * If the iterator supports the optional operations, attempting to add a
     * page that does not belong to this album will cause an
     * IllegalArgumentException.
     * 
     * Changes made to this album other than using the iterator, including
     * undo/redo, may invalidate the iterator, causing it to throw
     * ConcurrentModificationException on subsequent operations.
     * 
     * @see java.lang.Iterable#iterator()
     * @see java.util.ListIterator
     * @see java.lang.UnsupportedOperationException
     * @see java.lang.IllegalArgumentException
     * @see java.util.ConcurrentModificationException
     * 
     * @return a page list iterator
     */
    @Override public ListIterator<Page> iterator();
    
    ///////////////
    // Problem 4 //
    ///////////////
    
    /**
     * Save an HTML version of this album to disk.
     * <ul>
     * <li>For the <em>n</em>th page in the album, numbered from 0, creates a file
     *     <code>page<em>n</em>.html</code> in the given directory with the HTML for
     *     that page.
     * <li>For the album, creates a file <code>index.html</code> in the given
     *     directory with links to each page.
     *     Each link will be an <code>a</code> element with <code>href</code> of
     *     <code>page<em>n</em>.html</code> and the title of that page as the link
     *     text.
     *     The index file may include other HTML.
     * <li>Existing files with these names in the given directory are overwritten.
     * </ul>
     * 
     * @param directory directory in which to output the album,
     *                  directory.isDirectory() must be true
     * @throws IOException if an error occurs saving the album files
     */
    public void toHTML(File directory) throws IOException;
    
    ///////////////
    // Problem 5 //
    ///////////////
    
    /**
     * Revert the most recent <em>undoable</em> operation that is not already undone.
     * The state of this album returns to its state before the operation.
     * 
     * @throws UndoRedoException if there is no operation to undo
     */
    public void undo() throws UndoRedoException;
    
    /**
     * Reapply the most recently undone <em>undoable</em> operation,
     * if the only operations since that operation have been {@link #undo}, {@link #redo},
     * and/or operations that do not mutate the state of this album.
     * The state of this album returns to its state after the operation.
     * 
     * @throws UndoRedoException if there is no operation to redo
     */
    public void redo() throws UndoRedoException;
    
    /**
     * Thrown when an undo/redo operation cannot be performed.
     */
    public static class UndoRedoException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        
        /**
         * Construct a new undo/redo exception with the given message.
         * 
         * @param message exception detail message
         */
        public UndoRedoException(String message) {
            super(message);
        }
    }
}
