package edu.akarimin.week2;

/**
 * For Resizing implementation: Maintain head and tail indices
 */
public class LinkedQueueOfStrings {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public LinkedQueueOfStrings() {
    }

    public void enqueue(String item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }

    // identical to pop() method in Stack
    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
