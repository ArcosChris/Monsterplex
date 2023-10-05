package com.monsterplex.game;

import java.util.Random;

public enum MonsterType {
    CYCLOPS("Cyclops"),
    GHOST("Ghost"),
    ZOMBIE("Zombie"),
    CHUPACABRA("Chupacabra"),
    VAMPIRE("Vampire");

    private final String display;

    MonsterType(String display){
        this.display = display;
    }

    public String getDisplay(){
        return display;
    }

    public static MonsterType getRandomMonster() {
        int random = new Random().nextInt(MonsterType.values().length);
        return MonsterType.values()[random];
    }

    @Override
    public String toString(){
        return getDisplay();
    }
}
