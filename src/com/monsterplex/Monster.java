package com.monsterplex;

import java.util.Random;

public class Monster extends Character{
    public MonsterType monsterType;

    private Monster(MonsterType monster) {
      this.monsterType = monster;
    }

    public static Monster createRandom(){
        MonsterType randomType = MonsterType.getRandomMonster();
        return new Monster(randomType);
    }

    public void attack(Player target){
        Attack randomAttack = Attack.getRandomAttack();
        int force = Force.get();

        if(target.hasArmor()){
            target.setArmorHealth(target.getArmorHealth() - Armor.armorDamage);
            System.out.printf("\n%s uses attack %s. Your armor health is now: %s\n" +
                    "your health is still %s", getMonsterType(), randomAttack, target.getArmorHealth(), target.getHealth());
        }
        else {
            target.setHealth(target.getHealth() - force);
            System.out.printf("\n%s uses attack %s. Your health is now: %s", getMonsterType(), randomAttack, target.getHealth());
        }
    }

    public MonsterType getMonsterType() {
        return monsterType;
    }

    @Override
    public String toString(){
        return String.format("MonsterType: %s, Health: %s, TEST", getMonsterType(), getHealth());
    }

    static class Force {
        private final static int MIN_FORCE = 1;
        private final static int MAX_FORCE = 20;

        //method to return random force value between the min and max force
        public static int get() {
            double random = Math.random();
            return (int) (random * MAX_FORCE + MIN_FORCE);
        }
    }

    enum Attack {
        BITE("Bite"),
        FIRE("Fire"),
        PUNCH("Punch"),
        CHAINSAW("Chainsaw"),
        MACHETE("Machete"),
        CROSSBOW("Crossbow"),
        NUNCHUCKS("Nunchucks"),
        SCYTHE("Scythe"),
        THUNDER("Thunder");

        private final String attackName;

        Attack(String attackName) {
            this.attackName = attackName;
        }

        public String getAttackName() {
            return attackName;
        }

        public static Attack getRandomAttack() {
            int random = new Random().nextInt(Attack.values().length);
            return Attack.values()[random];
        }

        @Override
        public String toString() {
            return getAttackName();
        }
    }
}