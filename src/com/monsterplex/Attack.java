package com.monsterplex;

import java.util.Random;

public enum Attack {
    BITE("Bite"),
    FIRE("Fire"),
    PUNCH("Punch"),
    CHAINSAW("Chainsaw"),
    MACHETE("Machete"),
    CROSSBOW("Crossbow"),
    NUNCHUCKS("Nunchucks"),
    SCYTHE("Scythe"),
    THUNDER("Thunder");

    private final String attackName;

    Attack(String attackName) {
        this.attackName = attackName;
    }

    public String getAttackName() {
        return attackName;
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
