package edu.akarimin.week2;

public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            for (int j = i; j > 0; j--)
                if (a[j].compareTo(a[j - 1]) < 0) {
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

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
