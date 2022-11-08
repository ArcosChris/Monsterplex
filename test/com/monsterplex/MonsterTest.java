package com.monsterplex;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonsterTest {

    @Before
    public void setUp() {

    }

    @Test
    public void attack_shouldReducePlayerHealth_whenNoArmor(){
        Monster monster = new Monster(MonsterType.CYCLOPS);
        Player player = Player.create("John");
        monster.attack(player);
        assertTrue(player.getHealth() < Character.MAX_HEALTH);
    }

    @Test
    public void attack_shouldNotReducePlayerHealth_whenPlayerHasArmor(){
        Monster monster = new Monster(MonsterType.CYCLOPS);
        Player player = Player.create("John");
        Armor armor = new Armor();
        player.useTool(armor);
        monster.attack(player);
        assertEquals(Character.MAX_HEALTH, player.getHealth(), 0.0);
    }
}