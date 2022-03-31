package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.exit;

public class MainMenu {
    static Scanner scanner;
    static int mainMenuSelection;
    static String userEmail;
    static String userFirstName;
    static String userLastName;
    static String checkInInput;
    static String checkOutInput;
    static Date checkInDate;
    static Date checkOutDate;

    static String bookARoomInput = null;
    static String haveAnAccountInput = null;

    static private final String emailRegex = "^(.+)@(.+).com$";
    static Pattern pattern = Pattern.compile(emailRegex);
    Matcher matcher;

    static Collection<IRoom> allRooms = new ArrayList<IRoom>();

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
        boolean validInDate = false, validOutDate = false;
        boolean bookRoom = true, haveAccount = true;
        String roomNumberInput = null;

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        while (validInDate == false){
            try {
                System.out.println("\nEnter Check-in Date (mm/dd/yyyy):");
                checkInInput = scanner.nextLine();

                checkInDate = formatter.parse(checkInInput);

                validInDate = true;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
            } finally {

            }
        }

        while (validOutDate == false){
            try {
                System.out.println("Enter Check-out Date (mm/dd/yyyy):");
                checkOutInput = scanner.nextLine();

                checkOutDate = formatter.parse(checkOutInput);

                validOutDate = true;

            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
            } finally {

            }
        }

        allRooms = HotelResource.getInstance().findARoom(checkInDate, checkOutDate);

        for (IRoom room : allRooms) {
            System.out.println(room.toString());
        }

        while (bookRoom) {
            try {
                System.out.println("\nWould you like to book a room (y/n)?");
                bookARoomInput = scanner.nextLine().toUpperCase();

                checkBookingInput();

                if (bookARoomInput.equals("Y")) {
                    while (haveAccount) {
                        try {
                            System.out.println("Do you have an account with us (y/n)?");
                            haveAnAccountInput = scanner.nextLine().toUpperCase();

                            checkAccountInput();

                            if (haveAnAccountInput.equals("Y")) {
                                try {
                                    System.out.println("Enter Email Address (name@domain.com):");
                                    userEmail = scanner.nextLine();

                                    checkEmailAddress();

                                    System.out.println("What room would you like to reserve?");
                                    roomNumberInput = scanner.nextLine();
                                    IRoom room = HotelResource.getInstance().getRoom(roomNumberInput);

                                    HotelResource.getInstance().bookARoom(userEmail, room, checkInDate, checkOutDate);

                                    seeMyReservations();

                                    bookRoom = false;
                                    haveAccount = false;
                                } catch (Exception e) {
                                    System.out.println("ERROR: Invalid input");
                                    scanner.nextLine();
                                } finally {

                                }
                            } else {
                                System.out.println("Please create an account first from the Main Menu");

                                haveAccount = false;
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR: Invalid input");
                            scanner.nextLine();
                        } finally {

                        }
                    }
                } else {
                    bookRoom = false;
                }

                bookRoom = false;

            } catch (Exception e) {
                System.out.println("ERROR: invalid input");
            } finally {

            }
        }

        displayMainMenu();
    }

    private static void seeMyReservations() {
        Collection<Reservation> myReservations = new ArrayList<Reservation>();

        myReservations = HotelResource.getInstance().getCustomersReservations(userEmail);

        if (myReservations.isEmpty()) {
            System.out.println("You have no reservations yet.");
        } else {
            for (Reservation reservation : myReservations) {
                System.out.println(reservation.toString());
            }
        }

        displayMainMenu();
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

            HotelResource.getInstance().createACustomer(userEmail, userFirstName, userLastName);

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

    private static void checkBookingInput() {
        while (!(bookARoomInput.equals("Y") || bookARoomInput.equals("N"))) {
            System.out.println("ERROR: Invalid response");
            try {
                System.out.println("Would you like to book a room (y/n)?");
                bookARoomInput = scanner.nextLine().toUpperCase();
            } catch (Exception e) {
                scanner.nextLine();
            } finally {

            }
        }
    }

    private static void checkAccountInput() {
        while (!(haveAnAccountInput.equals("Y") || haveAnAccountInput.equals("N"))) {
            System.out.println("ERROR: Invalid response");
            try {
                System.out.println("Do you have an account with us (y/n)?");
                haveAnAccountInput = scanner.nextLine().toUpperCase();
            } catch (Exception e) {
                scanner.nextLine();
            } finally {

            }
        }
    }

    private static void viewAdminMenu() {
        AdminMenu.displayAdminMenu();
    }
}
