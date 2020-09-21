package edu.akarimin.week2.queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Deque: A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports
 * adding and removing items from either the front or the back of the data structure.
 */
public class Deque<Item> implements Iterable<Item> {

    private final Node first, last;  // Dummy nodes
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
            return current.next != last;
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

    private void validatePushingItemNullity(Item item) {
        if (Objects.isNull(item))
            throw new IllegalArgumentException("adding item cannot be null.");
    }

    private void validatePoppingItemNullity() {
        if (isEmpty())
            throw new NoSuchElementException("cannot remove an item from an empty Deque.");
    }

    // unit testing (required)
    public static void main(String[] args) {
        StdOut.println("================= STRING DEQUE =================");
        Deque<String> stringDeque = new Deque<>();
        String first = "Algorithm";
        String last = "Structure";
        String second = "Java";
        String secondLast = "Data";
        stringDeque.addFirst(first);
        StdOut.println("item added to first of Deque: " + first);
        stringDeque.addLast(last);
        StdOut.println("item added to last of Deque: " + last);
        stringDeque.addFirst(second);
        StdOut.println("item added to first of Deque: " + second);
        stringDeque.addLast(secondLast);
        StdOut.println("item added to last of Deque: " + secondLast);
        String removedFirst = stringDeque.removeFirst();
        StdOut.println("item removed from first of Deque " + removedFirst +
            " ? " + Objects.equals(removedFirst, second));
        String removedLast = stringDeque.removeLast();
        StdOut.println("item removed from last of Deque ? " + Objects.equals(removedLast, secondLast));
        StdOut.println("is size of Deque 2 ? " + (stringDeque.size() == 2));
        StdOut.println("next item is " + first + " ? " + Objects.equals(stringDeque.iterator().next(), first));
        StdOut.println("is Deque empty ? " + stringDeque.isEmpty());
        StdOut.println("Deque has next ? " + stringDeque.iterator().hasNext());

        StdOut.println("================= INT DEQUE =================");
        Deque<Integer> intDeque = new Deque<>();
        intDeque.addFirst(1);
        StdOut.println("item added to first of Deque: " + 1);
        intDeque.addLast(4);
        StdOut.println("item added to last of Deque: " + 4);
        intDeque.addFirst(2);
        StdOut.println("item added to first of Deque: " + 2);
        intDeque.addLast(3);
        StdOut.println("item added to last of Deque: " + 3);
        StdOut.println("item removed from first of Deque 2 ? " + (intDeque.removeFirst() == 2));
        StdOut.println("item removed from last of Deque 3 ? " + (intDeque.removeLast() == 3));
        StdOut.println("is size of Deque 2 ? " + (intDeque.size() == 2));
        StdOut.println("next item is 1 ? " + (intDeque.iterator().next() == 1));
        StdOut.println("is Deque empty ? " + intDeque.isEmpty());
        StdOut.println("Deque has next ? " + intDeque.iterator().hasNext());
    }
}
