package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService = new ReservationService(); // a Singleton

    private ReservationService() {} // private constructor

    public static ReservationService getInstance() { return reservationService; }

    Reservation reservation;
    Map<Customer, Reservation> reservations = new HashMap<Customer, Reservation>();

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> custReservations = new ArrayList<Reservation>();

        for (Map.Entry<Customer, Reservation> entry : reservations.entrySet()) {
            if (entry.getKey().equals(customer)) {
                custReservations.add(entry.getValue());
            }
        }
        return custReservations;
    }

    public void printAllReservation() {
        System.out.println(reservations);
    }
}
