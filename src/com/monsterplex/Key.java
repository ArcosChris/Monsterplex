package com.monsterplex;

public class Key {
    public int roomNumber;

    public Key (Room room) {
        setRoomNumber(room.roomNumber);
    };

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString(){
        return String.format("RoomNumber: %s", getRoomNumber());
    }
}
