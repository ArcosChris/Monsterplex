package com.monsterplex;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Player extends Character {
    private String name;
    private int tokens;
    private int[] currentPosition;
    private final UserMap userMap = new UserMap();

    //TODO: player will not have many of these but will pick up. Setters should be adding to this list not
    //replacing full list - LOOK AT SET WEAPON METHOD
    // ADD TO LIST METHOD AND A REMOVE FROM LIST
    private final List<Weapon> weapons = new ArrayList<>();
    private List<Tool> tools = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();
    private final WeaponType currentWeapon = WeaponType.STICK;
    public boolean hasArmor = false;
    public double armorHealth = Armor.NO_ARMOR_HEALTH;

    public Player(String name) {
        setName(name);
    }

    //actions methods
    public void attack(Monster target) {
        target.setHealth(target.health - currentWeapon.getDamage());
        MonsterType monsterAttacked = target.getMonsterType();
        System.out.printf("You attacked %s : %s current health: %s", monsterAttacked, monsterAttacked, target.getHealth());
    }

    public void useTool(Tool tool) {
        tool.ability(this);
    }

    //accessors
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getTokens() {
        return tokens;
    }

    private void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    private void setWeapons(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public List<Tool> getTools() {
        return tools;
    }

    private void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public List<Key> getKeys() {
        return keys;
    }

    private void setKeys(List<Key> keys) {
        this.keys = keys;
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