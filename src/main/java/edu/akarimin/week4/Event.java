package edu.akarimin.week4;

public class Event implements Comparable<Event> {

    private double time;                // Time of Event
    private Particle a, b;              // Particles involved in event
    private int countA, countB;         // collision counts for a and b

    public Event(double time, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Event that) {              // ordered by time
        return (int) (this.time - that.time);
    }

    public boolean isValid() {
        return true;
    }
}
