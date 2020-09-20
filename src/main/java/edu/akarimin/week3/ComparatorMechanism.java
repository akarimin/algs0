package edu.akarimin.week3;

import java.util.Comparator;

/**
 * CompareTo is the impl of natural order.
 * Comparator interface is used for alternate order
 */
public class ComparatorMechanism {

    // Insertion Sort using Comparator
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++)
            for (int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    static class Student {
        public static final Comparator<Student> BY_NAME = new ByName();
        public static final Comparator<Student> BY_SECTION = new BySection();
        private String name;
        private int section;

        private static class ByName implements Comparator<Student> {
            @Override
            public int compare(Student v, Student w) {
                return v.name.compareTo(w.name);
            }
        }

        private static class BySection implements Comparator<Student> {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.section - o2.section;
            }
        }
    }
}
