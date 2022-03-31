package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService(); // a Singleton

    private ReservationService() {} // private constructor

    public static ReservationService getInstance() { return reservationService; }

    Map<String, Reservation> allReservations = new HashMap<String, Reservation>();
    Map<String, IRoom> allRooms = new HashMap<String, IRoom>();

    Collection<IRoom> reservedRooms = new ArrayList<IRoom>();

    public void addRoom(IRoom room) {
        if (!allRooms.containsKey(room.getRoomNumber())) {
            allRooms.put(room.getRoomNumber(), room);
        }
    }

    public IRoom getARoom(String roomID) {
        IRoom room = allRooms.get(roomID);

        return room;
    }

    public Collection<IRoom> getAllRooms() {
        Collection<IRoom> allRms = new ArrayList<IRoom>();

        for (Map.Entry<String, IRoom> entry: allRooms.entrySet()) {
            allRms.add(entry.getValue());
        }

        return allRms;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availRooms = new ArrayList<IRoom>();

        getReservedRooms();

        for (Map.Entry<String, IRoom> roomsEntry: allRooms.entrySet()) {
            if (!reservedRooms.contains(getARoom(roomsEntry.getKey()))) {
                availRooms.add(getARoom(roomsEntry.getKey()));
            }
        }

        return availRooms;

//            for (Map.Entry<String, IRoom> roomsEntry: allRooms.entrySet()) {
//            if (allReservations.entrySet().contains(getARoom(roomsEntry.getKey()))) {
//                System.out.println("IF");
//                for (Map.Entry<String, Reservation> reservationEntry : allReservations.entrySet()) {
//                    if (checkInDate.after(reservationEntry.getValue().getCheckOutDate()) ||
//                        checkOutDate.before(reservationEntry.getValue().getCheckInDate())) {
//                            availRooms.add(getARoom(roomsEntry.getKey()));
//                    }
//                }
//            } else {
//                System.out.println("ELSE");
//                availRooms.add(getARoom(roomsEntry.getKey()));
//            }
//        }
//        return availRooms;
    }

    void getReservedRooms() {
        for (Map.Entry<String, Reservation> reservationsEntry: allReservations.entrySet()) {
            reservedRooms.add(reservationsEntry.getValue().getRoom());
        }
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.put(customer.getEmail(), reservation);

        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> custReservations = new ArrayList<Reservation>();

        for (Map.Entry<String, Reservation> entry : allReservations.entrySet()) {
            if (entry.getKey().equals(customer.getEmail())) {
                custReservations.add(entry.getValue());
            }
        }
        return custReservations;
    }

    public void printAllReservation() {
        if (allReservations.isEmpty()) {
            System.out.println("There are no reservations to display.");
        } else {
            for (Map.Entry<String, Reservation> entry : allReservations.entrySet()) {
                System.out.println(entry.getValue());
            }
        }
    }
}
