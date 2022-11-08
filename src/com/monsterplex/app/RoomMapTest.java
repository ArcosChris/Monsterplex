package com.monsterplex.app;

import com.monsterplex.Player;
import com.monsterplex.UserMap;

import java.util.Scanner;

class RoomMapTest {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {


//        Riddle test1 = InMemoryRiddleHolder.getRandomRiddle();
//        System.out.println(test1.getAnswer());
//        System.out.println(test1.getRiddle());

        Player player = Player.create("Chris");
        UserMap map = UserMap.create();

        map.show();

        System.out.println("Please enter a direction to continue");
        System.out.println("N, E, S, W");

        boolean validInput = false;
        while(validInput == false){
            String input = scanner.nextLine().toUpperCase();

            if(input.matches("[NESWI]")){
                //validInput = true;

                switch(input) {
                    case "N":
                    case "E":
                    case "S":
                    case "W":
                        map.setUserPosition(input);
                        break;
                    case "I":
                        System.out.println("No inventory");
                        break;
                    default:
                        System.out.println("Please enter valid command.");
                }
            }
            map.show();
        }
    }
}