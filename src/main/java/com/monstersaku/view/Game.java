package com.monstersaku.view;

import java.util.Scanner;
import java.util.Random;

import com.monstersaku.util.Config;
import com.monstersaku.monsters.Monster;
import com.monstersaku.monsters.statuscondition.StatusConditionType;
import com.monstersaku.players.Player;

public class Game {
    private static Player player1;
    private static Player player2;
    private static Player winnerPlayer;
    private static boolean isGameRunning = true;
    private static int countRound = 1;

    public static void play() {
            // SET GAME DATA
            System.out.printf("please wait"); Config.loading();
            setupGameData();

            // SET PLAYER
            setupPlayer();
            
            // GAME STARTED
            while (isGameRunning) {
                // Stores player 1 and player 2 move selection
                int[] movePlayers = {-1, -1};
                
                // Round started with player 1
                int id = 1;
                Player playerTurn = player1; Player playerOpponent = player2;
                
                // ROUND STARTED
                boolean isRoundRunning = true;
                while (isRoundRunning) {
                    // Check the id, is now player 2 turn or not
                    if (id == 2) { playerTurn = player2; playerOpponent = player1; }
                    
                    // Setup display
                    Config.clearConsole();
                    System.out.printf("##### ROUND %d #####\n", countRound);
                    
                    // TURN STARTED
                    boolean isTurnRunning = true;
                    while (isTurnRunning) {    
                        // Setup display
                        System.out.println("\n===== BEGINNING OF TURN =====");
                        System.out.println("Now is " + playerTurn.getName() + "'s turn\n");
                        // Selecting action
                        showAction();
                        int moveAction = selectAction(playerTurn, playerOpponent);
                        movePlayers[id-1] = moveAction;
                        // Handle SLEEP status (is monster can do move or not)
                        if (moveAction > 0 && playerTurn.getActiveMonster().getStatus() == StatusConditionType.SLEEP) {
                            System.out.println(playerTurn.getActiveMonster().getName() + " has SLEEP status condition.");
                            System.out.println(playerTurn.getActiveMonster().getName() + " can't do move for this turn.");
                            System.out.println("Select another monster");
                            playerTurn.switchActiveMonster();
                        }
                        // Handle PARALYZE status (is monster can do move or not)
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
                        // Check move amunition
                        if (moveAction > 0 && playerTurn.getActiveMonster().getMoves().get(moveAction-1).getAmmunition() < 1) {
                            System.out.println("Oopss, " + playerTurn.getActiveMonster().getName() + " can't do this move. This move is out of amunition");
                            playerTurn.getActiveMonster().getMoves().remove(moveAction-1);
                            movePlayers[id-1] = -1;
                            System.out.println("You can select another action");
                        }
                        // End of turn (player choose move or switch)
                        if (moveAction >= 0) { isTurnRunning = false; }
                    }
                    System.out.println("\n===== END OF TURN =====");
                    Config.loading();

                    // Handle SLEEP status (Reduce sleep counter)
                    for (Monster monster : playerTurn.getMonsters()) {
                        if (monster.getStatus() == StatusConditionType.SLEEP) {
                            if (monster.getSleepCounter() > 0) {
                                monster.setSleepCounter(monster.getSleepCounter() - 1); // reduce sleep counter
                            } else {
                                monster.setStatus(null);                                // release SLEEP status
                            }
                        }
                    }
                    
                    id++; // Go to next player
                    if (id > 2) { isRoundRunning = false; } // Round is end, go to next round
                }
                countRound++;
                System.out.printf("\n##### ROUND %d IS END #####\n", countRound-1);

                // HANDLE PLAYERS MOVE (based on priority and speed)
                System.out.printf("\nexecuting players move"); Config.loading();
                if (movePlayers[0] > 0 && movePlayers[1] > 0) {          // both players do move
                    System.out.println("\nBoth players choose to move monster");
                    boolean player1MoveFirst;
                    // Check move priority
                    if (player1.getActiveMonster().getMoves().get(movePlayers[0]-1).getPriority() > player2.getActiveMonster().getMoves().get(movePlayers[1]-1).getPriority()) {
                        System.out.printf("Player %s move priority greater than player %s move priority\n", player1.getName(), player2.getName());
                        player1MoveFirst = true;
                    } else if (player1.getActiveMonster().getMoves().get(movePlayers[0]-1).getPriority() < player2.getActiveMonster().getMoves().get(movePlayers[1]-1).getPriority()) {
                        System.out.printf("Player %s move priority greater than player %s move priority\n", player2.getName(), player1.getName());
                        player1MoveFirst = false;
                    } else {
                        System.out.printf("Player %s move priority equals to player %s move priority\n", player1.getName(), player2.getName());
                        // Check monster speed
                        if (player1.getActiveMonster().getStats().getSpeed() > player2.getActiveMonster().getStats().getSpeed()) {
                            System.out.printf("-> Player %s monster speed greater than player %s monster speed\n", player1.getName(), player2.getName());
                            player1MoveFirst = true;
                        } else if (player1.getActiveMonster().getStats().getSpeed() < player2.getActiveMonster().getStats().getSpeed()) {
                            System.out.printf("-> Player %s monster speed greater than player %s monster speed\n", player2.getName(), player1.getName());
                            player1MoveFirst = false;
                        } else {
                            System.out.printf("-> Player %s monster speed equals to player %s monster speed\n", player1.getName(), player2.getName());
                            // Move executed by random
                            System.out.println("   -> Move players executed by random");
                            Random r = new Random();
                            int player = r.nextInt(2) + 1;
                            if (player == 1) {
                                player1MoveFirst = true;
                            } else { // player == 2
                                player1MoveFirst = false;
                            }
                        }
                    }
                    // Executed players move by the sequence
                    if (player1MoveFirst) {
                        playerActionMove(movePlayers, 1);
                        if (player2.getActiveMonster().getIsAlive()) {
                            playerActionMove(movePlayers, 2);
                            handlePlayersActiveMonsterDied();
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        } else {
                            handlePlayersActiveMonsterDied();
                            System.out.printf("\nPlayer %s active monster died, can't do move\n", player2.getName());
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        }
                    } else {
                        playerActionMove(movePlayers, 2);
                        if (player1.getActiveMonster().getIsAlive()) {
                            playerActionMove(movePlayers, 1);
                            handlePlayersActiveMonsterDied();
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        } else {
                            handlePlayersActiveMonsterDied();
                            System.out.printf("\nPlayer %s active monster died, can't do move\n", player1.getName());
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        }
                    }
                } else if (movePlayers[0] > 0 && movePlayers[1] <= 0) {  // only player 1 do move
                    System.out.printf("\nOnly player %s choose to move monster\n", player1.getName());
                    playerActionMove(movePlayers, 1);
                    handlePlayersActiveMonsterDied();
                    isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                } else if (movePlayers[0] <= 0 && movePlayers[1] > 0) {  // only player 2 do move
                    System.out.printf("\nOnly player %s choose to move monster\n", player2.getName());
                    playerActionMove(movePlayers, 2);
                    handlePlayersActiveMonsterDied();
                    isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                } else if (movePlayers[0] <= 0 && movePlayers[1] <= 0) { // both players not do move
                    System.out.println("\nNo players choose move monster, get ready for next round!!");
                }
                
                // AFTER DAMAGE CALCULATION
                afterDamageCalculation();
                
                // HANDLE MONSTER DIED
                handlePlayersActiveMonsterDied();
                // CHECK IS GAME STILL RUNNING
                isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                
                System.out.printf("\ngoing to next round");
                Config.loading();
            }
            
            // END GAME - SHOW WINNER
            Config.clearConsole();
            System.out.println("\nTHE GAME IS END");
            System.out.printf("\nCONGRATULATION to %s for became the winner!!\n", winnerPlayer.getName());
            System.out.printf("\nprocessing to exit from the game"); Config.loading();
            Exit.exitCredits();
    }
    
    public static void setupGameData() {
        try {
            System.out.println("\n----- SETUP GAME DATA -----");
            Config.setAllElementEff();
            Config.setAllMonster(); 
            System.out.println("Data has been loaded successfully\n");   
        } catch (Exception e) {
            System.out.println("[Exception in Setup Game Data] : " + e.getMessage());
        }
    }

    public static void setupPlayer() {
        System.out.println("\n----- SETUP PLAYER -----");
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
            System.out.println("\n[ GAME INFO ]");
            player1.showPlayerInfo();           // move < 0 indicates player choose show monster or game info
            player2.showPlayerInfo();
        }
        return move;
    }

    public static void playerActionMove(int[] movePlayers, int id) {
        if (id == 1) {
            Monster monster = player1.getActiveMonster();
            Monster monsterTarget = player2.getActiveMonster();
            System.out.printf("\n[Player %s] : %s do %s move\n", player1.getName(), monster.getName(), monster.getMoves().get(movePlayers[0]-1).getMoveName());
            monster.getMoves().get(movePlayers[0]-1).doMove(monster, monsterTarget);
        } else if (id == 2) {
            Monster monster = player2.getActiveMonster();
            Monster monsterTarget = player1.getActiveMonster();
            System.out.printf("\n[Player %s] : %s do %s move\n", player2.getName(), monster.getName(), monster.getMoves().get(movePlayers[1]-1).getMoveName());
            monster.getMoves().get(movePlayers[1]-1).doMove(monster, monsterTarget);
        }
    }

    public static void afterDamageCalculation() {
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
    }

    public static void handlePlayersActiveMonsterDied() {
        if (!player1.getActiveMonster().getIsAlive()) { player1.activeMonsterDied(); }
        if (!player2.getActiveMonster().getIsAlive()) { player2.activeMonsterDied(); }
    }
    
    public static boolean checkIsGameStillRunning() {
        if (player1.getMonsters().isEmpty()) {
            winnerPlayer = player2;
            return false;
        } else if (player2.getMonsters().isEmpty()) {
            winnerPlayer = player1;
            return false;
        } else {
            return true;
        }
    }

}
