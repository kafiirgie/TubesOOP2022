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

    public static void playGame() {
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
                    
                    // TURN STARTED
                    boolean isTurnRunning = true;
                    while (isTurnRunning) {    
                        // Setup display
                        Config.clearConsole();
                        System.out.printf("##### ROUND %d #####\n", countRound);
                        System.out.println("\n===== BEGINNING OF TURN =====");
                        System.out.println("Now is " + playerTurn.getName() + "'s turn\n");
                        // Selecting action
                        showAction();
                        int moveAction = selectAction(playerTurn, playerOpponent);
                        movePlayers[id-1] = moveAction;
                        // Handle SLEEP status (monster can't do move)
                        if (moveAction > 0 && playerTurn.getActiveMonster().getStatus() == StatusConditionType.SLEEP) {
                            playerTurn.getActiveMonster().hasSleepStatus(playerTurn);
                        }
                        // Handle PARALYZE status (is monster can do move or not)
                        if (moveAction > 0 && playerTurn.getActiveMonster().getStatus() == StatusConditionType.PARALYZE) {
                            if (playerTurn.getActiveMonster().isParalyzeStatusCanMove(playerTurn)) {
                                System.out.println(playerTurn.getActiveMonster().getName() + " still can do move.");
                            } else {
                                System.out.println(playerTurn.getActiveMonster().getName() + " can't do move.");
                                movePlayers[id-1] = 0;
                            }
                        }
                        // !!!!!!!DELETED SOON!!!!!!!
                        // // Check move amunition
                        // if (moveAction > 0 && playerTurn.getActiveMonster().getMoves().get(moveAction-1).getAmmunition() < 1) {
                        //     System.out.println("Oopss, " + playerTurn.getActiveMonster().getName() + " can't do this move. This move is out of amunition");
                        //     playerTurn.getActiveMonster().getMoves().remove(moveAction-1);
                        //     System.out.println("You can select another action");
                        //     playerTurn.getActiveMonster().getMoves().remove(moveAction-1);
                        //     moveAction = -1;
                        // }
                        Config.goToNextPage();
                        // End of turn (player choose move or switch)
                        if (moveAction >= 0) { isTurnRunning = false; }
                    }
                    System.out.println("\n===== END OF TURN =====");
                    Config.loading();
                    Config.clearConsole();

                    // Handle SLEEP status (Reduce sleep counter)
                    for (Monster monster : playerTurn.getMonsters()) {
                        if (monster.getStatus() == StatusConditionType.SLEEP) {
                            if (monster.getSleepCounter() > 0) {
                                monster.setSleepCounter(monster.getSleepCounter() - 1); // reduce sleep counter
                            } else {
                                monster.setStatus(null);                         // release SLEEP status
                            }
                        }
                    }
                    
                    id++; // Go to next player
                    if (id > 2) { isRoundRunning = false; } // Round is end, go to next round
                }
                countRound++;
                Config.clearConsole();
                System.out.printf("\n##### ROUND %d IS END #####\n", countRound-1);

                // HANDLE PLAYERS MOVE (based on priority and speed)
                System.out.printf("\nexecuting players move"); Config.loading();
                if (movePlayers[0] > 0 && movePlayers[1] > 0) {          // both players do move
                    System.out.println("\nBoth players choose to move monster");
                    boolean player1MoveFirst;
                    // Check move priority
                    int priorityPlayer1 = player1.getActiveMonster().getMoves().get(movePlayers[0]-1).getPriority();
                    int priorityPlayer2 = player2.getActiveMonster().getMoves().get(movePlayers[1]-1).getPriority();
                    if (priorityPlayer1 > priorityPlayer2) {
                        System.out.printf("Player %s move priority greater than player %s move priority\n", player1.getName(), player2.getName());
                        player1MoveFirst = true;
                    } else if (priorityPlayer1 < priorityPlayer2) {
                        System.out.printf("Player %s move priority greater than player %s move priority\n", player2.getName(), player1.getName());
                        player1MoveFirst = false;
                    } else {
                        System.out.printf("Player %s move priority equals to player %s move priority\n", player1.getName(), player2.getName());
                        // Check monster speed
                        double speedPlayer1 = player1.getActiveMonster().getStats().getSpeed();
                        double speedPlayer2 = player2.getActiveMonster().getStats().getSpeed();
                        if (speedPlayer1 > speedPlayer2) {
                            System.out.printf("-> Player %s monster speed greater than player %s monster speed\n", player1.getName(), player2.getName());
                            player1MoveFirst = true;
                        } else if (speedPlayer1 < speedPlayer2) {
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
                        playerActionMove(movePlayers, 0, player1, player2);
                        handlePlayersActiveMonsterDied();
                        isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        if (player2.getActiveMonster().getIsAlive()) {
                            playerActionMove(movePlayers, 1, player2, player1);
                            handlePlayersActiveMonsterDied();
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        } else {
                            System.out.printf("\nPlayer %s active monster died, can't do move\n", player2.getName());
                            handlePlayersActiveMonsterDied();
                            isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                        }
                    } else {
                        playerActionMove(movePlayers, 1, player2, player1);
                        handlePlayersActiveMonsterDied();
                        isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; };
                        if (player1.getActiveMonster().getIsAlive()) {
                            playerActionMove(movePlayers, 0, player1, player2);
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
                    playerActionMove(movePlayers, 0, player1, player2);
                    handlePlayersActiveMonsterDied();
                    isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                } else if (movePlayers[0] <= 0 && movePlayers[1] > 0) {  // only player 2 do move
                    System.out.printf("\nOnly player %s choose to move monster\n", player2.getName());
                    playerActionMove(movePlayers, 1, player2, player1);
                    handlePlayersActiveMonsterDied();
                    isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                } else if (movePlayers[0] <= 0 && movePlayers[1] <= 0) { // both players not do move
                    System.out.println("\nNo players choose move monster, get ready for next round!!");
                }
                
                // AFTER DAMAGE CALCULATION
                System.out.println("\n[After Damage Calculation]");
                // Player 1
                afterDamageCalculation(player1);
                handlePlayersActiveMonsterDied();
                isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                // Player 2
                afterDamageCalculation(player2);
                handlePlayersActiveMonsterDied();
                isGameRunning = checkIsGameStillRunning(); if (!isGameRunning) { break; }
                
                System.out.printf("\ngoing to next round"); Config.loading();
                Config.goToNextPage();
            }
            Config.goToNextPage();
            // END GAME - SHOW WINNER
            endGame();
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
        player1 = new Player(1, playerName1);
        // Check player 2 name input
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                System.out.printf("Player 2 name : ");
                String playerName2 = sc.next();
                if (!playerName2.equals(playerName1)) {
                    isInputValid = true;
                    player2 = new Player(2, playerName2);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Playar 2 name must be different from Playar 1 name");
            } catch (Exception e) {
                System.out.println("[Input Exception] : " + e.getMessage());
            }
        }
    }

    public static void showAction() {
        System.out.println("----- ACTION -----");
        System.out.println("[1] Move Monster");
        System.out.println("[2] Switch Monster");
        System.out.println("[3] Monster Info");
        System.out.println("[4] Game Info");
        System.out.println("[5] Help");
        System.out.println("[0] Exit");
    }

    public static int selectAction(Player player1, Player player2) {
        boolean isInputValid = false;
        int move = -1;
        while (!isInputValid) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.printf("Select action : ");
                int input = sc.nextInt();
                if (input == 1) {
                    isInputValid = true;
                    move = player1.selectMonsterMove(); // move > 0 indicates player choose move
                } else if (input == 2) {
                    isInputValid = true;
                    player1.switchActiveMonster();      // move = 0 indicates player choose swicth
                    move = 0;
                } else if (input == 3) {
                    isInputValid = true;
                    player1.showMonsterInGameInfo();
                } else if (input == 4) {
                    isInputValid = true;
                    System.out.println("\n[ GAME INFO ]");
                    player1.showPlayerInfo();
                    System.out.println();
                    player2.showPlayerInfo();
                } else if (input == 5) {
                    isInputValid = true;
                    Menu.help();
                } else if (input == 0) {
                    isInputValid = true;
                    Menu.exit();
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Invalid input value, please input in option range");
            } catch (Exception e) {
                System.out.println("[Input Exception] : Wrong input type, please input in integer type");
            }
        }
        return move;
    }

    public static void playerActionMove(int[] movePlayers, int id, Player player, Player playerEnemy) {
        Monster monster = player.getActiveMonster();
        Monster monsterTarget = playerEnemy.getActiveMonster();
        System.out.printf("\n[Player %s] : %s do %s move\n", player.getName(), monster.getName(), monster.getMoves().get(movePlayers[id]-1).getMoveName());
        // Check move accuracy
        Random r = new Random();
        int value = r.nextInt(100);
        if (value < monster.getMoves().get(movePlayers[id]-1).getAccuracy()) {
            System.out.printf("[Player %s] : %s successfull did %s move\n", player.getName(), monster.getName(), monster.getMoves().get(movePlayers[id]-1).getMoveName());
            monster.getMoves().get(movePlayers[id]-1).doMove(monster, monsterTarget);
            // Check move amunition
            if (monster.getMoves().get(movePlayers[id]-1).getAmmunition() == 0) {
                monster.getMoves().remove(movePlayers[id]-1);
            }
        } else {
            System.out.printf("[Player %s] : Oops, %s failed did %s move\n", player.getName(), monster.getName(), monster.getMoves().get(movePlayers[id]-1).getMoveName());
        }
    }

    public static void afterDamageCalculation(Player player) {
        if (player.getActiveMonster().getStatus() == StatusConditionType.BURN) {
            double damage = Math.floor(player.getActiveMonster().getStats().getMaxHealthPoint() / 8);
            player.getActiveMonster().takeDamage(damage);
        } else if (player.getActiveMonster().getStatus() == StatusConditionType.POISON) {
            double damage = Math.floor(player.getActiveMonster().getStats().getMaxHealthPoint() / 16);
            player.getActiveMonster().takeDamage(damage);
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

    public static void endGame() {
        Config.clearConsole();
        System.out.println("\nTHE GAME IS END");
        System.out.printf("\nCONGRATULATION to %s for became the winner!!\n", winnerPlayer.getName());
        System.out.printf("\nprocessing to exit from the game"); Config.loading();
        Exit.exitCredits();
    }
}
