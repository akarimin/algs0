package edu.akarimin.week3.collinear;

public final class LineSegment {

    private final Point p;
    private final Point q;

    public LineSegment(final Point p, final Point q) {       // constructs the line segment between points p and q
        this.p = p;
        this.q = q;
    }

    public void draw() {                         // draws this line segment
        p.drawTo(q);
    }

    public String toString() {                   // string representation
        return p.toString() + " -> " + q.toString();
    }
}