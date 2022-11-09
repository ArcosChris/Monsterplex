package com.monsterplex;

import java.util.ArrayList;
import java.util.List;

public class Player extends Character {

    public static Player create(String name){
        return new Player(name);
    }

    private String name;
    //User inventory
    private final List<Weapon> weapons = new ArrayList<>();
    private List<Tool> tools = new ArrayList<>();
    private boolean hasArmor = false;
    private boolean hasKey = false;
    public double armorHealth = Armor.NO_ARMOR_HEALTH;
    private Weapon currentWeapon = Weapon.STICK;

    private Player(String name){
        setName(name);
        addWeapon(Weapon.STICK);
        addTool(Potion.create());
    }

    //actions methods
    public void attack(Monster target) {
        target.setHealth(target.getHealth() - currentWeapon.getDamage());
        MonsterType monsterAttacked = target.getMonsterType();
        System.out.printf("You attacked %s : %s current health: %s", monsterAttacked, monsterAttacked, target.getHealth());
    }

    public void useTool(Tool tool) {
        tool.ability(this);
    }

    public void addWeapon(Weapon weapon){
        if(!weapons.contains(weapon)){
            weapons.add(weapon);
        }
    }

    public void addTool(Tool tool){
        tools.add(tool);
    }

    public void removeTool (Tool tool){
        tools.remove(tool);
    }

    public List<Inventory> getUserInventory(){

        List<Inventory> inventory = new ArrayList<>();
        inventory.addAll(weapons);
        inventory.addAll(tools);
        return inventory;
    }

    //accessors
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public boolean hasKey(){
        return this.hasKey;
    }

    public void setHasKey(boolean hasKey){
        this.hasKey = hasKey;
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    public double getArmorHealth() {
        return armorHealth;
    }

    public void setArmorHealth(double armourHealth) {
        if(armourHealth == 0){
            setHasArmor(false);
            setArmorHealth(Armor.NO_ARMOR_HEALTH);
        }
        else {
            this.armorHealth = armourHealth;
        }
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon weapon){
        this.currentWeapon = weapon;
    }
}