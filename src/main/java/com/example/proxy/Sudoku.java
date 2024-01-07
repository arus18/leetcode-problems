package com.example.proxy;

public class Sudoku {

    public static void main(String[] args) {
        int[][] sudokuBoard = {
                {0, 0, 0},
                {0, 3, 0},
                {0, 0, 0}
        };

        if (solveSudoku(sudokuBoard)) {
            System.out.println("Sudoku solution:");
            printBoard(sudokuBoard);
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static boolean solveSudoku(int[][] board) {
        return solve(board);
    }

    private static boolean solve(int[][] board) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 3; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }

                            board[row][col] = 0; // Backtrack if placing 'num' doesn't lead to a solution
                        }
                    }
                    return false; // No valid number found for this cell
                }
            }
        }
        return true; // All cells filled, puzzle solved
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if 'num' is not present in the current row, column, and 3x3 subgrid
        return !usedInRow(board, row, num) && !usedInCol(board, col, num);
    }

    private static boolean usedInRow(int[][] board, int row, int num) {
        for (int col = 0; col < 3; col++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean usedInCol(int[][] board, int col, int num) {
        for (int row = 0; row < 3; row++) {
            if (board[row][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

