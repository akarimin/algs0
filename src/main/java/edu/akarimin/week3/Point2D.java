package edu.akarimin.week3;

import java.util.Comparator;

public class Point2D {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    public double x, y;

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

    /**
     * Comparator based approach for Graham Scan in solving Convex Hull
     */
    public class PolarOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D q1, Point2D q2) {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if (dy1 == 0 && dy2 == 0)           // p, q1, q2 horizontal
                throw new RuntimeException("Horizontal points provided.");
            else if (dy1 >= 0 && dy2 <= 0)      // q1 above p; q2 below p
                return -1;
            else if (dy2 >= 0 && dy1 < 0)       // q1 below p; q2 above p
                return 1;
            else
                return -ccw(Point2D.this, q1, q2);
        }
    }
}
