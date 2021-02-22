package edu.akarimin.week5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

/**
 * Assumptions:
 * 1. Keys are Comparable.
 * 2. Keys are generic type, use equals and hashcode.
 * 3. Use immutable types for symbol table keys.
 * */
public class SymbolTable<Key, Value> {

    // To create a ST
    public SymbolTable() {
    }

    // remove key from table if value is absent, overrides old value with new value if duplicate
    public void put(Key key, Value value) {

    }

    // null if key is absent
    public Value get(Key key) {
        return null;
    }

    // lazy version of delete
    public void delete(Key key) {
        put(key, null);
    }

    // is there a value paired with key ?
    public boolean contains(Key key) {
        return Objects.nonNull(get(key));
    }

    public void isEmpty() {

    }

    public int size() {
        return 0;
    }

    public Iterable<Key> keys() {
        return null;
    }

    public Key min() {
        return null;
    }

    public Key max() {
        return null;
    }

    public Key floor(Key key) {
        return null;
    }

    public Key ceiling(Key key) {
        return null;
    }

    public int rank(Key ey) {
        return 0;
    }

    public Key select(int k) {
        return null;
    }


    /**
     * Reflexive, Symmetric, Transitive, Non-null.
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object that) {
        // Check that all significant fields are the same.
        if (this == that)
            return true;
        if (that == null)
            return false;
        if (this.getClass() != that.getClass())
            return false;
        SymbolTable<Key, Value> st = (SymbolTable<Key, Value>) that;
        return Objects.equals(this, st);
    }

    public static void main(String[] args) {
        SymbolTable<String, Integer> st = new SymbolTable<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s: st.keys()) {
            StdOut.println(s + " " + st.get(s));
        }
    }

}
