package edu.akarimin.week2;

import java.util.Arrays;

/**
 * First non-trivial sorting method.
 * Insertion Sort is inefficient bc elements move only one position at a time.
 * Always takes quadratic time no matter what values in the array.
 * Idea: Move several position at a time called h-Sorting the array.
 * So ShellSort is Insertion sorting with h-sort.
 * worst-case comparisons woth 3X+1: O(N^3/2)
 * Accurate model has not been discovered yet.
 */
public class ShellSort {

    // H: 7-3-1 -> increment sequence: 3X + 1 easy to move approach

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;   // 1,4,13,40,121,364,... 3X + 1
        System.out.println("h: " + h);
        while (h >= 1) {     // h-sort
            for (int i = h; i < n; i++) {
                System.out.println("i: " + i);
                for (int j = i; j >= h && InsertionSort.less(a[j], a[j - h]); j -= h) {
                    System.out.println("j: " + j);
                    InsertionSort.exchange(a, j, j - h);
                }
            }
            h = h / 3;       // move to next increment
        }
        Arrays.stream(a).forEach(x -> System.out.print(x + ","));
    }
}
