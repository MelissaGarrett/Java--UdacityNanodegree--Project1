package menu;

import api.AdminResource;
import model.Customer;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    static Scanner scanner;
    static int adminMenuSelection;
    static int roomNumber;
    static double roomPrice;
    static RoomType convertedRoomType;

    static void displayAdminMenu() {
        try {
            scanner = new Scanner(System.in);

            System.out.println("\nAdmin Menu");
            System.out.println("----------\n");
            System.out.println("1.  See all customers");
            System.out.println("2.  See all rooms");
            System.out.println("3.  See all reservations");
            System.out.println("4.  Add a room");
            System.out.println("5.  Add test data");
            System.out.println("6.  Back to main menu");

            System.out.println("\nPlease enter a number (1-6):");
            adminMenuSelection = Integer.parseInt(scanner.nextLine());

            processSelection();
        } catch (Exception e) {
            System.out.println("ERROR: Invalid input");
        } finally {
            scanner.close();
        }
    }

    private static void processSelection() {
        switch (adminMenuSelection) {
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
        Collection<Customer> allCustomers = new ArrayList<Customer>();

        allCustomers = AdminResource.getInstance().getAllCustomers();

        for (Customer customer : allCustomers) {
            System.out.println("\nFirst Name: " + customer.getFirstName() + " Last Name: " + customer.getLastName() +
                    " Email: " + customer.getEmail());
        }

        displayAdminMenu();
    }

    private static void seeAllRooms() {

    }

    private static void seeAllReservations() {

    }

    private static void addARoom() {
        boolean validRoomNumber = false, validPrice = false, validRoomType = false;
        int roomType;
        boolean addMoreRooms = true;
        String moreRooms = null;

        while (validRoomNumber == false) {
            try {
                System.out.println("\nEnter Room Number:");
                roomNumber = scanner.nextInt();

                validRoomNumber = true;
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
                scanner.nextLine();

            } finally {

            }
        }
        scanner.nextLine(); // to clear buffer

        while (validPrice == false) {
            try {
                System.out.println("Enter Price per Night:");
                roomPrice = scanner.nextDouble();

                validPrice = true;
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
                scanner.nextLine();
            } finally {

            }
        }
        scanner.nextLine();

        while (validRoomType == false) {
            try {
                System.out.println("Enter Room Type: (1) for Single bed, (2) for Double bed:");
                roomType = scanner.nextInt();

                checkRoomType(roomType);

                if (roomType == 1) {
                    convertedRoomType = RoomType.SINGLE;
                } else {
                    convertedRoomType = RoomType.DOUBLE;
                }

                validRoomType = true;
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
                scanner.nextLine();
            } finally {

            }
        }
        scanner.nextLine();

        // TODO: Save off in a Room object

        while (addMoreRooms) {
            try {
                System.out.println("\nWould you like to add another room (Y/N)?");
                moreRooms = scanner.nextLine().toUpperCase();

                checkMoreRooms(moreRooms);

                if (moreRooms.equals("Y")) {
                    addARoom();
                } else {
                    addMoreRooms = false;
                }
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
                scanner.nextLine();
            } finally {

            }
        }

        //AdminResource.getInstance().addRoom();

        displayAdminMenu();
    }

    private static void checkRoomType(int roomType) {
        while (roomType < 1 || roomType > 2) {
            System.out.println("ERROR: Invalid Room Type");
            try {
                System.out.println("Enter Room Type: (1) for Single bed, (2) for Double bed:");
                roomType = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
            } finally {

            }
        }
    }

    private static void checkMoreRooms(String moreRooms) {
        while (!(moreRooms.equals("Y") || moreRooms.equals("N"))) {
            System.out.println("ERROR: Invalid response");
            try {
                System.out.println("Would you like to add another room (Y/N)?");
                moreRooms = scanner.nextLine().toUpperCase();
            } catch (Exception e) {
                scanner.nextLine();
            } finally {

            }
        }
    }

    private static void addTestData() {

    }

    private static void backToMainMenu() {
        MainMenu.displayMainMenu();
    }
}
