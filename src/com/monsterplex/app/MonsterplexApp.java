package com.monsterplex.app;

import java.io.*;
import java.util.Scanner;

/**
 * Controller - directs flow of an app like user promp,
 * sending provided inputs to system classes
 */

public class MonsterplexApp {
    private final Scanner scanner = new Scanner(System.in);

    public void execute() {
        welcome();
    }

    private void welcome() {
        try {
            File file = new File("MonsterplexArt.txt");
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
}




