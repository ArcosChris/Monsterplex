package com.monsterplex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Map {
   // private List<Room> rooms = new ArrayList<>();

    public Map() {
//        this.rooms = rooms;
    }

    public void loadMap(int level, int position) throws IOException {
        System.out.println(new String(Files.readAllBytes(Paths.get(String.format("Map%sPosition%s.txt", level, position)))));
    }

}