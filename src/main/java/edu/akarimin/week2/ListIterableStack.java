package edu.akarimin.week2;

import java.util.Iterator;

public class ListIterableStack<Item> implements Iterable<Item> {

    private Node<Item> first;

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Item> {

        private Node<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private Item[] s;

    private class ReverseArrayIterator implements Iterator<Item> {

        private int i;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return s[--i];
        }
    }
}
