package lineart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

public class LineSegmentTest {
    
    // Testing strategy:
    // 
    // Only partitioning the equals() and hashCode() ops here, but
    // LineSegment's other ops need partitioning and testing too.
    //
    // equals:
    //    start point is same or different
    //    end point is same or different
    //    color is same or different
    // Cover the "different" parts independently, rather than having a single pair that is different
    // in both x and y at once.
    //
    // hashCode:
    //    this.equals(that) but this!=that
    // Not much to test, since hashCode is very underdetermined.
    
    
    @Test // start, end, color all the same
    public void testEqualSameValues() {
        LineSegment seg1 = new LineSegment(1, 2, 3, 4, Color.black);
        LineSegment seg2 = new LineSegment(new Point(1, 2), new Point(3, 4), Color.black);
        assertTrue(seg1.equals(seg1)); // reflexive
        assertTrue(seg2.equals(seg2)); // reflexive
        assertTrue(seg1.equals(seg2)); // not just ==
        assertTrue(seg2.equals(seg1)); // symmetric
    }

    @Test // star different
    public void testEqualDifferentStart() {
        assertFalse(new LineSegment(8, 12, 4, 6, Color.red).equals(new LineSegment(9, 11, 4, 6, Color.red)));
    }

    @Test // y different
    public void testEqualDifferentEnd() {
        assertFalse(new LineSegment(-5, 0, -8, 0, Color.yellow).equals(new LineSegment(-5, 0, 16.5, 20, Color.yellow)));
    }

    @Test // color different
    public void testEqualDifferentColor() {
        assertFalse(new LineSegment(0, 0, 0, 0, Color.blue).equals(new LineSegment(0, 0, 0, 0, Color.green)));
    }

    @Test // x, y same
    public void testHashcodeSameValues() {
        LineSegment seg1 = new LineSegment(0, 100, 100, 0, Color.black);
        LineSegment seg2 = new LineSegment(new Point(0, 100), new Point(100, 0), Color.black);
        assertEquals(seg1.hashCode(), seg2.hashCode());
    }
}
