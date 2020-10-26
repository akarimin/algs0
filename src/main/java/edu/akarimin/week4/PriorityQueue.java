package edu.akarimin.week4;

import edu.princeton.cs.algs4.StdIn;

/**
 * Remove the largest or smallest item.
 * API is similar to Stack or Queue with difference in Generics of Comparable for Keys.
 * Different impl: (Order of Growth)
 * 1. Sort           -> MLogN (Time)  N (Space)
 * 2. Elementary PQ  -> MN            M (Space) [Unordered and Ordered]
 * 3. Binary Heap    -> NLogM         M (Space)
 * 4. Best in Theory -> N             M
 * Implement with LinkedList or Resizing Array
 */
public class PriorityQueue<T extends Comparable<T>> {

    private int size;

    public void insert(T item) {
        // different implementations
    }

    public void delMin() {
        // different implementations
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        PriorityQueue<String> pq = new PriorityQueue<>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            pq.insert(line);
            if (pq.size() > 5) {
                pq.delMin();
            }
        }
    }
}
