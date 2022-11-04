package com.monsterplex;

public class Key {
    public int keyNumber;

    public Key (Room room) {
        setRoomNumber(room.roomNumber);
    };

    public int getRoomNumber() {
        return keyNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.keyNumber = roomNumber;
    }

    @Override
    public String toString(){
        return String.format("RoomNumber: %s", getRoomNumber());
    }
}
