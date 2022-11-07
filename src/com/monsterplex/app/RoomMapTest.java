package com.monsterplex.app;

import com.monsterplex.UserMap;

import java.util.Scanner;

class RoomMapTest {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserMap test = new UserMap();
        test.show();

        System.out.println("Please enter a direction to continue");
        System.out.println("N, E, S, W");

        boolean validInput = false;
        while(validInput == false){
            String input = scanner.nextLine().toUpperCase();

            //NESW - map directions I - inventory

            if(input.matches("[NESWI]")){
                //validInput = true;

                switch(input) {
                    case "N":
                    case "E":
                    case "S":
                    case "W":
                        test.setUserPosition(input);
                        break;
                    case "I":
                        System.out.println("No inventory");
                        break;
                    default:
                        System.out.println("Please enter valid command.");
                }
            }
            test.show();
        }
    }
}