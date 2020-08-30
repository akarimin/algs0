package edu.akarimin.week2;

/**
 * Memory: ~8N to ~32N
 * Time:
 * Tradeoff: Resizing array vs. LinkedList => If you want a guarantee that each operation is fast, like switching
 * Internet packet, use LinkedList; Ow, if you care only about total amount of time, use resizing array.
 * */
public class ResizingCapacityStackOfStrings {

    private String[] s;
    private int n;

    public ResizingCapacityStackOfStrings() {
        s = new String[1];
    }

    /**
     * Solution: Double the array capacity when full.
     */
    public void push(String item) {
        if (n == s.length)
            resize(2 * s.length);
        s[n++] = item;
    }

    /**
     * Thrashing might happen: push,pop,push,pop ... sequence when array is full.
     * Solution: Shrink when array is down-sized to 1/4 full, then halve it.
     */
    public String pop() {
        String item = s[--n];
        s[n] = null;
        if (n > 0 && n == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        System.arraycopy(s, 0, copy, 0, s.length);
        s = copy;
    }
}
