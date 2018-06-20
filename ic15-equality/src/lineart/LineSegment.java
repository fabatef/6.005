/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lineart;

import java.awt.Color;

/**
 * An immutable line segment in pixel space.
 */
public class LineSegment {

    private final Point start;
    private final Point end;
    private final Color color;
    
    // Abstraction function:
    //      represents a line segment from start to end, colored color
    // Rep invariant:
    //      true
    // Safety from rep exposure:
    //      all fields are private, all types in the rep are immutable.
    
    // check the rep invariant, including the 6.005-implicit conditions!
    private void checkRep() {
        assert start != null;
        assert end != null;
        assert color != null;
    }
    
    /**
     * Construct a line segment from coordinate pairs.
     * 
     * @param startx x-coordinate of start point
     * @param starty y-coordinate of start point
     * @param endx x-coordinate of end point
     * @param endy y-coordinate of end point
     * @param color line segment color
     */
    public LineSegment(double startx, double starty, double endx, double endy, Color color) {
        this.start = new Point(startx, starty);
        this.end = new Point(endx, endy);
        this.color = color;
        checkRep();
    }

    /**
     * Construct a line segment from start and end points.
     * 
     * @param start one end of the line segment
     * @param end the other end of the line segment
     * @param color line segment color
     */
    public LineSegment(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
        checkRep();
    }

    /**
     * @return starting point of the line segment
     */
    public Point start() {
        return start;
    }

    /**
     * @return ending point of the line segment
     */
    public Point end() {
        return end;
    }

    /**
     * @return color of the line segment
     */
    public Color color() {
        return color;
    }

    /**
     * Compute the length of this segment.
     * 
     * @return the length of the line segment
     */
    public double length() {
        return Math.sqrt(Math.pow(this.start.x() - this.end.x(), 2.0)
                         + Math.pow(this.start.y() - this.end.y(), 2.0));
    }
    
    @Override
    public String toString() {
        return "Segment " + start + " -> " + end + " looks very " + color;
    }

    @Override
    public boolean equals(Object thatObject) {
        if ( ! (thatObject instanceof LineSegment)) { return false; }
        LineSegment that = (LineSegment)thatObject;
        
        return this.start().equals(that.start()) && this.end().equals(that.end())
        		&& this.color().equals(that.color());
    }
}
