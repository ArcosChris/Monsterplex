package com.monsterplex;


import java.util.ArrayList;

public class Armor implements Tool {



    public static final double armorDamage = 25.0;

    public static final double NO_ARMOR_HEALTH = 0.0;
    public static final double FULL_ARMOR_HEALTH = 100.0;

    public String description;


    public Armor(String description) {
        setDescription(description);
    }

    @Override
    public void ability(Player player) {

        if(player.hasArmor && player.armorHealth != FULL_ARMOR_HEALTH){
            player.armorHealth += FULL_ARMOR_HEALTH;
        }
        else{
            player.hasArmor = true;
            player.armorHealth = FULL_ARMOR_HEALTH;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

