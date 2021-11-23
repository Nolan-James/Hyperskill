package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] gameBoard = new char[3][3];
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                gameBoard[r][c] = ' ';
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
                    ArrayList<Integer> values = setAsciiValues(gameBoard);

                    if (turn > 3) {
                        boolean isNotFinished = checkIsNotFinished(gameBoard);
                        boolean isImpossible = checkIsImpossible(gameBoard);
                        boolean isImpossibleBothWin = checkIsImpossibleBothWin(values);
                        boolean isXWin = isXWin(values);
                        boolean isOWin = isOWin(values);
                        boolean isDraw = isDraw(isXWin, isOWin, isNotFinished);

                        if (isXWin) {
                            System.out.println("X wins");
                            break;
                        } else if (isOWin) {
                            System.out.println("O wins");
                            break;
                        } else if (isDraw) {
                            System.out.println("Draw");
                            break;
                        }
                    }
                    turn++;
                }

            } catch (Exception ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }

    private static boolean isDraw(boolean xWin, boolean oWin, boolean isNotFinished) {
        if (!xWin && !oWin && !isNotFinished) {
            return true;
        }
        return false;
    }

    private static boolean isOWin(ArrayList<Integer> values) {
        for (int value : values) {
            if (value == 237) {
                return true;
            }
        }
        return false;
    }

    private static boolean isXWin(ArrayList<Integer> values) {
        for (int value : values) {
            if (value == 264) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIsNotFinished(char[][] gameBoard) {
        boolean isFinished = false;

        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (gameBoard[r][c] == ' ') {
                    isFinished = true;
                    break;
                }
            }
        }
        return isFinished;
    }

    private static boolean checkIsImpossible(char[][] gameBoard) {
        boolean isImpossible = false;
        int countX = 0;
        int countO = 0;

        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (gameBoard[r][c] == 'X') {
                    countX++;
                } else if (gameBoard[r][c] == 'O') {
                    countO++;
                }
            }
        }
        int difference = Math.abs(countX - countO);
        return difference > 1;
    }

    private static boolean checkIsImpossibleBothWin(ArrayList<Integer> values) {
        int xWin = 0;
        int oWin = 0;

        for (int value : values) {
            if (value == 264) {
                xWin++;
            } else if (value == 237) {
                oWin++;
            }
        }

        if (xWin != 0 && oWin != 0) {
            if (xWin == oWin) {
                return true;
            }
        }

        return false;
    }

    private static ArrayList<Integer> setAsciiValues(char[][] gameBoard) {
        int asciiValueRow1 = getAsciiValueRow(gameBoard, 0);
        int asciiValueRow2 = getAsciiValueRow(gameBoard, 1);
        int asciiValueRow3 = getAsciiValueRow(gameBoard, 2);
        int asciiValueColumn1 = getAsciiValueColumn(gameBoard, 0);
        int asciiValueColumn2 = getAsciiValueColumn(gameBoard, 1);
        int asciiValueColumn3 = getAsciiValueColumn(gameBoard, 2);
        int asciiValueDiagonal1 = getAsciiValueDiagonal1(gameBoard);
        int asciiValueDiagonal2 = getAsciiValueDiagonal2(gameBoard);

        ArrayList<Integer> values = new ArrayList<>();
        values.add(asciiValueRow1);
        values.add(asciiValueRow2);
        values.add(asciiValueRow3);
        values.add(asciiValueColumn1);
        values.add(asciiValueColumn2);
        values.add(asciiValueColumn3);
        values.add(asciiValueDiagonal1);
        values.add(asciiValueDiagonal2);

        return values;

    }

    private static int getAsciiValueDiagonal2(char[][] gameBoard) {
        int result = 0;
        int last = gameBoard.length - 1;

        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[row].length; column++) {
                if (row == row && column == last) {
                    result += gameBoard[row][column];
                }
            }
            last -= 1;
        }
        return result;
    }

    private static int getAsciiValueDiagonal1(char[][] gameBoard) {
        int result = 0;
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = 0; column < gameBoard[row].length; column++) {
                if (row == column) {
                    result += gameBoard[row][column];
                }
            }
        }
        return result;
    }

    private static int getAsciiValueColumn(char[][] gameBoard, int i) {
        int result = 0;
        for (int row = 0; row < gameBoard.length; row++) {
            for (int column = i; column < i + 1; column++) {
                result += gameBoard[row][column];
            }
        }
        return result;
    }

    private static int getAsciiValueRow(char[][] gameBoard, int i) {
        int result = 0;
        for (int row = i; row < i + 1; row++) {
            for (int column = 0; column < gameBoard.length; column++) {
                result += gameBoard[row][column];
            }
        }
        return result;
    }

    private static void showGameBoard(char[][] gameBoard) {
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

    private static boolean playXMove(char[][] gameBoard, int rowChoice, int columnChoice, int turn) {
        rowChoice -= 1;
        columnChoice -= 1;
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (!(gameBoard[rowChoice][columnChoice] == 'X' || gameBoard[rowChoice][columnChoice] == 'O')) {
                    if (turn % 2 == 0) {
                        gameBoard[rowChoice][columnChoice] = 'X';
                    } else {
                        gameBoard[rowChoice][columnChoice] = 'O';
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


    private static void printEmptyGrid(char[][] gameBoard) {
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
