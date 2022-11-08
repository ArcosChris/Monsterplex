package com.monsterplex;

class Force {
    private final static int MIN_FORCE = 1;
    private final static int MAX_FORCE = 20;

    //method to return random force value between the min and max force
    public static int get(){
        double random = Math.random();
        return (int)(random * MAX_FORCE + MIN_FORCE);
    }
}