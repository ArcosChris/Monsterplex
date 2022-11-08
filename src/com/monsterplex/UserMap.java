package com.monsterplex;

import com.monsterplex.util.Randomizer;
import com.monsterplex.util.Reader;

import java.util.*;

import static com.monsterplex.Feature.*;

public class UserMap {
    private final List<StringBuilder> mapStructure = Reader.readFileToArrayList("images/map.txt");
    private final List<StringBuilder> legend = Reader.readFileToArrayList("images/mapLegend.txt");
    private final List<Room> rooms = new ArrayList<>();
    private final int mapLength = mapStructure.get(0).length();
    private final int mapWidth = mapStructure.size();

    private Player player = null;
    private int[] currentPosition = new int[]{1, mapStructure.size() - 2};

    public UserMap(Player player) {
        this.player = player;
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

        if (!validPosition(x, y)) {
            System.out.println("Hmm.. there seems to be a wall there. Let's try a different route.");
        } else {
            //clear previous position
            setInCoordinate(currentPosition[0], currentPosition[1], ' ');
            this.currentPosition = new int[]{x, y};
            setInCoordinate(x, y, PLAYER.symbol());
            checkUserSurroundingsForMonster(x, y);
        }
    }

    private void checkUserSurroundingsForMonster(int x, int y) {
        List<int[]> surroundings = new ArrayList<>();
        surroundings.add(new int[]{x, y + 1});
        surroundings.add(new int[]{x, y - 1});
        surroundings.add(new int[]{x - 1, y});
        surroundings.add(new int[]{x + 1, y});

        for (int[] item : surroundings) {
            int col = item[0];
            int row = item[1];

            if (characterAtPosition(col, row) == MONSTER.symbol()) {
                System.out.println("Monster spotted...");

            }
        }
    }


    private void setRoomPositions() {
        createRoomsBasedOnMap();
        int startingX = 2;
        int roomCount = 0;

        for (Room room : rooms) {
            int randomRoomLineY = Randomizer.randomPosition(Room.roomWidth - 2) + 1; //only on lines 1-4
            int randomRoomExitX = Math.random() > 0.5 ? 0 : Room.roomLength;

            int startingY = Math.random() < 0.5 ? 2 : 8; //random select top or bottom of map(room can't be outside)

            int currRoomLines = room.imageLines().size();

            for (int i = 0; i < currRoomLines; i++) {
                char[] lineItems = room.imageLines().get(i).toCharArray();

                for (int j = 0; j < lineItems.length; j++) {
                    if (randomRoomLineY == i && randomRoomExitX == j) {
                        lineItems[j] = room.isLocked()? Feature.LOCKED.symbol() : Feature.randomEntrance().symbol();
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

    private void createRoomsBasedOnMap() {
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

    private char characterAtPosition(int x, int y) {
        return mapStructure.get(y).charAt(x);
    }
}