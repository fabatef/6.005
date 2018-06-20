/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.net.URL;

/**
 * An immutable photo defined by an image URL and style modifications.
 * 
 * <ul>
 * <li>Photos are defined by URL and style only, not by image data retrieved
 *     from the URL.
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
public interface Photo {
    
    /**
     * Create a new photo with no appearance modifications.
     * 
     * @param url URL of the photo
     * @return new photo
     */
    public static Photo create(URL url) {
        throw new RuntimeException("create(URL) unimplemented");
    }
    
    /**
     * Apply a style.
     * 
     * @param style style to apply
     * @return a photo identical to this photo except with the given style
     *         instead of the current style
     */
    public Photo withStyle(Style style);
    
    /**
     * Remove style.
     * 
     * @return a photo identical to this photo except without any appearance
     *         modifications
     */
    public Photo withoutStyle();
    
    /**
     * Return HTML to render this photo.
     * <ul>
     * <li>Returns a single <code>img</code> tag.
     * <li>Will include a <code>src</code> attribute with the URL in quotation
     *     marks, e.g. <code>src="http://..."</code>.
     * <li>If the style includes appearance modifications,
     *     will include a <code>style</code> attribute with the style CSS in
     *     quotation marks.
     * <li>Will not include any additional attributes.
     * </ul>
     * 
     * @return HTML for this photo
     */
    public String toHTML();
}
