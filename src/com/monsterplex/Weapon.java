package com.monsterplex;

import java.util.Random;

public enum Weapon implements Inventory {
    STICK("Stick", 20),
    HAMMER("Hammer",30.00),
    SWORD("Sword",20.00),
    AXE("Axe", 30.00),
    KATANA("Katana", 20.0),
    SHOVEL("Shovel",25);

    private final String display;
    private final double damage;

    Weapon(String display, double damage){
        this.display = display;
        this.damage = damage;
    }

    public String getDisplay() {
        return display;
    }

    public double getDamage(){
        return damage;
    }

    public static Weapon getRandomWeapon() {
        int random = new Random().nextInt(Weapon.values().length);
        return Weapon.values()[random];
    }

    @Override
    public String toString(){
        return getDisplay();
    }

    @Override
    public String getDescription() {
        return String.format("%s has %s damage", getDisplay(), getDamage());
    }
}
