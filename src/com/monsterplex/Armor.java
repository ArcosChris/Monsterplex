package com.monsterplex;

class Armor implements Tool{
    public String description;

    public Armor(String description) {
        setDescription(description);
    }

    //TODO : complete implementation for this
    @Override
    public void ability(Player player) {
        //create logic that will do something to the player (always good)
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}