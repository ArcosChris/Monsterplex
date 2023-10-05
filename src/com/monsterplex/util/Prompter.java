package com.monsterplex.util;

import java.util.Scanner;

public class Prompter {
    private final Scanner scanner;

    public Prompter(Scanner scanner) {
        this.scanner = scanner;
    }

    public void prompt(String message) {
        System.out.println(message);
        System.out.print("> ");
    }

    public String prompt(String message, String regex, String error) {
        String userInput;
        boolean isValid;

        while (true) {
            prompt(message);
            userInput = getInput();
            isValid = userInput.matches(regex);

            if (isValid) {
                break;
            }
            System.out.println(error);
        }
        return userInput;
    }

    private String getInput() {
        return this.scanner.nextLine().toUpperCase().trim();
    }
}
