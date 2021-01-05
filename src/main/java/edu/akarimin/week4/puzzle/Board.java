package edu.akarimin.week4.puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private final int[] pq;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        pq = new int[n * n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                pq[i * n + j] = tiles[i][j];
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (int i = 0; i < n; i++) {
            builder.append("\n");
            for (int j = 0; j < n; j++) {
                builder.append(String.format("[%d,%d]=%d, ", i, j, pq[i * n + j]));
            }
            builder.append("\n");
        }

        builder.append("}");
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return 0;
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
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return null;
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
    }
}
