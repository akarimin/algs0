package edu.akarimin.week2;

/**
 * A set of n points is the smallest perimeter fence enclosing the points.
 * پوسته کوژ (قشر محدب)
 * Convex,Hull,Vertex
 * <p>
 * Graham Scan:
 * 1. Start from smallest y-coordinate point and give index to all points counterclockwise. (polar angle)
 * 2. Always you can move counterclockwise from lowest vertex.
 * <p>
 * Solution for 1: Define a total order comparing by y-coordinates.
 */
public class ConvexHull {

    public class Point2D {
        private final double x;
        private final double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // is a->b->c counter-clock-wise ?
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0)         // clockwise
            return -1;
        else if (area2 > 0)    // counterclockwise
            return 1;
        else                   // collinear
            return 0;
    }
}
