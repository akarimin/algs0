package edu.akarimin.week2;

import java.util.Iterator;

/**
 * Equal to Stack w/o pop or Queue w/o dequeue
 */
public interface Bag<Item> extends Iterable<Item> {

    void add(Item item);

    int size();

    boolean isEmpty();

    @Override
    Iterator<Item> iterator();
}
