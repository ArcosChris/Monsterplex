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

    private final int roomNumber;
    private boolean isLocked;

    public void addNewFeature(Feature newFeature) {
        addFeaturesToRoom(newFeature);
    }

    public Room(boolean isLocked) {
        this.roomNumber = nextId++;
        setLocked(isLocked);
        initialize();
        addFeatures();
    }

    public List<String> imageLines() {
        return roomStructure.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
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
        boolean add = false;
        for (Feature feature : roomFeatures()) {

            if(feature.equals(MONSTER) && isLocked){
                add = false;
            }
            else if(hasFeature() || feature.equals(PICTURE) || feature.equals(MONSTER)){
                add = true;
            }

            if(add){
                addFeaturesToRoom(feature);
            }
        }
    }

    private boolean hasFeature() {
        return Math.random() < 0.5;
    }

    private void addFeaturesToRoom(Feature feature) {
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