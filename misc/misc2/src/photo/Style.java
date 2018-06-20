/* Copyright (c) 2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package photo;

import java.util.Comparator;
import java.util.Map;

/**
 * An immutable style defined by image filter function parameters.
 * 
 * <ul>
 * <li>All filter functions take an integer-valued parameter.
 * <li>Each filter function may define minimum and/or maximum parameter values,
 *     or the minimum and/or maximum may be unbounded.
 * <li>For all filters, a parameter value of 0 is the default value, specifying
 *     no change to the appearance of the image.
 * <li>All filters scale linearly in their parameter.
 * <li>Style provides methods to obtain CSS representations. Note that CSS
 *     defines different domains for filter function parameters.
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
public interface Style {
    
    /**
     * Image filters applied by a style.
     * 
     * <p>PS2 instructions:
     * 
     * You MAY add additional enumeration values, but you MUST NOT change the
     * required specifications.
     */
    public static enum Filter {
        /** Sepia filter from 0 (original color) to 100 (sepia tones only).
         *  Corresponds to CSS filter function <code>sepia</code>. */
        SEPIA,
        
        /** Brightness filter where -100 is no brightness (completely black),
         *  0 is original image, and larger values are brighter.
         *  Corresponds to CSS filter function <code>brightness</code>. */
        BRIGHTNESS,
        
        /** Contrast filter where -100 is no contrast (completely gray),
         *  0 is original image, and larger values are more contrast.
         *  Corresponds to CSS filter function <code>contrast</code>. */
        CONTRAST,
        
        /** Saturation filter where -100 is no saturation (grayscale),
         *  0 is original image, and larger values are more saturation.
         *  Corresponds to CSS filter function <code>saturate</code>. */
        SATURATE,
        
        /** Gaussian blur filter from 0 (no blur) to 100 (blur radius 10 pixels).
         *  Corresponds to CSS filter function <code>blur</code>. */
        BLUR
    }
    
    /**
     * Obtain a style that specifies no appearance modifications.
     * 
     * @return original style
     */
    public static Style original() {
        return new ConcreteStyle();
    }
    
    /**
     * Obtain an immutable style comparator.
     * 
     * @param filter filter value by which to order styles
     * @return a comparator that imposes an order by the value of the given filter
     */
    public static Comparator<Style> comparator(Filter filter) {
        return new ConcreteStyleComparator(filter);
    }
    
    /**
     * Return true if and only if this style does not specify any appearance modifications.
     * 
     * @return true iff this is "original" style
     */
    public boolean isOriginal();
    
    /**
     * Return filter functions and parameters to render this style using a CSS
     * <code>filter</code> property.
     * 
     * @return map from CSS filter function names to input values for those functions.
     *         Keys in the map is are lower-case filter function names, e.g. "contrast".
     *         Values are the input to the function, e.g. "50%".
     *         The map will not include entries where the value is the default for that
     *         function, e.g. "contrast" of "1" or "100%".
     */
    public Map<String, String> getCSSFilterFunctions();
    
    /**
     * Return a CSS <code>filter</code> property to render this style.
     * May also include a <code>-webkit-filter</code> property or other
     * browser-specific synonyms for <code>filter</code>. Multiple properties will
     * be separated and terminated by semicolons.
     * 
     * @return CSS for this style
     */
    public String toCSS();
    
    /**
     * Add an appearance modification.
     * 
     * @param filter style filter to change
     * @param value new value for that filter
     * @return a style identical to this style except that the given filter has
     *         the given value, clamped to the filter's domain
     */
    public Style with(Filter filter, int value);
    
    /**
     * Remove an appearance modification.
     * 
     * @param filter style filter to reset
     * @return a style identical to this style except that the given filter has
     *         the default value
     */
    public Style without(Filter filter);
    
    /**
     * Scale this appearance modification.
     * 
     * @param factor multiplication factor
     * @return a style where every filter in the result has a new value equal to
     *         (old value) * factor, truncated to integer values, clamped to the
     *         filter's domain
     */
    public Style multiplied(double factor);
}
