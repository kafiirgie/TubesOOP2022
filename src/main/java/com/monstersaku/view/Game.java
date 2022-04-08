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
            // SET GAME DATA
            setupGameData();
            System.out.printf("Please wait"); loading();
            // SET PLAYER
            setupPlayer();
            
            // GAME
            boolean isGameRunning = true;
            while (isGameRunning) {
                // ROUND
                int id = 1;
                Player playerTurn = player1;
                Player playerOpponent = player2;
                boolean isRoundRunning = true;
                while (isRoundRunning) {
                    // TURN
                    if (id == 2) { playerTurn = player2; playerOpponent = player1; }
                    // if (!playerTurn.getActiveMonster().getIsAlive()) {
                    //     playerTurn.activeMonsterDied();
                    //     playerTurn.selectActiveMonster();
                    // }
                    boolean isTurnRunning = true;
                    Config.clearConsole();
                    System.out.println("===== BEGINNING OF TURN =====");
                    System.out.println("Now is " + playerTurn.getName() + "'s turn");
                    while (isTurnRunning) {    
                        showAction();
                        int action = selectAction(playerTurn, playerOpponent);
                        if (action == 1 | action == 2) { isTurnRunning = false; }
                    }
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
    
    public static void loading() {
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(500);
                System.out.printf(". ");
            }
            System.out.printf("\n");
        } catch (Exception e) {
            System.out.println("[Exception in loading] : " + e.getMessage());
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
        System.out.println("[1] Move Monster");
        System.out.println("[2] Switch Monster");
        System.out.println("[3] Monster Info");
        System.out.println("[4] Game Info");
    }

    public static int selectAction(Player player1, Player player2) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select action : ");
        int input = sc.nextInt();
        if (input == 1) {
            player1.selectMonsterMove();
        } else if (input == 2) {
            player1.switchActiveMonster();
        } else if (input == 3) {
            player1.showMonsterInGameInfo();
        } else if (input == 4) {
            player1.showPlayerInfo();
            player2.showPlayerInfo();
        }
        return input;
    }
}
