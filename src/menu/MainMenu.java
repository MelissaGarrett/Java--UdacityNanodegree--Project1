package menu;

import java.util.Scanner;

import static java.lang.System.exit;

public class MainMenu {
    static Scanner scanner;
    static int selection;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayMainMenu();
        }
    }

    private static void displayMainMenu() {
        try {
            System.out.println("\nWelcome to the Hotel Reservation Application");
            System.out.println("--------------------------------------------\n");
            System.out.println("1.  Find and reserve a room");
            System.out.println("2.  See my reservations");
            System.out.println("3.  Create an account");
            System.out.println("4.  Admin");
            System.out.println("5.  Exit");

            System.out.println("\nPlease enter a number (1-5)");
            selection = Integer.parseInt(scanner.nextLine());

            processSelection();
        } catch (Exception e) {
            System.out.println("Invalid input");
        } finally {
            scanner.close();
        }
    }

    private static void processSelection() {
        switch (selection) {
            case 1:

            case 2:

            case 3:

            case 4:
                viewAdminMenu();
            case 5:
                exit(0);
            default:
                displayMainMenu();
        }
    }

    private static void findReserveRoom() {

    }

    private static void seeMyReservations() {

    }

    private static void createAccount() {

    }

    private static void viewAdminMenu() {
        AdminMenu.displayAdminMenu();
    }
}
