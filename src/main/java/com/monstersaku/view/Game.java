package com.monstersaku.view;

import java.util.Scanner;
import java.lang.Thread;
import java.util.Random;

import com.monstersaku.util.*;
import com.monstersaku.monsters.Monster;
import com.monstersaku.monsters.statuscondition.StatusConditionType;
import com.monstersaku.players.*;

public class Game {
    private static Player player1;
    private static Player player2;
    private static Player winnerPlayer;

    public static void play() {
        //try {
            // SET GAME DATA
            System.out.printf("Please wait"); loading();
            setupGameData();
            // SET PLAYER
            setupPlayer();
            
            // GAME
            boolean isGameRunning = true;
            while (isGameRunning) {
                // ROUND
                int id = 1;
                Player playerTurn = player1;
                Player playerOpponent = player2;
                int[] movePlayers = {-1, -1}; // stores player 1 and player 2 move selection
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
                        int moveAction = selectAction(playerTurn, playerOpponent);
                        movePlayers[id-1] = moveAction;
                        // handle sleep status
                        if (moveAction > 0 && playerTurn.getActiveMonster().getStatus() == StatusConditionType.SLEEP) {
                            System.out.println(playerTurn.getActiveMonster().getName() + " has SLEEP status condition.");
                            System.out.println(playerTurn.getActiveMonster().getName() + " can't do move for this turn.");
                            System.out.println("Select another monster");
                            playerTurn.switchActiveMonster();
                        }
                        // handle paralyze status
                        if (moveAction > 0 && playerTurn.getActiveMonster().getStatus() == StatusConditionType.PARALYZE) {
                            System.out.println(playerTurn.getActiveMonster().getName() + " has PARALYZE status condition.");
                            Random r = new Random();
                            int random = r.nextInt(4);
                            if (random == 0) {
                                System.out.println(playerTurn.getActiveMonster().getName() + " can't do move.");
                                movePlayers[id-1] = 0;
                            } else {
                                System.out.println(playerTurn.getActiveMonster().getName() + " still can do move.");
                            }
                        }
                        // check move amunition
                        if (moveAction > 0 && playerTurn.getActiveMonster().getMoves().get(moveAction-1).getAmmunition() < 1) {
                            System.out.println("Oopss, " + playerTurn.getActiveMonster().getName() + " can't do this move. This move is out of amunition");
                            playerTurn.getActiveMonster().getMoves().remove(moveAction-1);
                            movePlayers[id-1] = -1;
                            System.out.println("You can select another action");
                        }
                        // end of turn (player choose move or switch)
                        if (moveAction >= 0 ) { isTurnRunning = false; }
                    }
                    System.out.println("===== END OF TURN =====");
                    // handle sleep status (reduce sleep counter)
                    for (Monster monster : playerTurn.getMonsters()) {
                        if (monster.getStatus() == StatusConditionType.SLEEP) {
                            if (monster.getSleepCounter() > 0) {
                                monster.setSleepCounter(monster.getSleepCounter() - 1); // reduce sleep counter
                            } else {
                                monster.setStatus(null);                                // release SLEEP status
                            }
                        }
                    }
                    // check is end game
                    if (playerTurn.getMonsters().isEmpty()) { isGameRunning = false; winnerPlayer = playerOpponent; break; }
                    // go to next player
                    id++;
                    if (id > 2) { isRoundRunning = false; } // round is end
                }
                
                // HANDLE PLAYERS MOVE (based on priority and speed)
                // TODO:simplified this code
                if (movePlayers[0] > 0 && movePlayers[1] > 0) {          // both players do move
                    if (player1.getActiveMonster().getMoves().get(movePlayers[0]-1).getPriority() > player2.getActiveMonster().getMoves().get(movePlayers[1]-1).getPriority()) {
                        Monster monster = player1.getActiveMonster();
                        Monster monsterTarget = player2.getActiveMonster();
                        monster.getMoves().get(movePlayers[0]-1).doMove(monster, monsterTarget);
                    } else if (player1.getActiveMonster().getMoves().get(movePlayers[0]-1).getPriority() < player2.getActiveMonster().getMoves().get(movePlayers[1]-1).getPriority()) {
                        Monster monster = player2.getActiveMonster();
                        Monster monsterTarget = player1.getActiveMonster();
                        monster.getMoves().get(movePlayers[1]-1).doMove(monster, monsterTarget);
                    } else {
                        if (player1.getActiveMonster().getStats().getSpeed() > player2.getActiveMonster().getStats().getSpeed()) {
                            Monster monster = player1.getActiveMonster();
                            Monster monsterTarget = player2.getActiveMonster();
                            monster.getMoves().get(movePlayers[0]-1).doMove(monster, monsterTarget);
                        } else if (player1.getActiveMonster().getStats().getSpeed() < player2.getActiveMonster().getStats().getSpeed()) {
                            Monster monster = player2.getActiveMonster();
                            Monster monsterTarget = player1.getActiveMonster();
                            monster.getMoves().get(movePlayers[1]-1).doMove(monster, monsterTarget);
                        } else {
                            Random r = new Random();
                            int player = r.nextInt(2) + 1;
                            if (player == 1) {
                                Monster monster = player1.getActiveMonster();
                                Monster monsterTarget = player2.getActiveMonster();
                                monster.getMoves().get(movePlayers[0]-1).doMove(monster, monsterTarget);
                            } else if (player == 2) {
                                Monster monster = player2.getActiveMonster();
                                Monster monsterTarget = player1.getActiveMonster();
                                monster.getMoves().get(movePlayers[1]-1).doMove(monster, monsterTarget);
                            }
                        }
                    }
                } else if (movePlayers[0] > 0 && movePlayers[1] <= 0) {  // only player 1 do move
                    Monster monster = player1.getActiveMonster();
                    Monster monsterTarget = player2.getActiveMonster();
                    monster.getMoves().get(movePlayers[0]-1).doMove(monster, monsterTarget);
                } else if (movePlayers[0] <= 0 && movePlayers[1] > 0) {  // only player 2 do move
                    Monster monster = player2.getActiveMonster();
                    Monster monsterTarget = player1.getActiveMonster();
                    monster.getMoves().get(movePlayers[1]-1).doMove(monster, monsterTarget);
                } else if (movePlayers[0] <= 0 && movePlayers[1] <= 0) { // both players not do move
                    System.out.println("No players choose move, get ready for next round!!");
                }
                
                // AFTER DAMAGE CALCULATION
                if (player1.getActiveMonster().getStatus() == StatusConditionType.BURN) {
                    double damage = Math.floor(player1.getActiveMonster().getStats().getMaxHealthPoint() / 8);
                    player1.getActiveMonster().takeDamage(damage);
                } else if (player1.getActiveMonster().getStatus() == StatusConditionType.POISON) {
                    double damage = Math.floor(player1.getActiveMonster().getStats().getMaxHealthPoint() / 16);
                    player1.getActiveMonster().takeDamage(damage);
                }
                if (player2.getActiveMonster().getStatus() == StatusConditionType.BURN) {
                    double damage = Math.floor(player2.getActiveMonster().getStats().getMaxHealthPoint() / 8);
                    player2.getActiveMonster().takeDamage(damage);
                } else if (player2.getActiveMonster().getStatus() == StatusConditionType.POISON) {
                    double damage = Math.floor(player2.getActiveMonster().getStats().getMaxHealthPoint() / 16);
                    player2.getActiveMonster().takeDamage(damage);
                }
                
                // HANDLE MONSTER DIED
                if (!player1.getActiveMonster().getIsAlive()) {
                    player1.activeMonsterDied();
                } else if (!player2.getActiveMonster().getIsAlive()) {
                    player2.activeMonsterDied();
                }
                
                // CHECK IS END GAME
                if (player1.getMonsters().isEmpty()) { isGameRunning = false; winnerPlayer = player2;} // game is end
                if (player2.getMonsters().isEmpty()) { isGameRunning = false; winnerPlayer = player1;} // game is end
            }
            
            // END GAME - SHOW WINNER
            System.out.println("THE GAME IS END");
            System.out.printf("\nCONGRATULATION to %s for became the winner!!\n", winnerPlayer.getName());

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
        int move = -1;
        if (input == 1) {
            move = player1.selectMonsterMove(); // move > 0 indicates player choose move
        } else if (input == 2) {
            player1.switchActiveMonster();      // move = 0 indicates player choose swicth
            move = 0;
        } else if (input == 3) {
            player1.showMonsterInGameInfo();    // move < 0 indicates player choose show monster or game info
        } else if (input == 4) {
            player1.showPlayerInfo();           // move < 0 indicates player choose show monster or game info
            player2.showPlayerInfo();
        }
        return move;
    }
}
