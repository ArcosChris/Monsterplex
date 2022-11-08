package com.monsterplex;

import java.security.Key;
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

    private final WeaponType currentWeapon = WeaponType.STICK;

    private Player(String name){
        setName(name);
    }

    //actions methods
    public void attack(Monster target) {
        target.setHealth(target.health - currentWeapon.getDamage());
        MonsterType monsterAttacked = target.getMonsterType();
        System.out.printf("You attacked %s : %s current health: %s", monsterAttacked, monsterAttacked, target.getHealth());
        target.attack(this);
    }

    public void useTool(Tool tool) {
        tool.ability(this);
        tools.remove(tool);
    }

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public void addTool(Tool tool){
        tools.add(tool);
    }

    //show all weapons and tools
    public void getUserInventory(){

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
}