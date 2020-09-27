package edu.akarimin.week3.collinear;

import java.util.Arrays;
import java.util.Objects;

/**
 * O(N^4)
 */
public class BruteCollinearPoints {

    private int segmentNum = 0;

    public BruteCollinearPoints(final Point[] points) {   // finds all line segments containing 4 points
        if (Objects.isNull(points) ||
                Arrays.stream(points).anyMatch(Objects::isNull) ||
                Arrays.stream(points).distinct().count() != points.length)
            throw new IllegalArgumentException("Points are not valid.");
        if (points.length != 4)
            throw new IllegalArgumentException("4 points needed.");
        for (int i = 0; i < points.length; i++) {
            double[] slopes = new double[3];
            for (int j = i + 1; j < points.length; j++) {
                slopes[j] = points[i].slopeTo(points[j]);
            }
            if (Arrays.stream(slopes).distinct().count() <= 1)
                segmentNum++;
        }
    }

    public int numberOfSegments() {                 // the number of line segments
        return segmentNum;
    }

    public LineSegment[] segments() {               // the line segments
        return null;
    }
}

