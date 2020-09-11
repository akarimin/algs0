package edu.akarimin.week2;

/**
 * 1/2(N^2) compares and N exchanges ~ O(1/2N^2 + N) => Quadratic time, even if input is sorted.
 */
public class SelectionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (InsertionSort.less(a[j], a[min]))
                    min = j;
                InsertionSort.exchange(a, i, min);
            }
        }
    }
}
