package com.monsterplex;

public class Monster extends Character{
    public MonsterType monsterType;
    public int position;


    public Monster(MonsterType monster) {
        this.monsterType = monster;
    }

    //TODO : player check the current armor level
    // if armor reduce armor health not player HEALTH
    // if no armor reduce actual health
    public void attack(Player player){



    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return String.format("MonsterType: %s, Health: %s, ", "CHRIS", getHealth());
    }
}