package edu.akarimin.week2;

/**
 * Basic Strategy - Stable
 * ~1/4 N^2 compares and exchanges. Twice as fast as Selection Sort.
 * Best Case: N-1 compares and 0 exchanges.
 * Worst Case: ~1/2N^2 compares and ~1/2N^2 exchanges.
 * Idea: Start with i, check if j = i and loop over j > 0, if j > i - 1 exchange.
 */
public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1])) {   // N-1 look back
                    exchange(a, j, j - 1);
                } else
                    break;
    }

    public static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        if (hi >= a.length || lo < 0)
            throw new RuntimeException("Invalid lo: " + lo + " and hi: " + hi);
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
