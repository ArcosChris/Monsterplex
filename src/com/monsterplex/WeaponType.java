package com.monsterplex;

public enum WeaponType {

    HAMMER("Hammer"),
    SWORD("Sword"),
    AXE("Axe"),
    KATANA("Katana"),
    SHOVEL("Shovel");

    private final String display;

    WeaponType(String display){
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString(){
        return getDisplay();
    }
}
