package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService(); // a Singleton

    private ReservationService() {} // private constructor

    public static ReservationService getInstance() { return reservationService; }

    List<Reservation> allReservations = new ArrayList<Reservation>();
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

        for (Reservation res : allReservations) {
            if (checkInDate.after(res.getCheckOutDate()) ||
                    checkOutDate.before(res.getCheckInDate())) {
                availRooms.add(getARoom(res.getRoom().getRoomNumber()));
            }
        }

        return availRooms;
    }

    void getReservedRooms() {
        for (Reservation res : allReservations) {
            reservedRooms.add(res.getRoom());
        }
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.add(reservation);

        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        Collection<Reservation> custReservations = new ArrayList<Reservation>();

        for (Reservation res : allReservations) {
            if (res.getCustomer().getEmail().equals(customer.getEmail())) {
                custReservations.add(res);
            }
        }

        return custReservations;
    }

    public void printAllReservation() {
        if (allReservations.isEmpty()) {
            System.out.println("There are no reservations to display.");
        } else {
            for (Reservation res : allReservations) {
                System.out.println(res);
            }
        }
    }
}
