package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "\nReservation\n" +
                customer.getFirstName() + " " + customer.getLastName() + "\n" +
                "Room " + room.getRoomNumber() + " " + room.getRoomType() + " bed\n" +
                "Price $" + room.getRoomPrice() + " per night\n" +
                "Check in Date " + checkInDate + "\n" +
                "Check out Date " + checkOutDate;
    }
}
