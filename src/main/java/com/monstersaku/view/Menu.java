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
                System.out.println("[Input Exception] : Invalid input value, please input in option range");
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
        System.out.println("DESKRIPSI PERMAINAN");
        System.out.println("Monster Saku adalah permainan yang diadaptasi dari permainan Pokemon.");
        System.out.println("Permainan ini merupakan jenis permainan PvP yang bisa dimainkan oleh 2 pemain yang saling berlawanan.");
        System.out.println("Masing-masing pemain akan menerima kombinasi 6 monster yang ditentukan secara acak oleh aplikasi pada setiap permainan");
        
        System.out.println("\nCARA BERMAIN");
        System.out.println("1. Masukkan nama pemain.");
        System.out.println("2. Pada saat permainan dimulai, setiap pemain diberikan pilihan 6 Monster di awal permainan.");
        System.out.println("3. Permainan akan memasuki fase pertarungan.");
        System.out.println("4. Setiap pemain diberikan giliran untuk memilih menyerang lawan atau mengganti monster.");
        System.out.println("5. Jika pemain memilih untuk menyerang lawan, pemain dapat memilih move yang dimiliki.");
        System.out.println("6. Jika pemain memilih untuk melakukan pergantian Monster, maka ia tidak bisa menyerang lawan pada giliran itu.");
        System.out.println("7. Move akan dieksekusi secara bergiliran berdasarkan prioritas dan speed ketika kedua pemain memilih untuk saling menyerang.");
        System.out.println("8. Pemain yang tidak memiliki Monster yang dapat digunakan untuk bertarung dinyatakan kalah.");
    }
    
    public static void exit() {
        Config.clearConsole();
        System.out.println("===== EXIT =====\n");
        System.out.printf("processing to exit from the game"); Config.loading();
        Exit.exitCredits();
    }
}
