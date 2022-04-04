package com.monstersaku.view;

import java.util.Scanner;
import java.lang.Thread;

import com.monstersaku.util.*;
import com.monstersaku.players.*;

public class Game {
    private static Player player1;
    private static Player player2;

    public static void play() {
        //try {
            //System.out.flush();
            // SET GAME DATA
            setupGameData();
            System.out.println("Please wait...");
            //Thread.sleep(1000);
            // SET PLAYER
            setupPlayer();
            
            // GAME
            boolean isGameRunning = true;
            while (isGameRunning) {
                // ROUND
                int id = 1;
                Player playerTurn = player1;
                boolean isRoundRunning = true;
                while (isRoundRunning) {
                    // TURN
                    if (id == 2) { playerTurn = player2; }
                    if (!playerTurn.getActiveMonster().getIsAlive()) {
                        playerTurn.activeMonsterDied();
                        playerTurn.selectActiveMonster();
                    }
                    Config.clearConsole();
                    System.out.println("===== BEGINNING OF TURN =====");
                    System.out.println("Now is " + playerTurn.getName() + "'s turn");
                    showAction();
                    selectAction(playerTurn);
                    System.out.println("===== END OF TURN =====");
                    id++;
                    if (playerTurn.getMonsters().isEmpty()) { isGameRunning = false; break; } // game is end
                    if (id > 2) { isRoundRunning = false; } // go to next round
                }
            }
        //} catch (Exception e) {
            //System.out.println("Game.play");
            //System.out.println(e.getMessage());
        //}
    } 
    
    public static void setupGameData() {
        try {
            Config.setAllElementEff();
            Config.setAllMonster(); 
            System.out.println("Data has been loaded successfully");   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setupPlayer() {
        Player.setRandomIdMonster();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Player 1 name : ");
        String playerName1 = sc.next();
        System.out.printf("Player 2 name : ");
        String playerName2 = sc.next();
        player1 = new Player(1, playerName1);
        player2 = new Player(2, playerName2);
    }

    public static void showAction() {
        System.out.println("----- ACTION -----");
        System.out.println("[1] Move");
        System.out.println("[2] Switch");
        System.out.println("[3] Info");
    }

    public static void selectAction(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select action : ");
        int input = sc.nextInt();
        if (input == 1) {
            player.selectMonsterMove();
        } else if (input == 2) {
            player.switchActiveMonster();
        } else if (input == 3) {
            player.showMonsterAlive();
        }
    }
}
