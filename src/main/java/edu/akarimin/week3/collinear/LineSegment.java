package edu.akarimin.week3.collinear;

import edu.princeton.cs.algs4.StdDraw;

public class LineSegment {

    private final Point p;
    private final Point q;

    public LineSegment(final Point p, final Point q) {       // constructs the line segment between points p and q
        this.p = p;
        this.q = q;
    }

    public void draw() {                         // draws this line segment
        StdDraw.line(p.getX(), p.getY(), q.getX(), q.getY());
    }

    public String toString() {                   // string representation
        return "{p=" + p.toString() + ",q=" + q.toString();
    }
}