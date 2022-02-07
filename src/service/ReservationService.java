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

    public void addRoom(IRoom room) {
        if (!allRooms.containsKey(room.getRoomNumber())) {
            allRooms.put(room.getRoomNumber(), room);
        }
    }

    public IRoom getARoom(String roomID) {
        IRoom room = allRooms.get(roomID);

        return room;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availRooms = new ArrayList<IRoom>();

        for (Map.Entry<String, Reservation> entry: allReservations.entrySet()) {
            if (checkInDate.after(entry.getValue().getCheckOutDate()) ||
                    checkOutDate.before(entry.getValue().getCheckInDate()))
            {
                availRooms.add(getARoom(entry.getKey()));
            }
        }
        return availRooms;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.put(customer.getEmail(), reservation);

        return reservation;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> custReservations = new ArrayList<Reservation>();

        for (Map.Entry<String, Reservation> entry : allReservations.entrySet()) {
            if (entry.getKey().equals(customer.getEmail())) {
                custReservations.add(entry.getValue());
            }
        }
        return custReservations;
    }

    public void printAllReservation() {
        for (Map.Entry<String, Reservation> entry: allReservations.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
