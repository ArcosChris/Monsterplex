package com.monsterplex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PlayerTest {


    @Before
    public void setUp(){

    }

    @Test
    public void addTool_shouldAddToolToList() {
        Player player = Player.create("John");
        player.addWeapon(Weapon.AXE);
        int sizeOfInventory = player.getUserInventory().size();
        assertEquals(3, sizeOfInventory);
    }

    @Test
    public void removeTool_shouldRemoveToolToList() {
        Player player = Player.create("John");
        Potion potion = new Potion();
        player.removeTool(potion);
        int sizeOfInventory = player.getUserInventory().size();
        assertEquals(2, sizeOfInventory);
    }

    @Test
    public void getUserInventory_shouldReturnSizeOfInventory() {
        Player player = Player.create("John");
        int sizeOfInventory = player.getUserInventory().size();
        assertEquals(2, sizeOfInventory);
    }

    @Test
    public void getUserInventory_shouldReturnDefaultToolsProvided() {
        Player player = Player.create("John");
        List<Inventory> inventory = player.getUserInventory();
        assertEquals(Weapon.STICK, inventory.get(0));
        assertEquals(Potion.class,inventory.get(1).getClass());
    }
}