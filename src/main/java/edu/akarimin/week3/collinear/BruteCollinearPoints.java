package edu.akarimin.week3.collinear;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * O(N^4)
 */
public final class BruteCollinearPoints {

    private final LineSegment[] segments;

    public BruteCollinearPoints(final Point[] points) {   // finds all line segments containing 4 points
        this.validatePoints(points);
        Arrays.sort(points);
        int n = points.length;
        List<LineSegment> segmentsSorted = new LinkedList<>();
        for (int i = 0; i < n - 3; i++) {
            Point a = points[i];
            for (int j = i + 1; j < n - 2; j++) {
                Point b = points[j];
                double abSlope = a.slopeTo(b);
                for (int k = j + 1; k < n - 1; k++) {
                    Point c = points[k];
                    double acSlope = a.slopeTo(c);
                    if (abSlope == acSlope) {
                        for (int w = k + 1; w < n; w++) {
                            Point d = points[w];
                            double adSlope = a.slopeTo(d);
                            if (abSlope == adSlope) {
                                segmentsSorted.add(new LineSegment(a, d));
                            }
                        }
                    }
                }
            }
        }
        segments = segmentsSorted.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {                 // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() {               // the line segments
        return Arrays.stream(segments).toArray(LineSegment[]::new);
    }

    private void validatePoints(final Point[] points) {
        if (Objects.isNull(points))
            throw new NullPointerException("Points are not valid.");
        for (Point point : points)
            if (Objects.isNull(point))
                throw new NullPointerException("Points are not valid.");
        for (int i = 0; i < points.length; i++)
            if (points[i].compareTo(points[i + 1]) == 0)
                throw new IllegalArgumentException("Duplicate points exist: " + points[i].toString());
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

