package edu.akarimin.week2;

/**
 * 1/2(N^2) compares and N exchanges ~ O(1/2N^2 + N) => Quadratic time, even if input is sorted.
 * Idea: Assume i = min and examines j = i + 1 loop over j < length and check if the j is less than min and exchange.
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
