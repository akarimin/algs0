package edu.akarimin.week2;

/**
 * Greatest Common Denominator
 */
public class GCD {

    static int gcd(int p, int q) {
        return q == 0 ? p : gcd(p, p % q);
    }

    public static void main(String[] args) {
        System.out.println(GCD.gcd(25, 24));
    }
}
