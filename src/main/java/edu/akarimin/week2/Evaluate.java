package edu.akarimin.week2;

import edu.princeton.cs.algs4.StdIn;

import java.util.Objects;
import java.util.Stack;

/**
 * 2-Stack Algorithm - An Interpreter
 */
public class Evaluate {

    public static void main(String[] args) {
        Stack<Double> vals = new Stack<>();
        Stack<String> ops = new Stack<>();
        System.out.println("Insert executable expression: ");
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (Objects.equals("(", s)) ;
                // ignore;
            else if (Objects.equals("+", s))
                ops.push(s);
            else if (Objects.equals("*", s))
                ops.push(s);
            else if (Objects.equals(")", s)) {
                String op = ops.pop();
                if (Objects.equals("+", op))
                    vals.push(vals.pop() + vals.pop());
                else if (Objects.equals("*", op))
                    vals.push(vals.pop() * vals.pop());
            } else
                vals.push(Double.parseDouble(s));
        }
        System.out.println(vals.pop());
    }
}
