package edu.akarimin.week2.deque;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Deque: A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {

    private final Node first; // Dummy nodes
    private final Node last;  // Dummy nodes
    private int n;

    private class Node {

        Node next, prev;
        Item item;

        Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = new Node(null);
        last = new Node(null);
        first.next = last;
        last.prev = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        this.validatePushingItemNullity(item);
        Node node = new Node(item);
        node.next = first.next;
        node.prev = first;
        first.next.prev = node;
        first.next = node;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        this.validatePushingItemNullity(item);
        Node node = new Node(item);
        node.next = last;
        node.prev = last.prev;
        last.prev.next = node;
        last.prev = node;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        this.validatePoppingItemNullity();
        Node node = first.next;
        first.next = node.next;
        first.next.prev = first;
        n--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        this.validatePoppingItemNullity();
        Node node = last.prev;
        last.prev = node.prev;
        last.prev.next = last;
        n--;
        return node.item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("cannot remove an item from an empty Deque.");
            current = current.next;
            return current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove operation is not supported for Deque Object.");
        }
    }

    private void validatePushingItemNullity(final Item item) {
        if (Objects.isNull(item))
            throw new IllegalArgumentException("adding item cannot be null.");
    }

    private void validatePoppingItemNullity() {
        if (isEmpty())
            throw new NoSuchElementException("cannot remove an item from an empty Deque.");
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("================ STRING DEQUE ================");
        Deque<String> stringDeque = new Deque<>();
        String first = "Algorithm";
        String last = "Data";
        String second = "Structure";
        String otherLast = "Java";
        stringDeque.addFirst(first);
        StdOut.println("item added to first of Deque: " + first);
        stringDeque.addLast(last);
        StdOut.println("item added to last of Deque: " + last);
        stringDeque.addFirst(second);
        StdOut.println("item added to first of Deque: " + second);
        stringDeque.addLast(otherLast);
        StdOut.println("item added to last of Deque: " + otherLast);
        StdOut.println("item removed from first of Deque: " + stringDeque.removeFirst());
        StdOut.println("item removed from last of Deque: " + stringDeque.removeLast());
        StdOut.println("size of Deque: " + stringDeque.size());
        StdOut.println("is Deque empty: " + stringDeque.isEmpty());
        StdOut.println("next item: " + stringDeque.iterator().next());
        StdOut.println("next item: " + stringDeque.iterator().next());
        StdOut.println("next item: " + stringDeque.iterator().next());

        StdOut.println("================ INT DEQUE ================");
        Deque<Integer> intDeque = new Deque<>();
        intDeque.addFirst(1);
        StdOut.println("item added to first of Deque: " + 1);
        intDeque.addLast(2);
        StdOut.println("item added to last of Deque: " + 2);
        intDeque.addFirst(3);
        StdOut.println("item added to first of Deque: " + 3);
        intDeque.addLast(4);
        StdOut.println("item added to last of Deque: " + 4);
        StdOut.println("item removed from first of Deque: " + intDeque.removeFirst());
        StdOut.println("item removed from last of Deque: " + intDeque.removeLast());
        StdOut.println("size of Deque: " + intDeque.size());
        StdOut.println("is Deque empty: " + intDeque.isEmpty());
        StdOut.println("next item: " + intDeque.iterator().next());
        StdOut.println("next item: " + intDeque.iterator().next());
        StdOut.println("next item: " + intDeque.iterator().next());
    }
}
