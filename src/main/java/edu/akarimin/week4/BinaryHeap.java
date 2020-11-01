package edu.akarimin.week4;

import static edu.akarimin.week2.InsertionSort.exchange;
import static edu.akarimin.week2.InsertionSort.less;

/**
 * Non-basic implementation of PriorityQueue
 * Binary-Tree -> Complete Tree
 * Height increases only when N is a power of 2 (N is number of Nodes)
 * Heap-ordered binary tree: Keys in nodes && parent's key no smaller than children's keys.
 * Array representation:
 * 0. Index starts at 1, take nodes in level order, no explicit links needed.
 * 1. Largest key is a[1], root of binary key.
 * 2. Parent of node at k is at k/2 (integer divide).
 * 3. Children of node k are at 2k and 2k+1.
 * Peter Principle; Node promoted to level of incompetence. (not be better than its boss)
 */
public class BinaryHeap<Key extends Comparable<Key>> {

    private final Key[] pq;
    private int n;

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {
        this.pq = (Key[]) new Comparable[capacity];
    }

    // Peter Principle
    public void swim(int k) {
        while (k > 1 && less(k/2, k)) {   // k/2 = parent
            exchange(pq, k, k/2);
            k = k/2;
        }
    }

    // Add node at the end, then swim it up
    public void insert(Key x) {
       pq[++n] = x;
       swim(n);
    }
}
