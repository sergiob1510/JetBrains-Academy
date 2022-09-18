package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    static int numberOfRows;
    static int seatsPerRow;
    static int totalSeats;
    static int purchasedTickets = 0;
    static int currentIncome = 0;
    static char[][] cinemaDisplay;

    public static void main(String[] args) {
        defineCinema();
        mainMenu();
    }

    public static void defineCinema() {
        System.out.println("Enter the number of rows:");
        numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = scanner.nextInt();
        totalSeats = numberOfRows * seatsPerRow;
        createCinemaArray(numberOfRows, seatsPerRow);
    }
    public static void createCinemaArray(int rows, int seats) {
        cinemaDisplay = new char[rows][seats];
        for (char[] row : cinemaDisplay) {
            Arrays.fill(row, 'S');
        }
    }
    public static void printCinema() {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 0; i < seatsPerRow; i++) {
            System.out.printf("%d ", i+1);
        }
        System.out.print("\n");
        for (int i = 0; i < cinemaDisplay.length; i++) {
            System.out.println(i+1 + " " + Arrays.toString(cinemaDisplay[i]).replaceAll("[,\\[\\]]", ""));
        }
    }
    public static void buyTicketMenu() {
        boolean clientBuying = true;
        do {
            System.out.println("Enter a row number:");
            int rowToBuy = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatToBuy = scanner.nextInt();
            if (isOperationPossible(rowToBuy, seatToBuy) && buyTicketOperation(rowToBuy, seatToBuy)) {
                clientBuying = false;
            }
        } while (clientBuying);


    }
    public static boolean isOperationPossible(int row, int seat) {
        if (row <= numberOfRows && seat <= seatsPerRow) return true;
        System.out.println("Wrong input!");
        return false;
    }
    public static boolean buyTicketOperation(int row, int seat) {
        if (cinemaDisplay[row - 1][seat - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            return false;
        } else {
            int price = ticketPriceCalculator(row);
            System.out.println("Ticket price: $" + price);
            cinemaDisplay[row - 1][seat - 1] = 'B';
            purchasedTickets++;
            currentIncome += price;
            return true;
        }
    }
    public static int ticketPriceCalculator(int row) {
        if (numberOfRows * seatsPerRow <= 60) return 10;
        if (numberOfRows % 2 == 0) {
            if (row > (numberOfRows / 2)) return 8;
        } else {
            if (row > (Math.floor((double) numberOfRows / 2))) return 8;
        }
        return 10;
    }
    public static int getTotalIncome() {
        if (totalSeats <= 60) return totalSeats * 10;
        if (numberOfRows % 2 == 0) return (numberOfRows / 2 * seatsPerRow * 10 + numberOfRows / 2 * seatsPerRow * 8);
        return (int) (Math.floor((double)numberOfRows/2) * seatsPerRow * 10 + Math.ceil((double)numberOfRows/2) * seatsPerRow * 8);
    }
    public static void showStatistics() {
        System.out.printf("""
                        Number of purchased tickets: %d \n
                        Percentage: %.2f%% \n
                        Current income $%d \n
                        Total income $%d \n
                        """, purchasedTickets, (float) purchasedTickets*100/totalSeats, currentIncome, getTotalIncome());
    }
    public static void mainMenu() {
        boolean menuOn = true;
        while (menuOn) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            switch(option) {
                case 1 -> printCinema();
                case 2 -> buyTicketMenu();
                case 3 -> showStatistics();
                case 0 -> menuOn = false;
                default -> System.out.println("Invalid option. Please retry");
            }
        }
    }
}
