package edu.akarimin.week4;

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
 * both insert and delMax -> logN order of growth ! which is great.
 * Fibonnaci insert is 1 for inserting and logN for delMax but not as practical as binary heap.
 * Assumptions:
 * -> client does not change the keys while they are on the PQ.
 * Best Practice: Make Keys Immutable.
 * underflow: throw exception in case of empty PQ.
 * overflow: add no-arg constructor and use resizing array,
 * For minimum-oriented priority queue, use greater() helper operation instead of less().
 * We can add other operations like Remove an arbitrary item OR change the priority of an item.
 */
public class BinaryHeap<Key extends Comparable<Key>> {

    private final Key[] pq;
    private int n;

    @SuppressWarnings("unchecked")
    public BinaryHeap(int capacity) {                      // we can use resizing strategy instead of fixed capacity
        this.pq = (Key[]) new Comparable[capacity + 1];    // bc we don't use position 0
    }

    // Add node at the end, then swim it up
    public void insert(Key x) {
        pq[++n] = x;
        swim(n);
    }

    // brilliant
    public Key delMax() {
        Key max = pq[1];
        exchange(1, n--);       // violates heap order so we need a sink
        sink(1);
        pq[n + 1] = null;             // prevent loitering
        return max;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    // Peter Principle
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {   // k/2 = parent
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    // if a new boss is hired and is less than subordinates, it should swim down (sink), starting with greater child
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1))    // children of node k (subordinates)
                j++;
            if (!less(k, j))
                break;
            exchange(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
