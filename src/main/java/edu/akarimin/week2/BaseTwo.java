package edu.akarimin.week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

/**
 * Java Stack's Iterator returns the items in FIFO order,
 * but Princeton Stack's Iterator returns in LIFO order.
 */
public class BaseTwo {

    public String findBaseTwo(int n, boolean java) {
        if (n < 1)
            throw new IllegalArgumentException("Invalid number.");
        StringBuilder builder = new StringBuilder();

        if (java) {
            Stack<Integer> stack = new Stack<>();
            while (n > 0) {
                stack.push(n % 2);
                n = n / 2;
            }

            for (int digit : stack) {
                builder.append(digit);
            }
        } else {
            edu.princeton.cs.algs4.Stack<Integer> stack = new edu.princeton.cs.algs4.Stack<>();
            while (n > 0) {
                stack.push(n % 2);
                n = n / 2;
            }

            for (int digit : stack) {
                builder.append(digit);
            }
        }
        StdOut.println();
        return builder.toString();
    }

    public static void main(String[] args) {
        BaseTwo baseTwo = new BaseTwo();
        System.out.printf("%-10s => JAVA", baseTwo.findBaseTwo(50, true));
        System.out.printf("%-10s => PRINCETON", baseTwo.findBaseTwo(50, false));
    }
}
