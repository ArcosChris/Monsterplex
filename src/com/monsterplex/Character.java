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

        if (health <= MIN_HEALTH) {
            isDead = true;
        } else {
            this.health = health;
        }
    }
}