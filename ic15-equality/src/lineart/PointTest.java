package lineart;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

    // Testing strategy:
    // 
    // Only partitioning the equals() and hashCode() ops here, but
    // Point's other ops need partitioning and testing too.
    //
    // equals:
    //    x is same or different
    //    y is same or different
    // Cover the "different" parts independently, rather than having a single pair that is different
    // in both x and y at once.
    //
    // hashCode:
    //    this.equals(that) but this!=that
    // Not much to test! hashCode is very underdetermined.
   
    
    @Test // x, y same
    public void testEqualSameValues() {
        Point p1 = new Point(3, 5);
        Point p2 = new Point(3, 5);
        assertTrue(p1.equals(p1)); // reflexive
        assertTrue(p1.equals(p2)); // not just ==
        assertTrue(p2.equals(p1)); // symmetric
    }

    @Test // x different
    public void testEqualDifferentX() {
        assertFalse(new Point(32, 0).equals(new Point(-8, 0)));
    }

    @Test // y different
    public void testEqualDifferentY() {
        assertFalse(new Point(-.5, 473).equals(new Point(-.5, 948)));
    }

    @Test // x, y same
    public void testHashcodeSameValues() {
        Point p1 = new Point(48, -13);
        Point p2 = new Point(48, -13);
        assertEquals(p1.hashCode(), p2.hashCode());
    }
}
