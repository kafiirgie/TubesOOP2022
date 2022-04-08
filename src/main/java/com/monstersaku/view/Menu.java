package com.monstersaku.view;

import java.util.Scanner;

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

    public static void selectMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select : ");
        int input = sc.nextInt();
        if (input == 1) {
            startGame();
        } else if (input == 2) {
            help();
        } else if (input == 0) {
            exit();
        }
    }

    public static void startGame() {
        Game.play();
    }

    public static void help() {
        System.out.println("hi, what can we help you?");
    }

    public static void exit() {
        try {
            System.out.println("processing to exit from the game...");
            Thread.sleep(1000);
            Exit.exitCredits();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
