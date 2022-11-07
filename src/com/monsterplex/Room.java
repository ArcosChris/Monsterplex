package com.monsterplex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.monsterplex.Feature.*;
import static com.monsterplex.Feature.roomFeatures;

class Room {
    private static int nextId = 1;
    private final List<StringBuilder> roomStructure = new ArrayList<>();
    public static final int roomLength = 11; //roomStructure.get(0).length();
    public static final int roomWidth = 6; //roomStructure.size();

    private List<Feature> features = new ArrayList<>();
    private final int roomNumber;
    private boolean isLocked = false;
    private Key roomKey;

    public void addNewFeature(Feature newFeature){
        addFeaturesToRoom(newFeature);
    }

    public Room() {
        this.roomNumber = nextId++;
        initialize();
        addFeatures();
    }

    public Room(boolean isLocked) {
        this();
        setLocked(isLocked);
    }

    public List<String> imageLines() {
        return roomStructure.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
    }

    public Key getRoomKey() {
        if (isLocked) {
            return roomKey;
        }
        return null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        if (isLocked) {
            roomKey = new Key(this);
        }
        this.isLocked = isLocked;
    }

    private int[] getFeatureCoordinates() {
        int[] coordinates = new int[2];

        Random rand = new Random();
        coordinates[0] = rand.nextInt(4) + 1;  // line 1-4
        coordinates[1] = rand.nextInt(10) + 1;  // character 1-10

        return coordinates;
    }

    private void addFeatures() {
        for (Feature feature : roomFeatures()) {
           addFeaturesToRoom(feature);
        }
    }

    private void addFeaturesToRoom(Feature feature){
        features.add(feature);
        int[] coordinates = getFeatureCoordinates();
        StringBuilder line = roomStructure.get(coordinates[0]);  // line 1-4
        line.setCharAt(coordinates[1], feature.symbol());     // character 1-10
    }

    private void initialize() {
        try {
            Files.lines(Path.of("images/room.txt"))
                    .forEach(line -> roomStructure.add(new StringBuilder(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isFeatureAdded() {
        return Math.random() < 0.5;
    }

}