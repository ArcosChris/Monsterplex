package com.monsterplex;


import java.util.ArrayList;

public class Armor implements Tool {
    public static final double armorDamage = 25.0;
    public static final double NO_ARMOR_HEALTH = 0.0;
    public static final double FULL_ARMOR_HEALTH = 100.0;

    private Armor() {
    }

    public static Armor create(){
        return new Armor();
    }
    @Override
    public void ability(Player player) {
        if (player.hasArmor() && player.getArmorHealth() != FULL_ARMOR_HEALTH) {
            player.setArmorHealth(FULL_ARMOR_HEALTH);
        } else {
            player.setHasArmor(true);
            player.setArmorHealth(FULL_ARMOR_HEALTH);
        }
    }

    @Override
    public String getDescription() {
        return "Armor will protect you against attacks, your health will not be affected - On attack will decrease by 25%";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

