package com.monsterplex;

class Potion implements Tool {

    public Potion() {

    }

    @Override
    public void ability(Player player) {

        double health = player.getHealth();

        if(health < Player.MAX_HEALTH){
            player.health = Player.MAX_HEALTH;
        }
    }
}