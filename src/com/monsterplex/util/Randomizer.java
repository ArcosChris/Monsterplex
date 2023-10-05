package com.monsterplex.util;

import java.util.Random;

public class Randomizer {

    public static int randomPosition(int bound){
        Random rand = new Random();
        return rand.nextInt(bound);
    }
}