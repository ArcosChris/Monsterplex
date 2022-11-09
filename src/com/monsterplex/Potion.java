package com.monsterplex;

public class Potion implements Tool {

    private Potion() {

    }
    public static Potion create(){
        return new Potion();
    }

    @Override
    public void ability(Player player) {
        double health = player.getHealth();
        if(health < Player.MAX_HEALTH){
            player.setHealth(Player.MAX_HEALTH);
            System.out.printf("Your health is now at %s\n", player.getHealth());
            player.removeTool(this);
        }else if(health == Player.MAX_HEALTH){
            System.out.printf("You cannot use %s. Your Health is already at %s. Don't be greedy..\n", getClass().getSimpleName(), player.getHealth());
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