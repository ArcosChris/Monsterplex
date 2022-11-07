package com.monsterplex.util;

import java.util.Random;

public class Randomizer {

    public static int randomPosition(int restriction1){
        Random rand = new Random();
        return rand.nextInt(restriction1);
    }

}