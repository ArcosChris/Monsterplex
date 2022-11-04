package com.monsterplex;

class Monster extends Character{
    public MonsterType monster;
    public int position;
    //public Attack attack;

    public Monster(double health) {
        super(health);
    }

    public MonsterType getMonster() {
        return monster;
    }

    public int getPosition() {
        return position;
    }

    public void setMonster(MonsterType monster) {
        this.monster = monster;
    }

    public void setPosition(int position) {
        this.position = position;
    }

//   public Attack getAttack() {
//        return attack;
//    }
//
//    public void setAttack(Attack attack) {
//        this.attack = attack;
//    }

    public void attackPlayer(Player player){
        System.out.println("Attacked player");
    }

    @Override
    public String toString(){
        return String.format("MonsterType: %s, Health: %s, ", getMonster(), getHealth());
    }
}