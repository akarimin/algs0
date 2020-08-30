package edu.akarimin.week1;

import java.util.Arrays;

/**
 * Have a new problem ?
 * 1. Develop and algorithm
 * 2. Prove a lower bound
 *
 * Any Gap ?
 * 1. Lower the upper bound (discover a new algorithm)
 * 2. Raise the lower bound (more difficult)
 *
 * Upper-bound: worst-case scenario
 * Lower-bound: ideal scenario
 * somewhere in between: our solution
 * */
public class BinarySearch {

    private int binarySearch(int[] nums, int key) {   // O(Lg (base-2) N)
        int lo = 0;
        int hi = nums.length - 1;
        Arrays.sort(nums);
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < nums[mid])
                hi = mid - 1;
            else if (key > nums[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int i = search.binarySearch(new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 34}, 3);
        System.out.println("Result: " + i);
    }
}
