package edu.akarimin.week1.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private int openSites = 0;
    private final WeightedQuickUnionUF unionFinder;
    private final boolean[] isOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) { // O(n^2)
        if (n <= 0)
            throw new IllegalArgumentException("Invalid dimension !" + n);
        this.n = n;
        this.isOpen = new boolean[n * n];
        unionFinder = new WeightedQuickUnionUF(n * n + 2);
        for (int i = 0; i < n * n; i++) {
            isOpen[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndex(row, col);
        isOpen[indexOf(row, col) - 1] = true;
        if (row == 1) {
            unionFinder.union(0, col);
        }
        if (row == n) {
            unionFinder.union(n * n + 1, col + (n - 1) * n);
        }

        if (isValidCorner(row - 1, col) && isOpen(row - 1, col)) {              // north 2,2 -> 2
            unionFinder.union(indexOf(row, col), indexOf(row - 1, col));
        }
        if (isValidCorner(row + 1, col) && isOpen(row + 1, col)) {              // south 2,2 -> 8
            unionFinder.union(indexOf(row, col), indexOf(row + 1, col));
        }
        if (isValidCorner(row, col - 1) && isOpen(row, col - 1)) {               // west 2,2 -> 4
            unionFinder.union(indexOf(row, col), indexOf(row, col - 1));
        }
        if (isValidCorner(row, col + 1) && isOpen(row, col + 1)) {               // east 2,2 -> 6
            unionFinder.union(indexOf(row, col), indexOf(row, col + 1));
        }
        openSites++;
    }

    private int indexOf(int row, int col) {
        this.validateIndex(row, col);
        return col + (row - 1) * n;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {            // O(1)
        this.validateIndex(row, col);
        return isOpen[indexOf(row, col) - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {            // O(1)
        this.validateIndex(row, col);
        return unionFinder.find(0) == unionFinder.find(indexOf(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {                     // O(n^2)
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {                         // O(Ln)
        return unionFinder.find(0) == unionFinder.find(n * n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        StdOut.print("Please Run PercolationStats main method.");
    }

    private void validateIndex(int row, int col) {
        if (row < 1 || row > n)
            throw new IllegalArgumentException("Invalid row: " + row);
        if (col < 1 || col > n)
            throw new IllegalArgumentException("Invalid col: " + col);
    }

    private boolean isValidCorner(int row, int col) {
        return !(row < 1 || col < 1 || row > n || col > n);
    }
}
