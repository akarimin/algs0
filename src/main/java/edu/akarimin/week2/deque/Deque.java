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

    private Node<Item> first, last; // Dummy nodes
    private int n;

    private class Node<Item> {
        Node<Item> next, prev;
        Item item;

        public Node(Item item) {
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque() {
        n = 0;
        first = new Node<>(null);
        last = new Node<>(null);
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
        Node<Item> node = new Node<>(item);
        node.next = first.next;
        first.next.prev = node;
        first.next = node;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        this.validatePushingItemNullity(item);
        Node<Item> node = new Node<>(item);
        node.prev = last.prev;
        last.prev.next = node;
        last.prev = node;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        this.validatePoppingItemNullity();
        Node<Item> node = first.next;
        first.next = node.next;
        first.next.prev = first;
        n--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        this.validatePoppingItemNullity();
        Node<Item> node = last.prev;
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

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current.next != last;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("cannot remove an item from an empty Deque.");
            Item next = current.item;
            current = current.next;
            return next;
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
        while (stringDeque.iterator().hasNext())
            StdOut.println("item: " + stringDeque.iterator().next());
    }
}
