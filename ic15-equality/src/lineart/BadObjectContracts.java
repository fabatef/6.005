package lineart;


import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BadObjectContracts {

    @Test
    public void testObjectContractForSetOfPoint() {
        // make a set with the single point in it
        Point point = new Point(35, 42);
        Set<Point> set = new HashSet<>();
        set.add(point);
        
        // see if the point is in the set
        Point samePoint = new Point(35, 42);
        assertTrue("immutable Points should implement observational equality",
                   point.equals(samePoint));
        assertTrue("set should contain() the point we added to it",
                   set.contains(samePoint));
    }
    
    @Test
    public void testObjectContractForSetOfLineart() {
        // draw a square
        LineArt simpleArt = new LineArt();
        simpleArt.add(new LineSegment(  0,   0,   0, 100, Color.black));
        simpleArt.add(new LineSegment(  0, 100, 100, 100, Color.black));
        simpleArt.add(new LineSegment(100, 100, 100,   0, Color.black));
        simpleArt.add(new LineSegment(100,   0,   0,   0, Color.black));
        
        // put it in a set        
        Set<LineArt> set = new HashSet<>();
        set.add(simpleArt);
        
        // draw a red X over it
        simpleArt.add(new LineSegment(  0,   0, 100, 100, Color.red));
        simpleArt.add(new LineSegment(  0, 100, 100,   0, Color.red));

        // see if set is now confused about its contents
        for (LineArt art : set) {
            assertTrue("set should contain() its own elements", 
                       set.contains(art));
        }
    }
    
}
