package com.monsterplex;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

class Room {
    private static int nextId = 1;

    public final int roomNumber;
    private List<Tool> tools;
    private List<Weapon> weapons = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private boolean isLocked = false;

    public Room(){
        this.roomNumber = nextId++;
        if(isLocked){
            Key key = new Key(this);
        }
    }

    public Room(List<Tool> tools, List<Token> tokens, List<Monster> monsters){
        this();
        setTools(tools);
        setTokens(tokens);
        setMonsters(monsters);
    }

    public Room(List<Tool> tools, List<Token> tokens, List<Monster> monsters, boolean isLocked) {
        this(tools, tokens, monsters);
        setLocked(isLocked);
    }

    public static int getNextId() {
        return nextId;
    }

    private static void setNextId(int nextId) {
        Room.nextId = nextId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public List<Tool> getTools() {
        return tools;
    }

    private void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    private void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    private void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    private void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public boolean isLocked() {
        return isLocked;
    }

    private void setLocked(boolean locked) {
        isLocked = locked;
    }
}