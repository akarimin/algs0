package edu.akarimin.week2;

import edu.princeton.cs.algs4.StdRandom;

/**
 * First non-trivial sorting application. e.g. used in online poker.
 * How to shuffle a deck of cards: Assign a random number [0,1) to each card and sort them.
 * It produces a uniformly random Permutation of the input array, provided no duplicate values.
 * Drawback: Needs sorting.
 * <p>
 * Linear Approach: pick i and r <= i uniformly and swap them, then increment i.
 */
public class ShuffleSort {

    public static void shuffle(Comparable[] sorted) {
        for (int i = 0; i < sorted.length; i++) {
            int r = StdRandom.uniform(0, i + 1);
            InsertionSort.exchange(sorted, i, r);
        }
    }

    /**
     * "The Generation of random numbers is too important to be left to chance."
     * - Robert R. Coveyou
     */
}
