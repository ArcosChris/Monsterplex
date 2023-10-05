package com.monsterplex;

import com.monsterplex.game.Armor;
import com.monsterplex.game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArmorTest {
    Player player;

    @Before
    public void setUp() {
        player = Player.create("Chris");
    }

    @Test
    public void ability_ShouldSetPlayerHasArmorToTrue_whenInvoked() {
        Armor newArmor = Armor.create();
        player.useTool(newArmor);
        assertTrue(player.hasArmor());
    }

    @Test
    public void ability_ShouldSetPlayerArmorHealthToFull_whenInvoked() {
        Armor newArmor = Armor.create();
        player.useTool(newArmor);
        assertEquals(100.0, player.getArmorHealth(), .001);
    }
}