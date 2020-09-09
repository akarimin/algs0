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

    private Item[] items;
    private int last;

    public RandomizedQueue() {
        @SuppressWarnings("unchecked")
        Item[] newArray = (Item[]) new Object[1];
        items = newArray;
        last = -1;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return last + 1;
    }

    // add the item
    public void enqueue(Item item) {
        this.validatePushingItemNullity(item);
        if (last + 1 == items.length)
            resize(2 * items.length);
        items[last++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        this.validatePoppingItemNullity();
        int rand = StdRandom.uniform(last + 1);
        Item dequeued = items[rand];
        items[last--] = null;
        if (last + 1 == items.length / 4)
            resize(items.length / 2);
        return dequeued;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        this.validatePoppingItemNullity();
        Item sample = null;
        while (sample == null) {
            sample = items[StdRandom.uniform(last + 1)];
        }
        return sample;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Object[] resized = new Object[newCapacity];
        System.arraycopy(items, 0, resized, 0, items.length);
        items = (Item[]) resized;
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // TODO:
    private class RandomizedQueueIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("cannot remove an item from an empty RandomizedQueue.");
            return null;
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
