package edu.akarimin.week2;

/**
 * Java does not allow Generic Array creation.
 */
public class ParameterizedStack<Item> {

    private Node<Item> first;

    public void push(Item item) {
        Node<Item> firstOld = first;
        first = new Node<>();
        first.item = item;
        first.next = firstOld;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }
}
