package edu.akarimin.week4.puzzle;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final int[] pq;
    private final int n;
    private int blank = -1;
    private int neighbour = -1;

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
        boolean isAllSet = true;
        for (int i = 0; i < pq.length - 1; i++) {
            if (pq[i] != i + 1) {
                isAllSet = false;
                break;
            }
        }
        return isAllSet && pq[pq.length - 1] == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (Objects.isNull(y) || y.getClass() != this.getClass())
            return false;
        Board checking = (Board) y;
        for (int i = 0; this.n == checking.n && i < checking.pq.length; i++)
            if (checking.pq[i] != this.pq[i])
                return false;
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        blank = getBlank();
        return Stream.of(west(), east(), north(), south())
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private int getBlank() {
        for (int i = 0; i < pq.length; i++)
            if (pq[i] == 0) {
                blank = i;
                return i;
            }
        return -1;
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

    private int firstNeighbour() {
        if (Objects.nonNull(north()))
            return blank - n;
        else if (Objects.nonNull(south()))
            return blank + n;
        else if (Objects.nonNull(east()))
            return blank + 1;
        else if (Objects.nonNull(west()))
            return blank - 1;
        else
            return -1;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int b = blank >= 0 ? blank : getBlank();
        int nb = neighbour >= 0 ? neighbour : firstNeighbour();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (b == i * tiles.length + j)
                    tiles[i][j] = pq[nb];
                else if (nb == i * tiles.length + j)
                    tiles[i][j] = pq[b];
                else
                    tiles[i][j] = pq[i * tiles.length + j];
            }
        }
        return new Board(tiles);
    }

    private void validateArraySize(int size) {
        if (size < 2 || size >= 128)
            throw new IllegalArgumentException("invalid size of array: " + n);
    }

}
