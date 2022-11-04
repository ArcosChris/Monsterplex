package com.monsterplex;

public enum MonsterType {

    CYCLOPS("Cyclops"),
    ZOMBIE("Zombie"),
    REAPERS("Reaper"),
    MUTANT("Mutant"),
    CHUPACABRA("Chupacabra");

    private final String display;

    MonsterType(String display){
        this.display = display;
    }

    public String getDisplay(){
        return display;
    };

    @Override
    public String toString(){
        return getDisplay();
    }

}
