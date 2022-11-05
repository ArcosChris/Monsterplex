package com.monsterplex;

class Potion implements Tool{
    public String description;

    public Potion(String description) {
        setDescription(description);
    }

    @Override
    public void ability(Player player) {

//        int health = player.getHealth();

//        if(health < Player.MAX_HEALTH){
//            health == Player.MAX_HEALTH;
//        }

        // if health is less than 100 will increase back up to 100
        // will not exceed 100 ever
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}