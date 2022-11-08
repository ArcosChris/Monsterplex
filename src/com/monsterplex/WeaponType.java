package com.monsterplex;

import java.util.Random;

public enum WeaponType {
    STICK("Stick", 2.0),
    HAMMER("Hammer",5.0),
    SWORD("Sword",15.0),
    AXE("Axe", 30.0),
    KATANA("Katana", 45.0),
    SHOVEL("Shovel",10);

    private final String display;
    private final double damage;

    WeaponType(String display, double damage){
        this.display = display;
        this.damage = damage;
    }

    public String getDisplay() {
        return display;
    }

    public double getDamage(){
        return damage;
    }

    public static WeaponType getRandomWeaponType() {
        int random = new Random().nextInt(WeaponType.values().length);
        return WeaponType.values()[random];
    }

    @Override
    public String toString(){
        return getDisplay();
    }
}
