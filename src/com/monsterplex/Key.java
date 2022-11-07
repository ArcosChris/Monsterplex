package com.monsterplex;

public class Key {
    public int keyNumber;

    public Key(Room room) {
        setKeyNumber(room.getRoomNumber());
    };

    public int getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(int roomNumber) {
        this.keyNumber = roomNumber;
    }

    @Override
    public String toString(){
        return String.format("RoomNumber: %s", getKeyNumber());
    }
}