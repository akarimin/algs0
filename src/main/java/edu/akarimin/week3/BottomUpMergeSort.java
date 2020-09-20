package edu.akarimin.week3;

/**
 * No Recursion - Industrial-Strength - it used more space than regular mergesort
 */
public class BottomUpMergeSort {

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int n = a.length;
        aux = new Comparable[n];
        for (int sz = 1; sz < n; sz++)
            for (int lo = 0; lo < n - sz; lo += sz + sz)
                MergeSort.merge(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
    }
}
