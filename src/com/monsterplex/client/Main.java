package com.monsterplex.client;

import com.monsterplex.Monster;
import com.monsterplex.MonsterType;
import com.monsterplex.Player;
import com.monsterplex.app.MonsterplexApp;

class Main {

    public static void main(String[] args) {

//        MonsterplexApp app = new MonsterplexApp();
//        app.execute();
        Player player = new Player("John");
        Monster monster = new Monster(MonsterType.CYCLOPS);
        System.out.println("Current Player Health =" + player.getHealth());
        System.out.println("Current Monster Health = " + monster.getHealth());
        player.attack(monster);
        monster.attack(player);
        System.out.printf("The %s's health dropped to %s ", monster.monsterType.getDisplay(), monster.getHealth());
        System.out.printf("\n%s's health dropped to %s ", player.getName(), player.getHealth());
        monster.attack(player);
        System.out.printf("\n%s's health dropped to %s ", player.getName(), player.getHealth());
    }
}