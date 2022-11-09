package com.monsterplex.app;

import com.apps.util.Prompter;
import com.apps.util.Console;
import com.monsterplex.*;

import java.io.*;
import java.lang.Character;
import java.sql.SQLOutput;
import java.util.*;


/**
 * Controller - directs flow of an app like user promp,
 * sending provided inputs to system classes
 */
public class MonsterplexApp {
    Scanner scanner = new Scanner(System.in);
    Prompter prompter = new Prompter(scanner);
    int attemptsToExit = 3;
    Player player = null;
    UserMap playerMap = null;
    boolean gameIsOver = false;
    boolean userWon = false;


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
        printFile("images/MonsterplexArt.txt");

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

        if (!gameIsOver) {
            playerMap.show();
        }

        while (!player.isDead() && !gameIsOver) {
            System.out.println("\n\nPlease enter a direction [N]orth, [E]ast, [S]outh, [W]est or [I]nventory:");
            String input = prompter.prompt("Enter command: ", "[NESWIneswi]{1}", "\nNot quite, try again.\n");
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


        if (userWon) {
            //win message
            System.out.println("YOU WON!!!!!!!");
        } else {
            //game over message
            System.out.println("THAT SUCKS!");
        }
    }

    private void userSwitchPosition(String direction) {
        Console.clear();

        int[] nextPosition = playerMap.getNextCoordinates(direction);
        int x = nextPosition[0];
        int y = nextPosition[1];
        char characterAtNext = playerMap.getCharacterAtPosition(x, y);

        if (featureAtPosition(characterAtNext)) {
            boolean userCompleted = getFeaturePrompt(characterAtNext);

            if (userCompleted) {
                playerMap.setCurrentPosition(x, y);
                //checkForMonsters(x,y);
            }
        } else {
            playerMap.setCurrentPosition(x, y);
        }

        playerMap.show();
    }

    //feature methods
    private boolean featureAtPosition(char symbol) {
        return Feature.getFeatureBySymbol(symbol) != null;
    }

    private boolean getFeaturePrompt(char symbol) {
        boolean completed = false;
        Feature feature = Feature.getFeatureBySymbol(symbol);

        switch (feature) {
            case KEY:
                completed = keyMessage();
                break;

            case RIDDLE:
                completed = riddlePrompt();
                break;

            case PICTURE:
                completed = inspectPicture();
                break;

            case WEAPON:
                landOnWeapon();
                completed = true;
                break;

            case TOOL:
                landOnTool();
                completed = true;
                break;

            case EXIT:
                completed = attemptExit();
                break;

            case HEADER:
            case WALL:
                System.out.println("Hmm.. there seems to be a wall there. Let's try a different route.");
                break;

            case LOCKED:
                completed = lockedDoor();
                break;

            case UNLOCKED:
                completed = true;
                break;
        }
        return completed;
    }

    private boolean riddlePrompt() {
        boolean completed = false;
        Riddle randomRiddle = Riddle.InMemoryRiddleHolder.getRandomRiddle();

        System.out.println("Answer the following riddle to enter room.");
        System.out.println(randomRiddle.getRiddle());
        String userAnswer = prompter.prompt("Answer: ", "[a-zA-z]+", "\nAnswer should be all letters\n");

        if (userAnswer.equalsIgnoreCase(randomRiddle.getAnswer())) {
            completed = true;
        }
        return completed;
    }

    private void userSelectedInventory() {
        Console.clear();
        List<Inventory> userInventory = player.getUserInventory();

        System.out.printf("%s's Inventory: \n", player.getName());
        System.out.printf("CurrentWeapon: %s \n\n", player.getCurrentWeapon());


        for (int i = 0; i < userInventory.size(); i++) {
            System.out.printf("[%s] - %s\n", i, userInventory.get(i));
        }

        String input = prompter.prompt("\nSelect an item number for details or [E]xit: ", String.format("[eE0-%s]", userInventory.size()), "\nNot Valid\n ");
        if ("E".equals(input.toUpperCase())) {
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
        System.out.printf("%s Details: \n", itemSelected);
        System.out.printf("%s\n", itemDescription);

        String selection = prompter.prompt("\n[U]se item \n[E]xit\n\nSelect option: ", "[ueUE]", "\nPlease enter valid selection\n");
        switch (selection.toUpperCase()) {
            case "U":
                if (itemSelected instanceof Weapon) {
                    Weapon weapon = (Weapon) itemSelected;
                    player.setCurrentWeapon(weapon);
                } else if (itemSelected instanceof Tool) {
                    Tool tool = (Tool) itemSelected;
                    player.useTool(tool);
                }
                Console.pause(1500);
                break;
            case "E":
                userSelectedInventory();
                break;
        }
        userSelectedInventory();
    }

    private boolean keyMessage() {
        boolean canTake = false;

        if (player.hasKey()) {
            System.out.println("You already have a key.. go open a door or something. Come back later");
        } else {
            System.out.println("One key for you my friend!");
            player.setHasKey(true);
            canTake = true;
        }
        return canTake;
    }

    private void landOnWeapon() {
        Weapon randomWeapon = Weapon.getRandomWeapon();

        if (player.getUserInventory().contains(randomWeapon)) {
            System.out.printf("\nLooks like you already have a %s. This wont be useful to you.", randomWeapon);
        } else {
            player.addWeapon(randomWeapon);
            System.out.printf("\nNew weapon added to inventory: %s\n", randomWeapon);

        }
    }

    private void landOnTool() {
        boolean random = Math.random() < 0.5;
        Tool newTool;

        if (random) {
            newTool = Potion.create();
        } else {
            newTool = Armor.create();
        }

        System.out.printf("\nWoohoo! New %s added to inventory.\n", newTool.getClass().getSimpleName());
        player.addTool(newTool);
    }

    private boolean lockedDoor() {
        boolean completed = false;
        if (player.hasKey()) {
            System.out.println("I guess that key was pretty useful after all.");
            completed = true;
            player.setHasKey(false);
        } else {
            System.out.println("It looks as though you don't have a key. Go find one.");
        }
        return completed;
    }

    private boolean inspectPicture() {
        boolean completed = false;
        System.out.println("Cool a picture! Should we check it out?");
        String input = prompter.prompt("\n[I]nspect Picture \n[E]xit\n\nSelect option: ", "[ieIE]", "\nThat's not valid.\n");

        switch (input.toUpperCase()) {
            case "I":
                int randomPicture = playerMap.getRandomExitCodeDigit();
                printDigitPicture(randomPicture);
                completed = true;
                break;
            case "E":
                break;
        }
        return completed;
    }

    private void printDigitPicture(int imageNumber) {
        String pictureNumToString = Integer.toString(imageNumber);
        String fileName = String.format("images/numbers/image%s.txt", pictureNumToString);
        printFile(fileName);
        System.out.println("I would write this down somewhere if I were you.");
        Console.pause(3000L);
        Console.clear();
    }

    private void printFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean attemptExit() {
        boolean completed = false;
        if (attemptsToExit == 0) {
            System.out.println("You have had enough tries! Goodbye forever.");
            player.setHealth(0);
        } else {
            System.out.println("Please enter 4 digit code to exit. Order does not matter.");
            String userAttempt = prompter.prompt("Code: ", "\\d{1,4}", "\nNot valid input code MUST be 4 digits\n");

            boolean isValidCode = verifyCode(userAttempt);

            if (isValidCode) {
                completed = true;
                gameIsOver = true;
                userWon = true;
                System.out.println("I think you got it!");
            } else {
                System.out.printf("\nYou have %s attempts left\n", attemptsToExit);
                attemptsToExit--;
            }
        }

        return completed;
    }

    private boolean verifyCode(String userInput) {
        boolean isCorrectCode = false;
        List<Integer> mapExitCode = playerMap.getExitCode();
        List<Integer> userAttempt = new ArrayList<>();
        char[] input = userInput.toCharArray();


        for (char codeDigit : input) {
            userAttempt.add(Character.getNumericValue(codeDigit));
        }

        Collections.sort(mapExitCode);
        Collections.sort(userAttempt);

        if (userAttempt.equals(mapExitCode)) {
            isCorrectCode = true;
        }


        return isCorrectCode;
    }
}
