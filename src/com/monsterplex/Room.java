package com.monsterplex;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

class Room {
    private List<Tool> tools = new ArrayList<>();
    private List<Weapon> = new ArrayList<>();
    private List<Token> tokens = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private boolean isLocked = false;
    private Key key;

    public Room(List<Tool> tools, List<Token> tokens, List<Monster> monsters){
        setTools(tools);
        setTokens(tokens);
        setMonsters(monsters);
    }

    public Room(List<Tool> tools, List<Token> tokens, List<Monster> monsters, boolean isLocked, Key key) {
        this(tools, tokens, monsters);
        setLocked(isLocked);
        setKey(key);
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public void setKey(Key key) {
        if(isLocked()){
            this.key = key;
        }
    }
}