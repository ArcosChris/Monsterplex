package com.monsterplex;

import com.monsterplex.util.Randomizer;
import com.monsterplex.util.Reader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.monsterplex.Feature.*;

public class UserMap {
    private final List<StringBuilder> mapStructure = Reader.readFileToArrayList("images/map.txt");
    private final List<StringBuilder> legend = Reader.readFileToArrayList("images/mapLegend.txt");
    private final List<Room> rooms = new ArrayList<>();
    private final List<Feature> itemsInMap = new ArrayList<>();
    private final Map<Room, Key> roomKeyMap = new HashMap<>();

    private final int mapLength = mapStructure.get(0).length();
    private final int mapWidth = mapStructure.size();
    private int[] currentPosition = new int[]{1, mapStructure.size() - 2};

    public UserMap() {
        setMapLegendItems();
        setRoomPositions();
    }

    public void show() {
        for (int i = 0; i < mapStructure.size(); i++) {

            if (legend.size() > i) {
                System.out.println(mapStructure.get(i) + "\t\t" + legend.get(i));
            } else {
                System.out.println(mapStructure.get(i));
            }
        }
    }

    public void setUserPosition(String userInput) {
        int x = currentPosition[0];
        int y = currentPosition[1];

        switch (userInput) {
            case "N":
                y -= 1;
                break;

            case "E":
                x += 1;
                break;

            case "S":
                y += 1;
                break;

            case "W":
                x -= 1;
                break;
        }

        if (x <= 0 || x > mapLength - 1 || y <= 0 || y > mapWidth - 1 || !validPosition(x, y)) {
            System.out.println("Hmm.. there seems to be a wall there. Let's try a different route.");
        } else {
            //clear previous position
            setInCoordinate(currentPosition[0], currentPosition[1], ' ');
            this.currentPosition = new int[]{x, y};
            setInCoordinate(x, y, PLAYER.symbol());
        }
    }

    private void setRoomPositions() {
        createRoomsBasedOnMap();

        Key previousRoomKey = null;
        Room previousRoom = null;
        int startingX = 2;
        int roomCount = 0;

        for (Room room : rooms) {
            if(previousRoomKey != null){
                room.addNewFeature(KEY);
                roomKeyMap.put(previousRoom, previousRoomKey);
                previousRoom = null;
                previousRoomKey = null;
            }

            int randomRoomLineY = Randomizer.randomPosition(Room.roomWidth - 2) + 1; //only on lines 1-4
            int randomRoomExitX = Math.random() > 0.5 ? 0 : Room.roomLength;

            previousRoomKey = room.isLocked() ? room.getRoomKey() : null;
            previousRoom = room.isLocked() ? room : null;

            int startingY = Math.random() < 0.5 ? 2 : 8; //random select top or bottom of map(room can't be outside)

            int currRoomLines = room.imageLines().size();

            for (int i = 0; i < currRoomLines; i++) {
                char[] lineItems = room.imageLines().get(i).toCharArray();

                for (int j = 0; j < lineItems.length; j++) {
                    if(randomRoomLineY == i && randomRoomExitX == j) {
                        lineItems[j] = previousRoomKey != null ? Feature.LOCKED.symbol() : Feature.randomEntrance().symbol();
                    }
                    int position = ((roomCount * (Room.roomLength + 3)) + (startingX));
                    setInCoordinate(position, startingY, lineItems[j]);
                    startingX++;
                }
                startingY++;
                startingX = 2;
            }
            roomCount++;
        }
    }

    private boolean validPosition(int x, int y) {
        boolean isValid = false;
        char charCheck = mapStructure.get(y).charAt(x);

        isValid = charCheck != WALL.symbol() && charCheck != HEADER.symbol();

        return isValid;
    }

    private void createRoomsBasedOnMap(){
        int amountOfRooms = (mapLength - 2) / (Room.roomLength + 2);
        for (int i = 0; i < amountOfRooms; i++) {
            if (i % 2 == 0) {
                rooms.add(new Room(true));
            } else {
                rooms.add(new Room());
            }
        }


    }

    private void setMapLegendItems() {
        int randomExitY = Randomizer.randomPosition(mapStructure.size() - 3);
        int randomExitX = mapStructure.get(randomExitY).length() - 1;

        setInCoordinate(currentPosition[0], currentPosition[1], PLAYER.symbol());


        if (randomExitY == 0) {
            randomExitX = Randomizer.randomPosition(mapStructure.get(randomExitY).length() - 5);
        }
        setInCoordinate(randomExitX, randomExitY, EXIT.symbol());
    }

    private void setInCoordinate(int x, int y, char symbol) {
        mapStructure.get(y).setCharAt(x, symbol);
    }
}