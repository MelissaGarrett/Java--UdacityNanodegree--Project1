package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return customer.equals(that.customer) && room.equals(that.room) &&
                getCheckInDate().equals(that.getCheckInDate()) && getCheckOutDate().equals(that.getCheckOutDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, getCheckInDate(), getCheckOutDate());
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("$%.2f", room.getRoomPrice());

        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM dd yyyy");
        String stringInDate = formatter.format(checkInDate);
        String stringOutDate = formatter.format(checkOutDate);

        return "\nReservation\n" +
                customer.getFirstName() + " " + customer.getLastName() + "\n" +
                "Room " + room.getRoomNumber() + " " + room.getRoomType() + " bed\n" +
                "Price " + formattedPrice + " per night\n" +
                "Check in Date " + stringInDate + "\n" +
                "Check out Date " + stringOutDate;
    }
}
