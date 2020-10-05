package edu.akarimin.week3.collinear;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * O(N^2)
 */
public final class FastCollinearPoints {

    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
        this.validatePoints(points);
        int n = points.length;
        Arrays.sort(points);
        final List<LineSegment> maxLineSegments = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            Point p = points[i];
            Point[] slopeSortedClone = points.clone();
            Arrays.sort(slopeSortedClone, p.slopeOrder());

            int x = 1;
            while (x < n) {
                LinkedList<Point> selectedSegments = new LinkedList<>();
                double originSlope = p.slopeTo(slopeSortedClone[x]);
                do {
                selectedSegments.add(slopeSortedClone[x++]);
            } while (x < n && p.slopeTo(slopeSortedClone[x]) == originSlope);

                if (selectedSegments.size() >= 3 && p.compareTo(selectedSegments.peek()) < 0) {
                    Point max = selectedSegments.removeLast();
                    maxLineSegments.add(new LineSegment(p, max));
                }
            }
        }
        segments = maxLineSegments.toArray(new LineSegment[0]);
    }

    public int numberOfSegments() {                 // the number of line segments
        return segments.length;
    }

    public LineSegment[] segments() {               // the line segments
        return Arrays.copyOf(segments, segments.length);
    }

    private void validatePoints(final Point[] points) {
        if (Objects.isNull(points))
            throw new NullPointerException("Points are not valid.");
        for (Point point : points)
            if (Objects.isNull(point))
                throw new NullPointerException("Points are not valid.");
        for (int i = 0; i < points.length - 1; i++)
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}


