package com.monsterplex.game;

import com.monsterplex.game.Feature;
import com.monsterplex.game.Room;
import com.monsterplex.util.FileHelper;
import com.monsterplex.util.Randomizer;

import java.io.IOException;
import java.util.*;

import static com.monsterplex.game.Feature.*;

public class UserMap {
    private final List<StringBuilder> mapStructure = FileHelper.readFileToStringBuilderArrayList("map.txt");
    private final List<StringBuilder> legend = FileHelper.readFileToStringBuilderArrayList("mapLegend.txt");
    private final List<Room> rooms = new ArrayList<>();
    private final int mapLength = mapStructure.get(0).length();
    private final int mapWidth = mapStructure.size();
    private final List<Integer> exitCode = new ArrayList<>();
    private final List<Integer> codeShownToUser = new ArrayList<>();
    private int[] currentPosition = new int[]{1, mapStructure.size() - 2};

    public static UserMap create() throws IOException {
        return new UserMap();
    }

    private UserMap() throws IOException {
        generateRandomExitCode();
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

    public int[] getNextCoordinates(String direction) {
        int x = currentPosition[0];
        int y = currentPosition[1];

        switch (direction.toUpperCase()) {
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
        return new int[]{x,y};
    }

    public char getCharacterAtPosition(int x, int y) {
        return mapStructure.get(y).charAt(x);
    }

    public void setCurrentPosition(int x, int y){
        //remove player from previous position
        setInCoordinate(currentPosition[0], currentPosition[1], ' ');
        this.currentPosition = new int[]{x, y};
        setInCoordinate(x, y, PLAYER.symbol());
    }

    public List<Integer> getExitCode(){
        return new ArrayList<>(exitCode);
    }

    public int getRandomExitCodeDigit(){
        int random = new Random().nextInt(codeShownToUser.size());
        Integer digitFromCode = codeShownToUser.get(random);
        codeShownToUser.remove(digitFromCode);
        return digitFromCode;
    }

    public int getMapLength(){
        return mapLength;
    }

    public int getMapWidth(){
        return mapWidth;
    }

    public int[] getPlayerPosition(){
        return currentPosition;
    }

    private void generateRandomExitCode() {
    for (int i =0; i < 4; i++){
        int randomNumber = new Random().nextInt(9);
        exitCode.add(randomNumber);
        codeShownToUser.add(randomNumber);
    }
}

    private void setRoomPositions() {
        createRoomsBasedOnMap();
        Room previousRoom= null;
        int startingX = 2;
        int roomCount = 0;

        for (Room room : rooms) {
            if(previousRoom != null && previousRoom.isLocked()){
                room.addNewFeature(KEY);
            }

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
            previousRoom = room;
        }
    }

    private void createRoomsBasedOnMap() {
        int amountOfRooms = (mapLength - 2) / (Room.roomLength + 2);
        for (int i = 0; i < amountOfRooms; i++) {
            if (i % 2 == 0) {
                rooms.add(new Room(true));
            } else {
                rooms.add(new Room(false));
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