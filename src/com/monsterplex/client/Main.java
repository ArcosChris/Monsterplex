package com.monsterplex.client;


import com.monsterplex.Map;
import com.monsterplex.app.MonsterplexApp;

import java.io.IOException;

class Main {

    public static void main(String[] args) throws IOException {
        Map testing = new Map();
        testing.loadMap(1,2);
        MonsterplexApp app = new MonsterplexApp();
        app.execute();

import com.monsterplex.Monster;
import com.monsterplex.MonsterType;
import com.monsterplex.Player;

class Main {

    public static void main(String[] args) {


        Player player = new Player("John");
        Monster monster = new Monster(MonsterType.CYCLOPS);
        System.out.println("Current Player Health =" + player.getHealth());
        System.out.println("Current Monster Health = " + monster.getHealth());
        player.attack(monster);
        monster.attack(player);
        System.out.printf("The %s's health dropped to %s ", monster.monsterType.getDisplay(), monster.getHealth());
        System.out.printf("\n%s's health dropped to %s ", player.getName(), player.getHealth());


    }
}