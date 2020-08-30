package edu.akarimin.week1;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.stream.IntStream;

public class ThreeSum {

    private int sumsToZero(int[] nums) {      // O(N^3)  --> Improved algorithm can make it O(N^2 LgN)
                                              // with binary-search approach
        int[] count = new int[]{0};
        IntStream.range(0, nums.length)
                .forEach(i -> IntStream.range(i + 1, nums.length)
                    .forEach(j -> IntStream.range(j + 1, nums.length)
                        .filter(k -> nums[i] + nums[j] + nums[k] == 0)
                        .forEach(x -> count[0] ++)));
        return count[0];
    }

    // TODO: Improved method -> Sort all nums, for each pair from the lowest binary search for -(a[i]+a[j])
    // only count k iff -> a[i] <= a[j] <= a[k]

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        int i = new ThreeSum().sumsToZero(new int[]{1, 2, 3, -3, -1, -5, 5, 3, 6, 7, 4, -3, -4, -6});
        System.out.println("Number of triples: " + i + ", run in: " + stopwatch.elapsedTime());
    }
}
