/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.util.Iterator;

/**
 * A mutable 2D arrangement of photos, belonging to a particular album.
 * 
 * <ul>
 * <li>A page has zero or more rows of photos.
 * <li>Each row has one or more photos. Rows may not be empty.
 * <li>Page titles may contain any standard English-language characters,
 *     punctuation, and spaces, except that they may not contain characters that
 *     require special escaping in HTML: '<', '>', '"', or '&'. They may not
 *     contain newlines.
 * <li>Certain operations are <em>undoable</em> via the page's photo album,
 *     see {@link PhotoAlbum#undo} and {@link PhotoAlbum#redo}.
 * </ul>
 * 
 * <p>PS2 instructions:
 * 
 * This is a required ADT interface. It defines instance methods which you must
 * implement in the concrete implementation(s).
 * 
 * You MUST NOT weaken the provided specifications, and you MUST NOT change the
 * method signatures (names, parameter types, return types, and exceptions). You
 * MAY strengthen the provided specifications, and you MAY also add additional
 * methods, but you MUST NOT expose your rep when you write your strengthened
 * specs or write specs for your additional methods.
 */
public interface Page extends Iterable<Photo> {
    
    ///////////////
    // Problem 3 //
    ///////////////
    
    /**
     * Album of this page.
     * 
     * @return the photo album to which this page belongs.
     */
    public PhotoAlbum album();
    
    /**
     * Rows of photos on this page.
     * 
     * @return current number of rows of photos on this page
     */
    public int rows();
    
    /**
     * Photos in a row.
     * 
     * @param row row number
     * @return current number of photos in the given row
     * @throws IndexOutOfBoundsException if row is not 0 <= row < current number of rows
     */
    public int photos(int row) throws IndexOutOfBoundsException;
    
    /**
     * Return an iterator over photos on this page.
     * 
     * Will return photos in order by row, in order by position within row.
     * 
     * The returned iterator may not support the optional <code>remove</code>
     * operation, in which case it will throw UnsupportedOperationException.
     * 
     * Changes made to this page other than using the iterator, including
     * undo/redo, may invalidate the iterator, causing it to throw
     * ConcurrentModificationException on subsequent operations.
     * 
     * @see java.lang.Iterable#iterator()
     * @see java.util.Iterator
     * @see java.lang.UnsupportedOperationException
     * @see java.util.ConcurrentModificationException
     * 
     * @return a photo iterator
     */
    @Override public Iterator<Photo> iterator();
    
    /**
     * Title of this page.
     * 
     * @return current title
     */
    public String getTitle();
    
    /**
     * Set the title of this page.
     * 
     * <em>Undoable</em> via this page's album (Problem 5).
     * 
     * @param title new title, must be a valid page title
     * @return old title
     */
    public String setTitle(String title);
    
    /**
     * Insert a photo into the page.
     * 
     * To insert into a new row, use row = current number of rows.
     * To insert at the end of a row, use index = current number of photos in the row.
     * 
     * <em>Undoable</em> via this page's album (Problem 5).
     * 
     * @param photo photo to insert
     * @param row row in which to insert the photo,
     *            0 <= row <= current number of rows
     * @param index position in the row at which to insert the photo,
     *              0 <= index <= current number of photos in the row
     */
    public void insert(Photo photo, int row, int index);
    
    /**
     * Remove a photo from the page.
     * 
     * <em>Undoable</em> via this page's album (Problem 5).
     * 
     * @param row row of the photo to remove,
     *            0 <= row < current number of rows
     * @param index position in the row of the photo to remove,
     *              0 <= index < current number of photos in the row
     * @return the removed photo
     */
    public Photo remove(int row, int index);
    
    ///////////////
    // Problem 4 //
    ///////////////
    
    /**
     * Return HTML to render this photo album page.
     * <ul>
     * <li>The HTML will include the following CSS in a <code>style</code> tag:
     *     img { box-sizing: border-box; border: 10px solid transparent; }
     * <li>It may include other CSS that does not change how photos are displayed.
     * <li>The HTML will include a <code>h1</code> tag with the page title.
     * <li>Each row will be enclosed in a <code>div</code> element.
     * <li>Each photo will be HTML code according to {@link Photo#toHTML()},
     *     except that the <code>img</code> tag will also include a
     *     <code>width</code> attribute with a percent value equal to
     *     (1 / (number of photos in the row)) in quotation marks.
     * <li>No whitespace will appear between photos or between photos and
     *     enclosing <code>div</code>s.
     * </ul>
     * @return HTML for this page
     */
    public String toHTML();
    
}
