package edu.akarimin.week3.collinear;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public final class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(final int x, final int y) {                // constructs the point (x, y)
        if (x < 0 || x > 32767)
            throw new IllegalArgumentException("x is invalid: " + x);
        if (y < 0 || y > 32767)
            throw new IllegalArgumentException("y is invalid: " + y);
        this.x = x;
        this.y = y;
    }

    public void draw() {                        // draws this point
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point that) {            // draws the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point that) {          // compare two points by y-coordinates, breaking ties by x-coordinates
        int diff = this.y - that.y;
        if (diff <= 0) {
            diff = this.x - that.x;
        }
        return diff;
    }

    public double slopeTo(Point that) {         // the slope between this point and that point
        int yDiff = that.y - this.y;
        int xDiff = that.x - this.x;
        if (xDiff == 0 && yDiff == 0)
            return Double.NEGATIVE_INFINITY;
        else if (yDiff == 0)
            return 0;
        else if (xDiff == 0)
            return Double.POSITIVE_INFINITY;
        else
            return yDiff / xDiff;
    }

    public Comparator<Point> slopeOrder() {     // compare two points by slopes they make with this point
        return (o1, o2) -> (int) o1.slopeTo(o2);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {                  // string representation
        return "{x='" + x + "','y='" + y + "'";
    }
}