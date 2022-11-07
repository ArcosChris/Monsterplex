package com.monsterplex;

import java.util.Random;

public enum Attack {
    BITE("Bite", 5.0),
    FIRE("Fire", 10.0),
    PUNCH("Punch", 7.0),
    CHAINSAW("Chainsaw", 20.0),
    MACHETE("Machete", 15.0),
    CROSSBOW("Crossbow", 30.0),
    NUNCHUCKS("Nunchucks", 25.0),
    SCYTHE("Scythe", 5.0),
    THUNDER("Thunder", 35.0);

    private final String attackName;


    private final double damage;

    Attack(String attackName, double damage) {
        this.attackName = attackName;
        this.damage = damage;
    }

    public String getAttackName() {
        return attackName;
    }

    public double getDamage() {
        return damage;
    }

    public static Attack getRandomAttack() {
        int random = new Random().nextInt(Attack.values().length);
        return Attack.values()[random];
    }

    @Override
    public String toString() {
        return getAttackName();
    }
}
