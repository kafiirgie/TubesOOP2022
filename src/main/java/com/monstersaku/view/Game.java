package com.monstersaku.view;

import java.util.Scanner;

import com.monstersaku.util.*;
import com.monstersaku.players.*;

public class Game {
    private static boolean isGameRunning = true;
    private static Player player1;
    private static Player player2;
    
    public static void setupGameData() {
        Config.setAllElementEff();
        Config.setAllMonster();    
    }

    public static void setupPlayer() {
        Scanner sc = new Scanner(System.in);
        String playerName1 = sc.next();
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
        System.out.printf("Select : ");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        if (input == 1) {
            player.selectMonsterMove();
        } else if (input == 2) {
            player.switchActiveMonster();
        }
        sc.close();
    }
}
