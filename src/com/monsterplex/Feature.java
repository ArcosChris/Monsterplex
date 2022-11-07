package com.monsterplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public enum Feature {
    MONEY('$'),
    KEY('*'),
    MONSTER('M'),
    RIDDLE('?'),
    PICTURE('#'),
    EXIT('+'),
    PLAYER('O'),
    HEADER('_'),
    WALL('|'),
    LOCKED('%'),
    UNLOCKED('/');

    private final char symbol;

    Feature(char symbol) {
        this.symbol = symbol;
    }

    public static Feature randomEntrance(){
        List<Feature> entrances = new ArrayList<>(List.of(UNLOCKED, RIDDLE));
        int random = new Random().nextInt(entrances.size());
        return entrances.get(random);
    }

    public static List<Feature> roomFeatures(){
        return new ArrayList<>(List.of(MONEY, MONSTER, PICTURE));
    }

    public char symbol() {
        return symbol;
    }
}
