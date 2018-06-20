package lineart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A mutable line drawing.
 */
public class LineArt {

    private final Set<LineSegment> segments = new HashSet<>();

    // Abstraction function:
    //      represents a line drawing made by drawing the segments, in any order.
    //      Where lines overlap, the pixels are an equal mix of the colors of those lines.
    // Rep invariant:
    //      true
    // Safety from rep exposure:
    //      all fields are private. The only operation that takes or returns a mutable type is
    //      lines(), which makes a copy of segments (and even returns it as a different type, so
    //      that aliasing can't happen accidentally).

    // assert rep invariant, which in this case is just 6.005-bog-standard "no nulls"
    private void checkRep() {
        assert segments != null;
        assert !segments.contains(null); // alas, HashSet does allow nulls to be added, so check for them
    }
    
    /**
     * Make an empty line drawing.
     */
    public LineArt() {        
    }
    
    /**
     * Add a line segment to this drawing.  No effect if the segment is already in this drawing.
     * @param segment to add
     */
    public void add(LineSegment segment) {
        segments.add(segment);
    }
    
    /**
     * Remove a line segment from this drawing.  No effect if the segment isn't found in this drawing.
     * @param segment to remove
     */
    public void remove(LineSegment segment) {
        segments.remove(segment);
    }

    /**
     * @return a list of line segments in this drawing, in arbitrary order
     */
    public List<LineSegment> lines() {
        return new ArrayList<>(segments);
    }
    
    /**
     * Compute the total length of this line drawing.
     * 
     * @return total length of all the line segments in the drawing
     */
    public double length() {
        double sum = 0;
        for (LineSegment segment : segments) {
            sum += segment.length();
        }
        return sum;
    }

}
