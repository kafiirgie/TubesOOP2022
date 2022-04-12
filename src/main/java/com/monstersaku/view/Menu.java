package com.monstersaku.view;

import java.util.Scanner;

import com.monstersaku.util.Config;

public class Menu {
    public static void showWelcome() {
        System.out.println("Welcome to 'MONSTER SAKU'");
        // TODO:add welcoming text
    }

    public static void showStartupMenu() {
        System.out.println("\n###### MENU ######");
        System.out.println("[1] Start Game");
        System.out.println("[2] Help");
        System.out.println("[0] Exit");
    }

    public static void selectMenu() {
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.printf("\nSelect menu : ");
                int input = sc.nextInt();
                if (input == 1) {
                    isInputValid = true;
                    startGame();
                } else if (input == 2) {
                    isInputValid = true;
                    help();
                    Config.goToNextPage();
                    Config.clearConsole();
                    showWelcome();
                    showStartupMenu();
                    selectMenu();
                } else if (input == 0) {
                    isInputValid = true;
                    exit();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Invalid input value, please input either 0, 1, or 2");
            } catch (Exception e) {
                System.out.println("[Input Exception] : Wrong input type, please input in integer type");
            }
        }
    }

    public static void startGame() {
        Config.clearConsole();
        System.out.println("===== GAME =====\n");
        Game.play();
    }

    public static void help() {
        Config.clearConsole();
        System.out.println("===== HELP =====\n");
        System.out.println("hi, what can we help you?");
        System.out.println();
        // TODO:create help text
    }
    
    public static void exit() {
        Config.clearConsole();
        System.out.println("===== EXIT =====\n");
        System.out.printf("processing to exit from the game"); Config.loading();
        Exit.exitCredits();
    }
}
