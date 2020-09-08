package edu.akarimin.week2.deque;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item
 * removed is chosen uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Node first, last;
    private int n;

    private class Node {

        Node next;
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
        Node oldLast = last;
        last = new Node(item);
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        this.validatePoppingItemNullity();
        Node oldFirst = first;
        first = oldFirst.next;
        if (isEmpty())
            last = null;
        n--;
        return oldFirst.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        this.validatePoppingItemNullity();
        int uniform = StdRandom.uniform(0, n);
        int i = 0;
        Node sample = first;
        while (i < uniform) {
            sample = sample.next;
            i++;
        }
        return sample.item;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private Node current = last;

        @Override
        public boolean hasNext() {
            return last != first;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("cannot remove an item from an empty RandomizedQueue.");
            Node node = current.next;
            last = last.next;
            return node.item;
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
        String second = "Data";
        String third = "Structure";
        String fourth = "Java";
        String fifth = "Princeton";
        stringQueue.enqueue(first);
        StdOut.println("item enqueued to Queue: " + first);
        stringQueue.enqueue(second);
        StdOut.println("item enqueued to Queue: " + second);
        stringQueue.enqueue(third);
        StdOut.println("item enqueued to Queue: " + third);
        stringQueue.enqueue(fourth);
        StdOut.println("item enqueued to Queue: " + fourth);
        stringQueue.enqueue(fifth);
        StdOut.println("item enqueued to Queue: " + fifth);
        String firstDequeue = stringQueue.dequeue();
        StdOut.println("item dequeued from Queue: " + firstDequeue);
        String secondDequeue = stringQueue.dequeue();
        StdOut.println("item dequeued from Queue: " + secondDequeue);
        String sampled1 = stringQueue.sample();
        StdOut.println("item sampled from Queue: " + sampled1);
        String sampled2 = stringQueue.sample();
        StdOut.println("item sampled from Queue: " + sampled2);
        String sampled3 = stringQueue.sample();
        StdOut.println("item sampled from Queue: " + sampled3);
        String sampled4 = stringQueue.sample();
        StdOut.println("item sampled from Queue: " + sampled4);
        String sampled5 = stringQueue.sample();
        StdOut.println("item sampled from Queue: " + sampled5);
        StdOut.println("size of Queue: " + stringQueue.size());
        StdOut.println("is Queue empty: " + stringQueue.isEmpty());
    }
}
