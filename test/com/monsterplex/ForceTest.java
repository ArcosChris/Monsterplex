package com.monsterplex;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.monsterplex.Monster.*;

public class ForceTest {

    @Test
    public void getForce_shouldReturnRandomForceValueBetweenMinAndMaxForce() {
        int force = Force.get();
        assertTrue(force >= 1 && force <= 20);
    }
}