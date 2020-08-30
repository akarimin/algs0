package edu.akarimin.week2;

import edu.princeton.cs.algs4.StdIn;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * LIFO - {@link java.util.LinkedList}
 * A stack with N items used ~40N bytes [16b object overhead, 8b inner class overhead, 16b reference]
 */
public class LinkedStackOfStrings {

    private Node first;
    private int n;

    public LinkedStackOfStrings() {
        first = null;
        n = 0;
    }

    private class Node {
        String item;
        Node next;
    }

    public void push(String item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public String pop() {
        if (this.isEmpty())
            throw new NoSuchElementException("Stack Underflow.");
        String item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    // tobe.txt: to be or not to - be - - that - - - is
    // output: to be not that or be
    public static void main(String[] args) {
        LinkedStackOfStrings stack = new LinkedStackOfStrings();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (Objects.equals("-", s))
                System.out.println(stack.pop());
            else
                stack.push(s);
        }
        System.out.println("Size of Stack: " + stack.size());
        System.exit(1);
    }
}
