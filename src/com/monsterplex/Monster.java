package com.monsterplex;

public class Monster extends Character{
    public MonsterType monsterType  ;
    public int position;

    public Monster(MonsterType monster) {
      this.monsterType = monster;
    }

    public void attack(Player target){

        if(target.hasArmor){
            target.setArmorHealth(target.armorHealth - Armor.armorDamage);
        }
        else {
            target.setHealth(target.getHealth() - Attack.BITE.getDamage()); // make it random
        }
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString(){
        return String.format("MonsterType: %s, Health: %s, TEST",  getHealth());
    }
}