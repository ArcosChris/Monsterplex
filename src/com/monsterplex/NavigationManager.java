package com.monsterplex;

import static com.monsterplex.Feature.HEADER;
import static com.monsterplex.Feature.WALL;

public class NavigationManager {
    UserMap currentMap;

    public void movePlayer(UserMap userMap) {
        this.currentMap = userMap;
        checkSurroundings();
    }

    private static void checkSurroundings() {

    }



}