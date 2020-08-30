package edu.akarimin.week2;

/**
 * Item[] items = new Item[n] => not allowed because Arrays (unlike Generics) need to know about its components at
 * runtime, so if the type has not been determined yet, Generic Arrays cannot be instantiated.
 */
public class ParameterizedArrayStack<Item> {

    private Item[] s;
    private int n;

    public ParameterizedArrayStack(int capacity) {
        s = (Item[]) new Object[capacity]; // unfortunately we have to
    }
}
