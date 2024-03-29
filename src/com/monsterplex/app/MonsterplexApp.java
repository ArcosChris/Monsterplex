package com.monsterplex.app;

import com.monsterplex.game.Character;
import com.monsterplex.game.*;
import com.monsterplex.util.Clock;
import com.monsterplex.util.Console;
import com.monsterplex.util.FileHelper;
import com.monsterplex.util.Prompter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * Controller - directs flow of an app like user promp,
 * sending provided inputs to system classes
 */
public class MonsterplexApp {
    private final Prompter prompter = new Prompter(new Scanner(System.in));
    private int attemptsToExit = 3;
    private Player player = null;
    private UserMap playerMap = null;
    private boolean gameIsOver = false;
    private boolean userWon = false;
    private final int timeToComplete = 10;
    private final Clock clock = new Clock(timeToComplete);


    public void execute() throws IOException {
        welcome();
        String name = promptUserForName();
        player = Player.create(name);
        playerMap = UserMap.create();
        printDirections();
        Console.pause(800);
        game();

        String input = prompter.prompt("\nWould you like to play again ? [Y]es or [N]\n", "[yYnN]", "\nInvalid entry\n");
        if("Y".equalsIgnoreCase(input)){
            Console.clear();
            new MonsterplexApp().execute();
        }
        Console.clear();
    }

    private void welcome() throws IOException {
        FileHelper.loadAndPrint("MonsterplexArt.txt");
    }

    private String promptUserForName() {
        return prompter.prompt("PLEASE ENTER YOUR NAME TO BEGIN: ", "[a-zA-Z]+", "\nName should only have letters.\n");
    }

    private void printDirections() {
        String directions = String.format("\nWelcome, %s. The game rules are very simple... just don't die. You have %s minutes to escape.\n", player.getName(), timeToComplete);
        for (char c : directions.toCharArray()) {
            Console.pause(100);
            System.out.print(c);
        }
    }

    private void game() throws IOException {
        Console.clear();
        clock.start();
        System.out.println("Your timer has started... Good luck! ");

        if (!gameIsOver) {
            playerMap.show();
        }

        while (!player.isDead() && !gameIsOver && clock.isTimeRemaining()) {
            System.out.printf("Time remaining: %s", clock.timeRemainingAsString());
            System.out.println("\n\nPlease enter a direction [N]orth, [E]ast, [S]outh, [W]est or [I]nventory:");
            String input = prompter.prompt("Enter command: ", "[NESWIneswi]{1}", "\nNot quite, try again.\n");
            switch (input.toUpperCase()) {
                case "N":
                case "E":
                case "S":
                case "W":
                    Console.clear();
                    userSwitchPosition(input);
                    if (!gameIsOver && !player.isDead() && clock.isTimeRemaining()) {
                        playerMap.show();
                    }
                    break;
                case "I":
                    printUserInventory();
                    Console.clear();
                    playerMap.show();
                    break;
            }
        }

        Console.pause(2000);

        if (userWon) {
            //win message
           FileHelper.loadAndPrint("YouWin.txt");
        } else if (!clock.isTimeRemaining()) {
            FileHelper.loadAndPrint("timeisUp.txt");
        } else if (player.isDead()) {
            FileHelper.loadAndPrint("Dead.txt");
        }
    }

    private void userSwitchPosition(String direction) throws IOException {
        int[] nextPosition = playerMap.getNextCoordinates(direction);
        int x = nextPosition[0];
        int y = nextPosition[1];
        char characterAtNext = playerMap.getCharacterAtPosition(x, y);

        if (featureAtPosition(characterAtNext)) {
            boolean userCompleted = getFeaturePrompt(characterAtNext);

            if (userCompleted) {
                playerMap.setCurrentPosition(x, y);
            }
        } else {
            playerMap.setCurrentPosition(x, y);
        }
        int[] playerPosition = playerMap.getPlayerPosition();
        checkForMonsters(playerPosition[0], playerPosition[1]);
    }

    private void checkForMonsters(int x, int y) {
        List<int[]> surroundings = new ArrayList<>();
        surroundings.add(new int[]{x, y + 1});
        surroundings.add(new int[]{x, y - 1});
        surroundings.add(new int[]{x - 1, y});
        surroundings.add(new int[]{x + 1, y});
        surroundings.add(new int[]{x - 1, y - 1});
        surroundings.add(new int[]{x + 1, y - 1});
        surroundings.add(new int[]{x - 1, y + 1});
        surroundings.add(new int[]{x + 1, y + 1});

        for (int[] item : surroundings) {
            int col = item[0];
            int row = item[1];

            if (col <= 0 || col > playerMap.getMapLength() || row <= 0 || row > playerMap.getMapWidth()) {
                continue;
            }

            if (playerMap.getCharacterAtPosition(col, row) == 'M') {
                boolean playerWon = monsterFight();
                if (playerWon) {
                    playerMap.setCurrentPosition(col, row);
                } else {
                    gameIsOver = true;
                    userWon = false;
                }
            }
        }
    }

    private boolean monsterFight() {
        boolean playerWon = false;
        Monster monster = Monster.createRandom();

        //do monster presentation stuff here
        while (!monster.isDead() && !player.isDead()) {
            if(!player.isDead()) {
                monster.attack(player);
                Console.pause(1000);
            }
            else{
                break;
            }

            String userInput = prompter.prompt("\n[A]ttack \n[I]Inventory\n\nSelect option: ", "[aAiI]", "\nNot valid comand.\n");
            switch (userInput.toUpperCase()) {
                case "A":
                    if(!monster.isDead()) {
                        player.attack(monster);
                        Console.pause(1000);
                    }
                    break;
                case "I":
                    printUserInventory();
                    break;
            }
        }
        Console.clear();
        if (monster.isDead()) {
            playerWon = true;
            System.out.printf("You successfully killed %s, your current health is: %s\n", monster.getMonsterType(), player.getHealth());
        }else if(player.isDead()){
            System.out.printf("You have been killed by %s\n", monster.getMonsterType());
        }
        Console.pause(3000);
        Console.clear();
        return playerWon;
    }

    //feature methods
    private boolean featureAtPosition(char symbol) {
        return Feature.getFeatureBySymbol(symbol) != null;
    }

    private boolean getFeaturePrompt(char symbol) throws IOException {
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
                attemptExit();
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

        System.out.println("Answer the following riddle to enter room.\n");
        System.out.println(randomRiddle.getRiddle());
        String userAnswer = prompter.prompt("Answer: ", "[a-zA-z]+", "\nAnswer should be all letters\n");

        if (userAnswer.equalsIgnoreCase(randomRiddle.getAnswer())) {
            completed = true;
            System.out.println("You may now enter...");
        }
        else{
            System.out.println("Try again later.");
        }
        Console.pause(2000);
        Console.clear();
        return completed;
    }

    private void printUserInventory() {
        Console.pause(1000L);
        Console.clear();
        List<Inventory> userInventory = player.getUserInventory();

        System.out.printf("%s's Inventory: \n", player.getName());
        System.out.printf("Current Weapon: %s \n\n", player.getCurrentWeapon());


        for (int i = 0; i < userInventory.size(); i++) {
            System.out.printf("[%s] - %s\n", i, userInventory.get(i));
        }
        inventoryDetailPrompt();
    }

    private void inventoryDetailPrompt() {
        String input = prompter.prompt("\nSelect an item number for details: ", String.format("[0-%s]", player.getUserInventory().size()), "\nNot Valid\n ");

        int item = Integer.parseInt(input);
        Inventory itemSelected = player.getUserInventory().get(item);
        String itemDescription = itemSelected.getDescription();
        Console.clear();
        System.out.printf("%s Details: \n", itemSelected);
        System.out.printf("%s\n", itemDescription);

        String selection = prompter.prompt("\n[U]se item\n[E]xit\n\nSelect option: ", "[ueUE]", "\nPlease enter valid selection\n");
        switch (selection.toUpperCase()) {
            case "U":
                if (itemSelected instanceof Weapon) {
                    Weapon weapon = (Weapon) itemSelected;
                    player.setCurrentWeapon(weapon);
                    System.out.printf("Ahh yes, the %s. Good choice.\n", weapon.getDisplay());
                } else if (itemSelected instanceof Tool) {
                    Tool tool = (Tool) itemSelected;
                    player.useTool(tool);
                    if (player.getHealth() != Character.MAX_HEALTH) {
                        System.out.printf("This %s should be helpful with all the monsters running around.\n", tool.getClass().getSimpleName());
                    }
                }
                Console.pause(2000);
                Console.clear();
                break;
            case "E":
                break;
        }
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
            System.out.printf("Looks like you already have a %s. This wont be useful to you.\n", randomWeapon);
        } else {
            player.addWeapon(randomWeapon);
            System.out.printf("New weapon added to inventory: %s\n", randomWeapon);

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

        System.out.printf("Woohoo! New %s added to inventory.\n", newTool.getClass().getSimpleName());
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

    private boolean inspectPicture() throws IOException {
        boolean completed = false;
        System.out.println("Cool, a picture! Should we check it out?");
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
        Console.clear();
        return completed;
    }

    private void printDigitPicture(int imageNumber) throws IOException {
        String pictureNumToString = Integer.toString(imageNumber);
        String fileName = String.format("numbers/image%s.txt", pictureNumToString);
        FileHelper.loadAndPrint(fileName);
        System.out.println("I would write this down somewhere if I were you.");
        Console.pause(3000L);
        Console.clear();
    }

    private void attemptExit() {
        if (attemptsToExit == 0) {
            System.out.println("You have had enough tries! Goodbye forever.");
            player.setHealth(0);
        }
        else {
            System.out.printf("You have %s attempts left.", attemptsToExit);
            System.out.println("Please enter 4 digit code to exit. Order does not matter.");
            String userAttempt = prompter.prompt("Code: ", "\\d{1,4}", "\nNot valid input code MUST be 4 digits\n");

            boolean isValidCode = verifyCode(userAttempt);

            if (isValidCode) {
                gameIsOver = true;
                userWon = true;
                System.out.println("I think you got it!");
            } else {
                attemptsToExit--;
                System.out.printf("\nYou have %s attempts left\n", attemptsToExit);
            }
        }
        Console.pause(2000);
        Console.clear();
    }

    private boolean verifyCode(String userInput) {
        boolean isCorrectCode = false;
        List<Integer> mapExitCode = playerMap.getExitCode();
        List<Integer> userAttempt = new ArrayList<>();
        char[] input = userInput.toCharArray();


        for (char codeDigit : input) {
            userAttempt.add(java.lang.Character.getNumericValue(codeDigit));
        }

        Collections.sort(mapExitCode);
        Collections.sort(userAttempt);

        if (userAttempt.equals(mapExitCode)) {
            isCorrectCode = true;
        }


        return isCorrectCode;
    }
}
