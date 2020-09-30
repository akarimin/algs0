package edu.akarimin.week3.collinear;


import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;
import java.util.Objects;

public final class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    public Point(final int x, final int y) {                // constructs the point (x, y)
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
        if (Objects.isNull(that))
            throw new NullPointerException("Point is not provided.");
        int diff = this.y - that.y;
        if (diff == 0) {
            diff = this.x - that.x;
        }
        return diff;
    }

    public double slopeTo(Point that) {         // the slope between this point and that point
        if (Objects.isNull(that))
            throw new NullPointerException("Point is not provided.");
        int yDiff = this.y - that.y;
        int xDiff = this.x - that.x;
        if (xDiff == 0 && yDiff == 0)
            return Double.NEGATIVE_INFINITY;
        else if (yDiff == 0)
            return 0;
        else if (xDiff == 0)
            return Double.POSITIVE_INFINITY;
        else
            return (yDiff * 1.0 / xDiff);
    }

    public Comparator<Point> slopeOrder() {     // compare two points by slopes they make with this point
        return new SlopeComparator(this);
    }

    public String toString() {                  // string representation
        return "(" + x + "," + y + ")";
    }

    private class SlopeComparator implements Comparator<Point> {

        private final Point comparingPoint;

        public SlopeComparator(final Point point) {
            this.comparingPoint = point;
        }

        @Override
        public int compare(Point o1, Point o2) {
            double thisSlope = o1.slopeTo(comparingPoint);
            double thatSlope = o2.slopeTo(comparingPoint);
            return Double.compare(thisSlope, thatSlope);
        }
    }
}