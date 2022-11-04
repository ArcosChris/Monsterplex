package com.monsterplex;

public class Weapon {
    public WeaponType weapon;
    public int damage;

    public WeaponType getWeapon() {
        return weapon;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return String.format("WeaponType: %s, Damage: %s", getWeapon(), getDamage());
    }
}
