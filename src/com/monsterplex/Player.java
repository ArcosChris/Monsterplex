package com.monsterplex;

import javax.tools.Tool;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player extends Character {
    private String name;
    private int tokens;
    private int currentPosition;
    private Map map;
    private List<Weapon> weapons = new ArrayList<Weapon>();
    private List<Tool> tools = new ArrayList<Tool>();
    private List<Key> keys = new ArrayList<Key>();

    public void attack(Weapon weapon, Monster monster){

    }
    public void getAttacked (Monster monster){

    }

    public Character getCharacter() {
        return Null;
    }

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

    public int getCurrentPosition() {
        return currentPosition;
    }

    private void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Map getMap() {
        return map;
    }

    private void setMap(Map map) {
        this.map = map;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    private void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
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
}