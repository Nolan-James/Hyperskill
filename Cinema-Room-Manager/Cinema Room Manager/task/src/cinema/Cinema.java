package cinema;

import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    private int ticketsSold = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        String[][] seatArray = new String[numOfRows][seatsInRow];
        initializeSeatingPlan(seatArray);
        int command = -1;
        int ticketsSold = 0;
        int totalTicketPrice = 0;
        int rowNumber = -1;
        int seatNumber = -1;

        while (command != 0) {
            showMenu(scanner);

            command = scanner.nextInt();

            switch (command) {
                case 1:
                    showSeats(seatArray);
                    break;
                case 2:
                    int[] rowNumberAndSeatNumber = getRowAndSeatNumber(scanner);
                    rowNumber = rowNumberAndSeatNumber[0];
                    seatNumber = rowNumberAndSeatNumber[1];
                    while (true) {
                        if (rowNumber > seatArray.length || seatNumber > seatArray[1].length) {
                            System.out.println("Wrong input!");
                        }

                        int ticketPrice = calculateTicketPrice(numOfRows, seatsInRow, rowNumber);
                        if (buyTicket(seatArray, rowNumber, seatNumber)) {
                            ticketsSold++;
                            totalTicketPrice += ticketPrice;
                            System.out.println("Ticket price: $" + ticketPrice);
                            break;
                        } else {
                            rowNumberAndSeatNumber = getRowAndSeatNumber(scanner);
                            rowNumber = rowNumberAndSeatNumber[0];
                            seatNumber = rowNumberAndSeatNumber[1];
                            ticketPrice = calculateTicketPrice(numOfRows, seatsInRow, rowNumber);
                        }
                    }
                    break;
                case 3:
                    getNumberOfPurchasedTickets(ticketsSold);
                    getPercentageOfTicketsSold(seatArray, ticketsSold);
                    getCurrentIncome(totalTicketPrice);
                    getTotalIncome(numOfRows, seatsInRow, rowNumber);
                    break;
                case 0:
                    break;
            }
        }

    }

    private static void getTotalIncome(int numOfRows, int seatsInRow, int rowNumber) {
        int totalIncome = 0;
        int firstHalfOfRows = 0;
        int secondHalfOfRows = 0;
        if (numOfRows * seatsInRow <= 60) {
            totalIncome = (numOfRows * seatsInRow) * 10;
            System.out.println("Total income: $" + totalIncome);
        } else {
            firstHalfOfRows = (int) Math.floor(numOfRows / 2);
            secondHalfOfRows = numOfRows - firstHalfOfRows;
            totalIncome = (firstHalfOfRows * seatsInRow) * 10 +
                    (secondHalfOfRows * seatsInRow) * 8;

            System.out.println("Total income: $" + totalIncome);
        }
    }

    private static void getCurrentIncome(int totalTicketPrice) {
        System.out.println("Current income: $" + totalTicketPrice);
    }

    private static void getPercentageOfTicketsSold(String[][] seatArray, int ticketsSold) {
        int seats = seatArray.length * seatArray[0].length;
        double percentage = (double) ticketsSold / seats * 100;

        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
    }

    private static void getNumberOfPurchasedTickets(int ticketsSold) {
        System.out.println("Number of purchased tickets: " + ticketsSold);
    }

    private static int[] getRowAndSeatNumber(Scanner scanner) {
        System.out.println("Enter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        return new int[]{rowNumber, seatNumber};
    }

    private static int calculateTicketPrice(int numOfRows, int seatsInRow, int rowNumber) {
        int ticketPrice = 10;
        int firstHalfOfRows = 0;
        int secondHalfOfRows = 0;

        if (numOfRows * seatsInRow <= 60) {
            return ticketPrice;
        } else {
            firstHalfOfRows = (int) Math.floor(numOfRows / 2);
            secondHalfOfRows = numOfRows - firstHalfOfRows;
        }

        if (rowNumber <= firstHalfOfRows) {
            ticketPrice = 10;
            return ticketPrice;
        } else {
            ticketPrice = 8;
            return ticketPrice;
        }
    }

    private static void initializeSeatingPlan(String[][] seatArray) {
        for (int row = 0; row < seatArray.length; row++) {
            for (int column = 0; column < seatArray[row].length; column++) {
                seatArray[row][column] = "S";
            }
        }
    }

    private static void showSeats(String[][] seatArray) {
        System.out.println("Cinema:");

        for (int i = 0; i <= seatArray.length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println("");

        for (int row = 0; row < seatArray.length; row++) {
            System.out.print(row + 1);
            System.out.print(" ");
            for (int column = 0; column < seatArray[row].length; column++) {
                System.out.print(seatArray[row][column] + " ");
            }
            System.out.println();
        }
    }

    private static boolean buyTicket(String[][] seatArray, int rowNumber, int seatNumber) {
        for (int row = 0; row < seatArray.length; row++) {
            for (int column = 0; column < seatArray[row].length; column++) {
                if (row == rowNumber - 1 && column == seatNumber - 1) {
                    if (Objects.equals(seatArray[row][column], "B")) {
                        System.out.println("That ticket has already been purchased!");
                        return false;
                    } else {
                        seatArray[row][column] = "B";
                        return true;
                    }
                }
            }
        }
        return true;
    }


    private static void showMenu(Scanner scanner) {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
}