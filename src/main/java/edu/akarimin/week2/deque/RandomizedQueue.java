package edu.akarimin.week2.deque;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random among items in the data structure
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private final Node first, last;
    private int n;

    private class Node {

        Node next, prev;
        Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.n = 0;
        first = new Node(null);
        last = new Node(null);
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        this.validatePushingItemNullity(item);
    }

    // remove and return a random item
    public Item dequeue() {
        this.validatePoppingItemNullity();
        return null;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        this.validatePoppingItemNullity();
        return null;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            throw new NoSuchElementException("cannot remove an item from an empty RandomizedQueue.");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported for RandomizedQueue Object.");
        }
    }

    private void validatePushingItemNullity(final Item item) {
        if (Objects.isNull(item))
            throw new IllegalArgumentException("enqueuing item cannot be null.");
    }

    private void validatePoppingItemNullity() {
        if (isEmpty())
            throw new NoSuchElementException("cannot remove an item from an empty RandomizedQueue.");
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> stringQueue = new RandomizedQueue<>();
        String first = "Algorithm";
        String last = "Data";
        String second = "Structure";
        String otherLast = "Java";
        stringQueue.enqueue(first);
        StdOut.println("item enqueued to Queue: " + first);
        stringQueue.enqueue(last);
        StdOut.println("item enqueued to Queue: " + last);
        StdOut.println("item dequeued from Queue: " + stringQueue.dequeue());
        StdOut.println("item sampled from Queue: " + stringQueue.sample());
        StdOut.println("size of Queue: " + stringQueue.size());
        StdOut.println("is Queue empty: " + stringQueue.isEmpty());
        while (stringQueue.iterator().hasNext())
            StdOut.println("item: " + stringQueue.iterator().next());
    }
}
