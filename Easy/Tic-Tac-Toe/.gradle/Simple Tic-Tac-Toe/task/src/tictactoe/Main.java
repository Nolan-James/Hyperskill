package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> values = new ArrayList<>();
        String[][] gameBoard = new String[3][3];
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                gameBoard[r][c] = " ";
            }
        }

        printEmptyGrid(gameBoard);

        int turn = 0;
        while (true) {

            int rowChoice = 0;
            int columnChoice = 0;
            System.out.println("Enter the coordinates:");
            String coords = scanner.nextLine();
            String[] parts = coords.split(" ");
            try {
                rowChoice = Integer.parseInt(parts[0]);
                columnChoice = Integer.parseInt(parts[1]);
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            try {

                boolean result = playXMove(gameBoard, rowChoice, columnChoice, turn);

                if (result) {
                    showGameBoard(gameBoard);
                    getAsciiValue(gameBoard, )
                    if (turn > 3) {

                    }
                    turn++;

                }
//                System.out.println("This cell is occupied! Choose another one!");


            } catch (Exception ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

    private static void showGameBoard(String[][] gameBoard) {
        System.out.println("---------");
        for (int row = 0; row < gameBoard.length; row++) {
            System.out.print("| ");
            for (int column = 0; column < gameBoard[row].length; column++) {
                System.out.print(gameBoard[row][column] + " ");
            }
            System.out.print("| ");
            System.out.println();
        }
        System.out.println("---------");
    }

    private static boolean playXMove(String[][] gameBoard, int rowChoice, int columnChoice, int turn) {
        rowChoice -= 1;
        columnChoice -= 1;
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (!(Objects.equals(gameBoard[rowChoice][columnChoice], "X") || Objects.equals(gameBoard[rowChoice][columnChoice], "O"))) {
                    if (turn % 2 == 0) {
                        gameBoard[rowChoice][columnChoice] = "X";
                    } else {
                        gameBoard[rowChoice][columnChoice] = "O";
                    }
                    return true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    return false;
                }
            }
        }
        return false;
    }


    private static void printEmptyGrid(String[][] gameBoard) {
        System.out.println("---------");
        for (int row = 0; row < gameBoard.length; row++) {
            System.out.print("| ");
            for (int column = 0; column < gameBoard[row].length; column++) {
                System.out.print(" " + " ");
            }
            System.out.print("| ");
            System.out.println();
        }
        System.out.println("---------");
    }

}
