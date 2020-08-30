package edu.akarimin.week2;

/**
 * Java does not allow Generic Array creation.
 */
public class ParameterizedStack<Item> {

    private Node first;
    private int n;

    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node firstOld = first;
        first = new Node();
        first.item = item;
        first.next = firstOld;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }
}
