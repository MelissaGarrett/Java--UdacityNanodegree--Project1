package menu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;

import java.util.*;

public class AdminMenu {
    static Scanner scanner;
    static int adminMenuSelection;
    static String roomNumber;
    static double roomPrice;
    static RoomType convertedRoomType;

    static int roomType;
    static String moreRooms = null;

    static List<IRoom> rooms = new ArrayList<IRoom>();

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

        if (allCustomers.isEmpty()) {
            System.out.println("There are no customers to display.");
        } else {
            for (Customer customer : allCustomers) {
                System.out.println(customer.toString());
            }
        }

        displayAdminMenu();
    }

    private static void seeAllRooms() {
        Collection<IRoom> allRooms = new ArrayList<IRoom>();

        allRooms = AdminResource.getInstance().getAllRooms();

        if (allRooms.isEmpty()) {
            System.out.println("There are no rooms to display.");
        } else {
            for (IRoom room : allRooms) {
                System.out.println(room.toString());
            }
        }

        displayAdminMenu();
    }

    private static void seeAllReservations() {
        ReservationService.getInstance().printAllReservation();

        displayAdminMenu();
    }

    private static void addARoom() {
        boolean validPrice = false, validRoomType = false;
        boolean addMoreRooms = true;

        Room room = new Room();

        try {
            System.out.println("\nEnter Room Number:");
            roomNumber = scanner.nextLine();
        } catch (Exception e) {

        } finally {

        }

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

                checkRoomType();

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

        room.setRoomNumber(roomNumber);
        room.setRoomPrice(roomPrice);
        room.setRoomType(convertedRoomType);
        rooms.add(room);

        while (addMoreRooms) {
            try {
                System.out.println("\nWould you like to add another room (y/n)?");
                moreRooms = scanner.nextLine().toUpperCase();

                checkMoreRooms();

                if (moreRooms.equals("Y")) {
                    addARoom();
                } else {
                    addMoreRooms = false;
                }
            } catch (Exception e) {
                System.out.println("ERROR: Invalid input");
            } finally {

            }
        }

        AdminResource.getInstance().addRoom(rooms);

        displayAdminMenu();
    }

    private static void checkRoomType() {
        while (roomType < 1 || roomType > 2) {
            System.out.println("ERROR: Invalid Room Type");
            try {
                System.out.println("Enter Room Type: (1) for Single bed, (2) for Double bed:");
                roomType = scanner.nextInt();
            } catch (Exception e) {

            } finally {

            }
        }
    }

    private static void checkMoreRooms() {
        while (!(moreRooms.equals("Y") || moreRooms.equals("N"))) {
            System.out.println("ERROR: Invalid response");
            try {
                System.out.println("Would you like to add another room (y/n)?");
                moreRooms = scanner.nextLine().toUpperCase();
            } catch (Exception e) {

            } finally {

            }
        }
    }

    private static void addTestData() {
        Room room1 = new Room("100", 100.0, RoomType.SINGLE);
        Room room2 = new Room("200", 125.0, RoomType.DOUBLE);
        Room room3 = new Room("300", 100.0, RoomType.SINGLE);
        Room room4 = new Room("400", 125.0, RoomType.DOUBLE);
        Room room5 = new Room("500", 100.0, RoomType.SINGLE);

        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        rooms.add(room4);
        rooms.add(room5);

        AdminResource.getInstance().addRoom(rooms);

        HotelResource.getInstance().createACustomer("max@gmail.com", "Max", "Mall");
        HotelResource.getInstance().createACustomer("jordan@gmail.com", "Jordan", "Jam");
        HotelResource.getInstance().createACustomer("alex@gmail.com", "Alex", "Apple");

        displayAdminMenu();
    }

    private static void backToMainMenu() {
        MainMenu.displayMainMenu();
    }
}
