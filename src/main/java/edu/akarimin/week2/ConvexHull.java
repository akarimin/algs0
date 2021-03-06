package edu.akarimin.week2;

import edu.akarimin.week3.Point2D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

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
 * <p>
 * Scan: NLogN for sorting and linear for rest
 */
public class ConvexHull {

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

    // Graham Scan
    // Simplifying assumptions: no three points on a line; at least 3 points
    public void scan(Point2D[] p) {
        Stack<Point2D> hull = new Stack<>();
        Arrays.sort(p, Comparator.comparing(point -> point.y));       // sort with lowest y-coordinate
        hull.push(p[0]);
        Arrays.sort(p, p[0].POLAR_ORDER);                             // sort by polar angle
        hull.push(p[1]);

        for (int i = 2; i < p.length; i++) {
            Point2D top = hull.pop();
            while (ccw(hull.peek(), top, p[i]) <= 0)
                top = hull.pop();
            hull.push(top);
            hull.push(p[i]);
        }
    }
}
