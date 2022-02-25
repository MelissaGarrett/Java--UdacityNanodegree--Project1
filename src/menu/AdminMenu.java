package menu;

import java.util.Scanner;

public class AdminMenu {
    static Scanner scanner;
    static int selection;

    static void displayAdminMenu() {
        try {
            System.out.println("\n1.  See all customers");
            System.out.println("2.  See all rooms");
            System.out.println("3.  See all reservations");
            System.out.println("4.  Add a room");
            System.out.println("5.  Add test data");
            System.out.println("6. Back to main menu");

            System.out.println("\nPlease enter a number (1-6)");
            selection = Integer.parseInt(scanner.nextLine());

            processSelection();
        } catch (Exception e) {
            System.out.println("Invalid input.");
        } finally {

        }
    }

    private static void processSelection() {
        switch (selection) {
            case 1:
                seeAllCustomers();
                break;
            case 2:
                seeAllRooms();
                break;
            case 3:
                seeAllReservations();
                break;
            case 4:
                addARoom();
                break;
            case 5:
                addTestData();
                break;
            case 6:
                backToMainMenu();
                break;
            default:
                displayAdminMenu();
        }
    }

    private static void seeAllCustomers() {

    }

    private static void seeAllRooms() {

    }

    private static void seeAllReservations() {

    }

    private static void addARoom() {

    }

    private static void addTestData() {

    }

    private static void backToMainMenu() {
        MainMenu.displayMainMenu();
    }
}
