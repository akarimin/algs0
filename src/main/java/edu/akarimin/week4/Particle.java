package edu.akarimin.week4;

public class Particle {

    private double rx, ry;          // position
    private double vx, vy;          // velocity
    private final double radius;    // radius
    private final double mass;      // mass
    private int count;              // number of collisions

    public Particle() {
        radius = 1.0;
        mass = 1.0;
    }

    public void move(double dt) {}

    public void draw() {}

    /* predict collision with particle or wall. */
    public double timeToHit(Particle that) {
        return 0.0;
    }
    public double timeToHitVerticalWall() {
        return 0.0;
    }
    public double timeToHitHorizontalWall() {
        return 0.0;
    }

    /* resolve collision with particle or wall. */
    public void bounceOff(Particle that) {}
    public void bounceOffVerticalWall(Particle that) {}
    public void bounceOffHorizontalWall(Particle that) {}
}
