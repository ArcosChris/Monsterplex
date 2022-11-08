package com.monsterplex;

public class Monster extends Character{
    public MonsterType monsterType;

    public Monster(MonsterType monster) {
      this.monsterType = monster;
    }

    public void attack(Player target){
        Attack randomAttack = Attack.getRandomAttack();
        int force = Force.get();

        if(target.hasArmor){
            target.setArmorHealth(target.armorHealth - Armor.armorDamage);
            System.out.printf("%s uses attack %s. Your armor health is now: %s", getMonsterType(), randomAttack, target.getArmorHealth());
        }
        else {
            target.setHealth(target.getHealth() - force); // make it random
            System.out.printf("%s uses attack %s. Your health is now: %s", getMonsterType(), randomAttack, target.getHealth());
        }
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    public void setMonsterType(MonsterType monsterType) {
        this.monsterType = monsterType;
    }

    @Override
    public String toString(){
        return String.format("MonsterType: %s, Health: %s, TEST", getMonsterType(), getHealth());
    }
}