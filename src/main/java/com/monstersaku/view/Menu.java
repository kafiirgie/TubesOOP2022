package com.monstersaku.view;

public class Menu {
    public static void showWelcome() {
        System.out.println("Welcome to 'MONSTER SAKU'");
    }

    public static void showStartupMenu() {
        System.out.println("----- MENU -----");
        System.out.println("[1] Start Game");
        System.out.println("[2] Help");
        System.out.println("[0] Exit");
    }

    public static void runMenu(int input) {
        if (input == 1) {
            startGame();
        } else if (input == 2) {
            help();
        } else if (input == 3) {
            exit();
        }
    }

    public static void startGame() {
        System.out.println("game already started...");
    }

    public static void help() {
        System.out.println("hi, what can we help you?");
    }

    public static void exit() {
        Exit.exitCredits();
        System.out.println("processing to exit from the game...");
    }
}
