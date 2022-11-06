package com.monsterplex.client;

import com.monsterplex.*;
import com.monsterplex.Character;

import java.io.IOException;

class Main {

    public static void main(String[] args) {

        Player player = new Player("Chris");

        Monster monster = new Monster(MonsterType.CYCLOPS);


        player.attack(monster);

        System.out.println(monster.getHealth());

    }
}