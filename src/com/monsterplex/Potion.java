package com.monsterplex;

class Potion implements Tool {
    public String description;

    public Potion(String description) {
        setDescription(description);
    }

    @Override
    public void ability(Player player) {

        double health = player.getHealth();

        if(health < Player.MAX_HEALTH){
            player.health = Player.MAX_HEALTH;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}