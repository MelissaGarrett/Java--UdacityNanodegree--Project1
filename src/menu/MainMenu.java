package menu;

import api.HotelResource;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.exit;

public class MainMenu {
    static Scanner scanner;
    static int mainMenuSelection;
    static String userEmail;
    static String userFirstName;
    static String userLastName;

    static private final String emailRegex = "^(.+)@(.+).com$";
    static Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher;

    public static void main(String[] args) {
        displayMainMenu();
    }

    static void displayMainMenu() {
        try {
            scanner = new Scanner (System.in);

            System.out.println("\nWelcome to the Hotel Reservation Application");
            System.out.println("--------------------------------------------\n");
            System.out.println("1.  Find and reserve a room");
            System.out.println("2.  See my reservations");
            System.out.println("3.  Create an account");
            System.out.println("4.  Admin");
            System.out.println("5.  Exit");

            System.out.println("\nPlease enter a number (1-5):");
            mainMenuSelection = Integer.parseInt(scanner.nextLine());

            processSelection();
        } catch (Exception e) {
            System.out.println("ERROR: Invalid input");
        } finally {
            scanner.close();
        }
    }

    private static void processSelection() {
        switch (mainMenuSelection) {
            case 1:
                findReserveRoom();
                break;
            case 2:
                seeMyReservations();
                break;
            case 3:
                createAccount();
                break;
            case 4:
                viewAdminMenu();
                break;
            case 5:
                scanner.close();
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
        try {
            System.out.println("\nEnter Email Address (name@domain.com):");
            userEmail = scanner.nextLine();

            checkEmailAddress();

            System.out.println("Enter First Name:");
            userFirstName = scanner.nextLine();

            System.out.println("Enter Last Name:");
            userLastName = scanner.nextLine();

            HotelResource.getInstance().createACustomer(userEmail, userEmail, userLastName);

            displayMainMenu();
        } catch (Exception e){
            System.out.println("ERROR: Invalid input");
        } finally {

        }
    }

    private static void checkEmailAddress() {
        while (!pattern.matcher(userEmail).matches()) {
            System.out.println("ERROR: Invalid email address");

            try {
                System.out.println("\nEnter Email Address (name@domain.com):");
                userEmail = scanner.nextLine();
            } catch (Exception e) {

            } finally {

            }
        }
    }

    private static void viewAdminMenu() {
        AdminMenu.displayAdminMenu();
    }
}
