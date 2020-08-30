package edu.akarimin.week1.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE = 1.96d;
    private final double[] probs;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0.");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException("trials <= 0.");
        }
        this.probs = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openSites = percolation.numberOfOpenSites();
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(n) + 1;
                int randomCol = StdRandom.uniform(n) + 1;
                if (!percolation.isOpen(randomRow, randomCol)) {
                    percolation.open(randomRow, randomCol);
                    openSites++;
                }
            }
            probs[i] = openSites * 1.0 / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(probs);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(probs);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE * stddev()) / Math.sqrt(probs.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE * stddev()) / Math.sqrt(probs.length);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        if (t < 1)
            throw new IllegalArgumentException("invalid number of trials: " + t);
        PercolationStats stats = new PercolationStats(n, t);
        StdOut.println("mean                     = " + stats.mean());
        StdOut.println("stddev                   = " + stats.stddev());
        StdOut.println("95% confidence interval  = " +
                "["
                + stats.confidenceLo() + ", "
                + stats.confidenceHi() +
                "]");
    }
}
