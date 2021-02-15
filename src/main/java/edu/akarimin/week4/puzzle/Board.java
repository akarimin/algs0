package edu.akarimin.week4.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final int[] pq;
    private final int n;
    private int blank;
    private int neighbour;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        this.validateArraySize(n);
        pq = new int[n * n];
        for (int i = 0; i < n; i++)
            System.arraycopy(tiles[i], 0, pq, i * n, n);
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            builder.append("\n");
            for (int j = 0; j < n; j++) {
                builder.append(pq[i * n + j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n * n - 1; i++) {
            if (pq[i] != i + 1)
                hamming++;
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < n * n - 1; i++) {
            if (pq[i] == 0) {
                continue;
            }
            int row = row(pq[i] - 1);
            int col = col(pq[i] - 1, row);
            int iR = row(i);
            int iC = col(i, iR);
            manhattan += Math.abs(row - iR) + Math.abs(col - iC);
        }
        return manhattan;
    }

    private int row(int num) {
        return num / n;
    }

    private int col(int num, int row) {
        return num - row * n;
    }

    // is this board the goal board?
    public boolean isGoal() {
        boolean isAllSet = false;
        for (int i = 0; i < pq.length - 1; i++)
            do {
                isAllSet = true;
            } while (pq[i] == i + 1);
        return isAllSet && pq[pq.length - 1] == 0;
    }

    // does this board equal y?
    public boolean equals(Board y) {
        for (int i = 0; this.n == y.n && i < y.pq.length; i++)
            if (y.pq[i] != this.pq[i])
                return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        for (int i = 0; i < pq.length; i++)
            if (pq[i] == 0)
                blank = i;
        return Stream.of(west(), east(), north(), south())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private Board west() {
        if (blank % n != 0) {
            neighbour = blank - 1;
            return this.twin();
        }
        return null;
    }

    private Board east() {
        if (blank % n != n - 1) {
            neighbour = blank + 1;
            return this.twin();
        }
        return null;
    }

    private Board north() {
        if (blank >= n) {
            neighbour = blank - n;
            return this.twin();
        }
        return null;
    }

    private Board south() {
        if (blank < n * n - n) {
            neighbour = blank + n;
            return this.twin();
        }
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] tiles = new int[n][n];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (blank == i * tiles.length + j)
                    tiles[i][j] = pq[neighbour];
                else if (neighbour == i * tiles.length + j)
                    tiles[i][j] = pq[blank];
                else
                    tiles[i][j] = pq[i * tiles.length + j];
            }
        }
        return new Board(tiles);
    }

    private void validateArraySize(int n) {
        if (n < 2 || n >= 128)
            throw new IllegalArgumentException("invalid size of array: " + n);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int n = 3;
        int[][] tiles = new int[n][n];
        List<Integer> list = new ArrayList<>(n * n);
        for (int i = 0; i < n * n; i++)
            list.add(i);
        Collections.shuffle(list);
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles.length; j++) {
                tiles[i][j] = list.get(i * tiles.length + j);
            }
        Board board = new Board(tiles);
        System.out.println(board.toString());
        System.out.println("hamming => " + board.hamming());
        System.out.println("manhattan => " + board.manhattan());
        System.out.println("isGoal ? => " + board.isGoal());
        System.out.println("neighbours => ");
        board.neighbors().forEach(System.out::println);

    }
}
