package com.monsterplex;

public class Monster extends Character{
    public MonsterType monsterType  ;
    public int position;

    public Monster(MonsterType monster) {
      this.monsterType = monster;
    }

    public void attack(Player target){

        if(target.hasArmor){
            target.setArmorHealth(target.armorHealth - 1);
        }

        // should only be called when Monster is attacked by player
        if(target.health <= MIN_HEALTH){
            target.isDead = true;
            System.out.println("Player is dead");
        }
        else {
            target.setHealth(target.getHealth() - Attack.BITE.getDamage());
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