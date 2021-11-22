package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] gameBoard = new char[3][3];

        System.out.println("Enter cells: ");
        String cells = scanner.nextLine();
        char[] moves = cells.toCharArray();

        populateGameBoard(gameBoard, moves);
        showGameBoard(gameBoard);

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
            }

            try {
                boolean result = playXMove(gameBoard, moves, rowChoice, columnChoice);

                if (result) {
                    showGameBoard(gameBoard);
                    break;
                }
                System.out.println("This cell is occupied! Choose another one!");


            } catch (Exception ex) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }


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


        boolean isNotFinished = checkIsNotFinished(moves);
        boolean isImpossible = checkIsImpossible(moves);
        boolean isImpossibleBothWin = checkIsImpossibleBothWin(values);

        boolean isXWin = isXWin(values);
        boolean isOWin = isOWin(values);
        boolean isDraw = isDraw(isXWin, isOWin, isNotFinished);

        if (isImpossibleBothWin) {
            System.out.println("Impossible");
        } else if (isImpossible) {
            System.out.println("Impossible");
        } else if (isXWin) {
            System.out.println("X wins");
        } else if (isOWin) {
            System.out.println("O wins");
        } else if (isDraw) {
            System.out.println("Draw");
        } else if (isNotFinished) {
            System.out.println("Game not finished");
        } else if (!isNotFinished) {
            System.out.println("");
        }
    }

    private static boolean playXMove(char[][] gameBoard, char[] moves, int rowChoice, int columnChoice) {
        rowChoice -= 1;
        columnChoice -= 1;
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                if (!(gameBoard[rowChoice][columnChoice] == 'X' || gameBoard[rowChoice][columnChoice] == 'O')) {
                    gameBoard[rowChoice][columnChoice] = 'X';
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
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

    private static boolean isXWin(ArrayList<Integer> values) {
        for (int value : values) {
            if (value == 264) {
                return true;
            }
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

    private static boolean isDraw(boolean xWin, boolean oWin, boolean isNotFinished) {
        if (!xWin && !oWin && !isNotFinished) {
            return true;
        }

        return false;
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

    private static int getAsciiValueRow(char[][] gameBoard, int i) {
        int result = 0;
        for (int row = i; row < i + 1; row++) {
            for (int column = 0; column < gameBoard.length; column++) {
                result += gameBoard[row][column];
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

    private static void populateGameBoard(char[][] gameBoard, char[] moves) {
        int move = 0;
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                gameBoard[r][c] = moves[move];
                move++;
            }
        }
    }

    private static boolean checkIsImpossible(char[] moves) {
        boolean isImpossible = false;
        int countX = 0;
        int countO = 0;

        for (char move : moves) {
            if (move == 'X') {
                countX++;
            } else if (move == 'O') {
                countO++;
            }
        }

        int difference = Math.abs(countX - countO);

        return difference > 1;

    }

    private static boolean checkIsNotFinished(char[] moves) {
        boolean isFinished = false;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i] == '_') {
                isFinished = true;
                break;

            }
        }
        return isFinished;
    }

}
