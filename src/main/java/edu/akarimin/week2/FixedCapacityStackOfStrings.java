package edu.akarimin.week2;

import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Fundamental defect with the array implementation is size of the array which needs to be managed.
 * Loitering
 */
public class FixedCapacityStackOfStrings {

    private final String[] s;
    private int n = 0;

    public FixedCapacityStackOfStrings(/*CHEAT*/int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void push(String item) {
        if (n == s.length)
            throw new IllegalStateException("Stack Overflow.");
        s[n++] = item;
    }

    /**
     * Loitering might happen
     * */
    public String pop() {
        if (n == 0)
            throw new NoSuchElementException("Stack Underflow.");
        String item = s[--n];
        s[n] = null; // To Avoid Loitering
        return item;
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (Objects.equals("-", item))
                System.out.println(stack.pop());
            else
                stack.push(item);
        }
    }

}
