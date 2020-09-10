package edu.akarimin.week2;

import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.util.Objects;

public class SimpleSorting {

    private boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    private static void exchange(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void sortDoubles(String[] args) {
        int n = Integer.parseInt(args[0]);
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform();
        }
        Insertion.sort(a);
        for (int i = 0; i < n; i++) {
            StdOut.println(a[i]);
        }
    }

    private void sortStrings(String[] args) {
        String[] a = args;
        Insertion.sort(a);
        for (String s : a) {
            StdOut.println(s);
        }
    }

    private void sortFiles(String[] args) {
        File directory = new File(args[1]);
        File[] files = directory.listFiles();
        if (Objects.nonNull(files) && files.length != 0) {
            Insertion.sort(files);
            for (File file : files) {
                StdOut.println(file.getName());
            }
        }
    }

    public static void main(String[] args) {
        SimpleSorting simpleSorting = new SimpleSorting();
        if (Objects.equals("s", args[0]))
            simpleSorting.sortStrings(args);
        else if (Objects.equals("f", args[0]))
            simpleSorting.sortFiles(args);
        else
            simpleSorting.sortDoubles(args);
    }
}
