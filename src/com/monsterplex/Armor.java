package com.monsterplex;


import java.util.ArrayList;

public class Armor implements Tool {
    public static final double armorDamage = 25.0;
    public static final double NO_ARMOR_HEALTH = 0.0;
    public static final double FULL_ARMOR_HEALTH = 100.0;

    public Armor() {
    }

    @Override
    public void ability(Player player) {
        if(player.hasArmor() && player.getArmorHealth() != FULL_ARMOR_HEALTH){
            player.setArmorHealth(FULL_ARMOR_HEALTH);
        }
        else{
            player.setHasArmor(true);
            player.setArmorHealth(FULL_ARMOR_HEALTH);
        }
    }
}

