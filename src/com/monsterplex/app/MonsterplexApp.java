package com.monsterplex.app;

import com.apps.util.Prompter;
import com.apps.util.Console;
import com.monsterplex.Player;
import com.monsterplex.UserMap;

import java.io.*;
import java.util.Scanner;


/**
 * Controller - directs flow of an app like user promp,
 * sending provided inputs to system classes
 */
public class MonsterplexApp {
    Prompter prompter = new Prompter(new Scanner(System.in));
    Player player = null;
    UserMap playerMap= null;

    public void execute() {
        welcome();
        String name = promptUserForName();
        player = Player.create(name);
        playerMap = new UserMap(player);
        printDirections();
        showMap();

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

    private String promptUserForName(){
       return prompter.prompt("PLEASE ENTER YOUR NAME TO BEGIN: ", "[a-zA-Z]+", "\nName should only have letters.\n");
    }

    private void printDirections(){
        String directions = String.format("Welcome, %s. The game rules are very simple... just don't die. You have 10 minutes to escape.", player.getName());
        for (char c : directions.toCharArray()) {
            Console.pause(100);
            System.out.print(c);
        }
    }

    private void showMap(){
        playerMap.show();
    }

}
