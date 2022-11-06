package com.monsterplex;

public abstract class Character {
    public static final double  MIN_HEALTH = 0;
    public static final double MAX_HEALTH = 100;
    public double health = 100.0;
    public boolean isDead = false;

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        //TODO if health 0 or less character is dead.
        if(health <= MIN_HEALTH){

        }

            this.health = health;
    }

}