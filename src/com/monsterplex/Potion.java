package com.monsterplex;

class Potion implements Tool {

    public Potion() {

    }

    @Override
    public void ability(Player player) {
        double health = player.getHealth();
        if(health < Player.MAX_HEALTH){
            player.health = Player.MAX_HEALTH;
            player.removeTool(this);
        }else if(health == Player.MAX_HEALTH){
            System.out.printf("You cannot use %s. Your Health is already at %s\n", getClass().getSimpleName(), player.getHealth());
        }
    }

    @Override
    public String getDescription() {
        return "Potion will return your health to 100";
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}