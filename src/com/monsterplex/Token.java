package com.monsterplex;

public class Token {
    private int value;

    public Token(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}