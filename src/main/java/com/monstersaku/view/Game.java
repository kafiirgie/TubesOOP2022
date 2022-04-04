package com.monstersaku.view;

import java.util.Scanner;
import java.lang.Thread;

import com.monstersaku.util.*;
import com.monstersaku.players.*;

public class Game {
    private static boolean isGameRunning = true;
    private static Player player1;
    private static Player player2;
    //private static Player[] players = [player1, player2];

    public static void play() {
        try {
            //while (isGameRunning) {
                System.out.flush();
                
                // SET GAME DATA
                setupGameData();
                System.out.println("Please wait...");
                Thread.sleep(1000);
                
                // SET PLAYER
                setupPlayer();
                
                // GAME ROUND
                boolean isCurrentRound = true;
                while (isCurrentRound) {
                    // PLAYER TURN
                    int id = 1;
                    Player playerTurn = player1;
                    boolean isCurrentTurn = true;
                    while (isCurrentTurn) {
                        if (id == 2) { playerTurn = player2; }
                        System.out.flush();
                        if (!playerTurn.getActiveMonster().getIsAlive()) {
                            playerTurn.activeMonsterDied();
                            playerTurn.selectActiveMonster();
                        }
                        showAction();
                        selectAction(playerTurn);
                        id++;
                        if (id > 2) { isCurrentTurn = false; }
                    }

                    // TODO : kondisi game berakhir

                }
            //}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
        Scanner sc = new Scanner(System.in);
        System.out.printf("Player 1 name : ");
        String playerName1 = sc.next();
        System.out.printf("Player 2 name : ");
        String playerName2 = sc.next();
        player1 = new Player(1, playerName1);
        player2 = new Player(2, playerName2);
        sc.close();
    }

    public static void showAction() {
        System.out.println("----- ACTION -----");
        System.out.println("[1] Move");
        System.out.println("[2] Switch");
    }

    public static void selectAction(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select action : ");
        int input = sc.nextInt();
        if (input == 1) {
            player.selectMonsterMove();
        } else if (input == 2) {
            player.switchActiveMonster();
        }
        sc.close();
    }
}
