package com.monsterplex;

public abstract class Character {
    public static final double  MIN_HEALTH = 0;
    public static final double MAX_HEALTH = 100;
    public double health = 100.0;

    public Character(double health) {
        setHealth(health);
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }
}