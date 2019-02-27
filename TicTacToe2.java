//package komlanattiogbe.csci220;

import java.util.Scanner;

public class TicTacToe2 {
	/**
	 * This is an implementation of the 
	 * classic tictactoe game
	 * 
	 *
	 * @author Komlan Attiogbe
	 * @version (02/24/19)
	 */

	public static String[][] GameBoard;
	public static String Player[] = {"X", "O"};
	public static String currPlayer;

	public static int ROW;
	public static int COL;
	
	public static Scanner scnr = new Scanner(System.in);
	public static Scanner scan = new Scanner(System.in);

	/*
	 * Initialize the GameBoard cells to "", essentially empty
	 */
	public static void TicTacToe() {
		GameBoard = new String[3][3];
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col ++) {
				GameBoard[row][col] = "";
			}
		}
		currPlayer = Player[0]; // X will play first
	}

	/*
	 * Checks if a move can be made at that location
	 */
	public static boolean isValid(int row, int col) {
		return row >= 0 && row < 3 && col >=0 && GameBoard[row][col] == "";
	}

	/*
	 * Makes sure that the player enters a valid input and checks if it is a tie 
	 * or if that player has won after their move.
	 */
	public static void move() {
		printBoard();
		boolean validMove = false;
		while (!validMove) {
			if (currPlayer == Player[0]) {
				System.out.print("Player 'X', please enter a move: ");	
			} else { // Player[1]
				System.out.print("Player 'O', please enter a move: ");
			}
			try {
				String input = scnr.nextLine().replaceAll(" ", "");
				if (input.length() > 3) {
					System.out.println("Invalid entry! Please try again. . .");
					continue;
				}
				String[] lst = input.split(",");
				if (lst.length > 2) {
					System.out.println("Invalid entry! Please try again. . .");
					continue;
				}
				int row = Integer.parseInt(lst[0]);
				int col = Integer.parseInt(lst[1]);
				if (isValid(row, col)) {
					ROW = row;
					COL = col;
					GameBoard[ROW][COL] = currPlayer;
					validMove = true; // break out of the loop
				} else {
					System.out.println("Invalid entry! Please try again. . .");
				}
			}
			catch (Exception e) {
				System.out.println("Invalid entry! Please try again. . .");
			}
		}
		if (isWinner(currPlayer, ROW, COL)) {
			printBoard();
			System.out.printf("'%s' wins!\n", currPlayer);
		}
		if (isTie()) {
			printBoard();
			System.out.println("It's a tie!");
		}
	}
	
	/*
	 * Prints the board with the current moves
	 * Alternatively, you could initialize the board to 3 sets of 
	 * '*' to avoid extra loops
	 */
	public static void printBoard() {
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col ++) {
				if (GameBoard[row][col] == "") {
					System.out.print(" * ");
				} else {
					System.out.printf(" %s ", GameBoard[row][col]);
				}
			}
			System.out.println();
			if (row != 2) {
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static boolean isWinner(String currPlayer, int currRow, int currCol) {
		// checks if the current player has won by checking the rows
		if (GameBoard[0][currCol].equals(currPlayer) && GameBoard[1][currCol].equals(currPlayer) && GameBoard[2][currCol].equals(currPlayer)) {
			return true;
		}
		// checks the columns to see if current player has won
		if (GameBoard[currRow][0].equals(currPlayer) && GameBoard[currRow][1].equals(currPlayer) && GameBoard[currRow][2].equals(currPlayer)) {
			return true;
		}
		// checks first diagonal
		if (currRow == currCol && GameBoard[0][0].equals(currPlayer) && GameBoard[1][1].equals(currPlayer) && GameBoard[2][2].equals(currPlayer)) {
			return true;
		}
		// checks next diagonal
		if (GameBoard[0][2].equals(currPlayer) && GameBoard[1][1].equals(currPlayer) && GameBoard[2][0].equals(currPlayer)) {
			return true;
		}
		return false;
	}
	
	/*
	 * Check if there is a tie by making sure all cells are filled
	 */
	public static boolean isTie() {
		for (int row = 0; row < 3; row ++) {
			for (int col = 0; col < 3; col ++) {
				if (GameBoard[row][col] == "") {
					return false; // unfilled cell, no tie
				}
			}
		} return true;
	}

	public static void main(String[] args) {
		TicTacToe();
		System.out.println("Let's play Tic-Tac-Toe!");
		System.out.println("Enter coordinates using this format: ROW[0-2],COLUMN[0-2]");
		boolean gameRunning = false;
		boolean playAgain = false;
		while (!playAgain) {
		while (!gameRunning) {
			move();
			if (isWinner(currPlayer, ROW, COL) || isTie()) {
				gameRunning = true;
			}
			if (currPlayer.equals(Player[0])) {
				currPlayer = Player[1];
			} else {
				currPlayer = Player[0];
			}
		}
		System.out.print("Play again? (y/n): ");
		char ans = scan.next().charAt(0);
		if (ans == 'y') {
			TicTacToe();
			gameRunning = false;
		} else {
			System.out.println("Goodbye!");
			playAgain = true;
		}
	}
	}
}

	

