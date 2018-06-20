/* Copyright (c) 2007-2015 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package lineart;

/**
 * An immutable point in floating-point pixel space.
 */
public class Point {
    private final double x;
    private final double y;
    
    // Abstraction function:
    //      represents a point (x,y) in the plane
    // Rep invariant:
    //      true
    // Safety from rep exposure:
    //      all fields are private, all types in the rep are immutable.

    // assert the rep invariant
    private void checkRep() {
        // nothing to do. can't even compare x and y against null
    }
    
    /**
     * Construct a point at the given coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        checkRep();
    }

    /**
     * @return x-coordinate of the point
     */
    public double x() {
        return x;
    }

    /**
     * @return y-coordinate of the point
     */
    public double y() {
        return y;
    }
    
    @Override public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    @Override
    public boolean equals(Object thatObject) {
        if ( ! (thatObject instanceof Point)) { return false; }
        Point that = (Point)thatObject;
        
        return this.x()==that.x && this.y()==that.y();
    }
    @Override
    public int hashCode(){
    	//return 17;
    	//make eclipse generate it for you from the source menu
    	return new Double(this.x()).hashCode(); //all points with same x are in the same hash bucket
    	                                 // since a Double object obeys the object contract
    	
    	
    	
    	
    }
}
