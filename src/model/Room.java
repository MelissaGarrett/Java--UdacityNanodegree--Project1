package model;

import java.util.Objects;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public Room() {}

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    public void setRoomPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean isFree() {
        return price == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getRoomNumber().equals(room.getRoomNumber()) && price.equals(room.price) &&
                enumeration == room.enumeration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), price, enumeration);
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("$%.2f", getRoomPrice());

        return "\nRoom Number: " + getRoomNumber() + " " + getRoomType() + " bed" + " Room Price: " + formattedPrice;
    }
}
