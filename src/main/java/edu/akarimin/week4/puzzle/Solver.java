package edu.akarimin.week4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public final class Solver {

    private boolean isSolvable;
    private MinPQ<SearchNode> minPQ;
    private SearchNode solutionNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(final Board initial) {
        if (Objects.isNull(initial))
            throw new IllegalArgumentException("initial Board is null.");
        solutionNode = null;
        minPQ = new MinPQ<>();
        minPQ.insert(new SearchNode(null, initial, 0));

        while (true) {
            SearchNode currentNode = minPQ.delMin();
            Board current = currentNode.getBoard();
            if (current.isGoal()) {
                isSolvable = true;
                solutionNode = currentNode;
                break;
            }
            if (current.hamming() == 2 && current.twin().isGoal()) {
                isSolvable = false;
                break;
            }
            int moves = currentNode.getMoves();
            Board prevBoard = moves > 0 ? currentNode.prev.getBoard() : null;

            for (Board next: current.neighbors()) {
                if (Objects.nonNull(prevBoard) && next.equals(prevBoard))
                    continue;
                minPQ.insert(new SearchNode(currentNode, next,moves + 1));
            }

        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return isSolvable() ? solutionNode.getMoves() : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable())
            return null;
        Deque<Board> solution = new LinkedList<>();
        SearchNode node = solutionNode;
        while (Objects.nonNull(node)) {
            solution.addFirst(node.getBoard());
            node = node.getPrev();
        }
        return solution;
    }

    private static class SearchNode implements Comparable<SearchNode> {

        private final SearchNode prev;
        private final Board board;
        private final int moves;

        private SearchNode(SearchNode prev, Board board, int moves) {
            this.prev = prev;
            this.board = board;
            this.moves = moves;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority() - that.priority();
        }

        private int priority() {
            return board.manhattan() + moves;
        }

        public SearchNode getPrev() {
            return prev;
        }

        public Board getBoard() {
            return board;
        }

        public int getMoves() {
            return moves;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
    // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
