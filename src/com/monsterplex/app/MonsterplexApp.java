package com.monsterplex.app;

import com.apps.util.Prompter;
import com.apps.util.Console;
import com.monsterplex.*;

import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * Controller - directs flow of an app like user promp,
 * sending provided inputs to system classes
 */
public class MonsterplexApp {
    Prompter prompter = new Prompter(new Scanner(System.in));
    Player player = null;
    UserMap playerMap = null;

    public void execute() {
        welcome();
        String name = promptUserForName();
        player = Player.create(name);
        playerMap = UserMap.create();
        printDirections();
        Console.pause(200);
        game();
    }

    private void welcome() {
        try {
            File file = new File("images/MonsterplexArt.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private String promptUserForName() {
        return prompter.prompt("PLEASE ENTER YOUR NAME TO BEGIN: ", "[a-zA-Z]+", "\nName should only have letters.\n");
    }

    private void printDirections() {
        String directions = String.format("Welcome, %s. The game rules are very simple... just don't die. You have 10 minutes to escape.\n", player.getName());
        for (char c : directions.toCharArray()) {
            Console.pause(100);
            System.out.print(c);
        }
    }

    private void game() {
        Console.clear();
        playerMap.show();
        while (!player.isDead) {
            String input = prompter.prompt("Please enter a direction to continue or enter I for inventory: ", "[NESWIneswi]{1}", "\nNot quite, try again.\n");
            switch (input.toUpperCase()) {
                case "N":
                case "E":
                case "S":
                case "W":
                    userSwitchPosition(input);
                    break;
                case "I":
                    userSelectedInventory();
                    break;
            }
        }
    }

    private void userSwitchPosition(String direction) {
        playerMap.setUserPosition(direction);
        playerMap.show();
    }

    private void userSelectedInventory() {
        Console.pause(3000L);
        Console.clear();
        List<Inventory> userInventory = player.getUserInventory();

        System.out.printf("\n%s's Inventory: \n", player.getName());

        for (int i = 0; i < userInventory.size(); i++) {
            System.out.printf("[%s] - %s\n", i, userInventory.get(i));
        }

        String input = prompter.prompt("Select an item number for details or [E]xit: ", String.format("[xX0-%s]", userInventory.size()), "\nNot Valid\n ");
        if ("X".equals(input.toUpperCase())) {
            game();
        } else {
            inventoryDetails(input);
        }
    }

    private void inventoryDetails(String input) {
        Console.clear();
        int item = Integer.parseInt(input);
        Inventory itemSelected = player.getUserInventory().get(item);
        String itemDescription = itemSelected.getDescription();
        System.out.printf("\n%s Details: \n",itemSelected);
        System.out.printf("%s\n", itemDescription);

        String selection = prompter.prompt("[U]se item \n[E]xit\nSelect option: ", "[ueUE]", "Please enter valid selection");
        switch (selection.toUpperCase()) {
            case "U":
                if (itemSelected instanceof Weapon) {
                    Weapon weapon = (Weapon) itemSelected;
                    player.setCurrentWeapon(weapon);
                } else if (itemSelected instanceof Tool) {
                    Tool tool = (Tool) itemSelected;
                    player.useTool(tool);
                }
                break;
            case "E":
                userSelectedInventory();
                break;
        }
        userSelectedInventory();
    }
}
