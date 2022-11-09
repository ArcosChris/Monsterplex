package com.monsterplex;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void setHealth_shouldReturnFalseWhenHealthIsGreaterThanMinHealth() {
        Player player = Player.create("John");
        player.setHealth(10);
        assertFalse(player.isDead);
    }

    @Test
    public void setHealth_shouldReturnTrueWhenHealthIsLessThanMinHealth() {
        Player player = Player.create("John");
        player.setHealth(0);
        assertTrue(player.isDead);
    }
}