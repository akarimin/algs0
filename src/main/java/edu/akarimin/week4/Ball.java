package edu.akarimin.week4;

import edu.princeton.cs.algs4.StdDraw;

public class Ball {

    private double rx, ry;          // position
    private double vx, vy;          // velocity
    private final double radius;    // radius

    public Ball() {
        radius = 1.0;
    }

    public void move(double dt) {
        if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius))  // Check for collision with walls
            vx = -vx;
        if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius))
            vy = -vy;
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }
}
