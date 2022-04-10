package com.monstersaku.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import com.monstersaku.monsters.*;
import com.monstersaku.util.Config;

public class Player {
    // ATTRIBUTES
    private final int id;
    private String name;
    private List<Monster> monsters = new ArrayList<Monster>();
    private Monster activeMonster;
    private List<Monster> nonActiveMonsters = new ArrayList<Monster>();
    private static Integer[] arrOfIdMonster;

    // CONSTRUCTOR
    public Player(int id, String name) {
        // SET id and name
        this.id = id;
        this.name = name;
        // SET random monster
        for (int i = (6*(id-1)); i < (6*id); i++) {
            int idMonster = arrOfIdMonster[i]+1;
            System.out.println(idMonster);
            System.out.println(Config.getMapOfMonster().get(idMonster).getName());
            this.monsters.add(Config.getMapOfMonster().get(idMonster));
        }
        // SET activeMonster
        this.activeMonster = this.monsters.get(0);
        // SET nonActiveMonster
        this.nonActiveMonsters.addAll(monsters);
        this.nonActiveMonsters.remove(0);
    }
       
    // GETTER
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Monster> getMonsters() { return monsters; }
    public Monster getActiveMonster() { return activeMonster; }
    public List<Monster> getNonActiveMonsters() { return nonActiveMonsters; }
    
    // SETTER
    public void setName(String name) { this.name = name; }
    public void setMonsters(List<Monster> monsters) { this.monsters = monsters; }
    public void setActiveMonster(Monster activeMonster) { this.activeMonster = activeMonster; }
    public void setNonActiveMonsters(List<Monster> nonActiveMonsters) { this.nonActiveMonsters = nonActiveMonsters; }

    // METHODS
    public static void setRandomIdMonster() { // there are minimum 12 monsters in configuration file
        Set<Integer> setOfIdMonster = getUniqueRandomInt(12, Config.getMapOfMonster().size());
        arrOfIdMonster = setOfIdMonster.toArray(new Integer[setOfIdMonster.size()]);
    }
    public static Set<Integer> getUniqueRandomInt(int size, int range) {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>(size);
        while(set.size() < size) {
            while (set.add(random.nextInt(range)) != true);
        }
        assert set.size() == size;
        return set;
    }

    public void activeMonsterDied() {
        // Remove active monster from monsters
        System.out.printf("\n%s's active monster died : %s\n", this.name, this.activeMonster.getName());
        int removedId = -1;
        for (int i = 0; i < monsters.size(); i++) {
            if (monsters.get(i).getName() == this.activeMonster.getName()) {
                removedId = i;
            }
        }
        this.monsters.remove(removedId);
        // Change active monster
        System.out.println("Select another active monster");
        this.selectActiveMonster();
    }
    public void selectActiveMonster() {     // if active monster died
        System.out.printf("\n[Player %s]\n", this.name);
        showNonActiveMonster();
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                if (this.nonActiveMonsters.size() > 0) {
                    Scanner sc = new Scanner(System.in);
                    System.out.printf("Select monster : ");
                    int input = sc.nextInt();
                    if (input >= 1 && input <= this.nonActiveMonsters.size()) {
                        isInputValid = true;
                        this.activeMonster = this.nonActiveMonsters.get(input-1);
                        this.nonActiveMonsters.remove(input-1);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    isInputValid = true;
                    System.out.println("\nAll monsters already died");
                    System.out.println("The game is end");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Invalid input value, please input in option range");
            } catch (Exception e) {
                System.out.println("[Input Exception] : Wrong input type, please input in integer type");
            }
        }
    }
    
    // [1] Move Monster
    public int selectMonsterMove() {
        System.out.println("\n[ SELECT MOVE ]");
        this.activeMonster.showMonsterMove();
        int input = 0;
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.printf("Select move : ");
                input = sc.nextInt();
                if (input >= 1 && input <= this.activeMonster.getMoves().size()) {
                    isInputValid = true;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Invalid input value, please input in option range");
            } catch (Exception e) {
                System.out.println("[Input Exception] : Wrong input type, please input in integer type");
            }
        }
        return input;
    }
    
    // [2] Switch Monster
    public void switchActiveMonster() {
        System.out.println("\n[ SWITCH MONSTER ]");
        showNonActiveMonster();
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                if (this.nonActiveMonsters.size() > 0) {
                    Scanner sc = new Scanner(System.in);
                    System.out.printf("Select monster : ");
                    int input = sc.nextInt();
                    if (input >= 1 && input <= this.nonActiveMonsters.size()) {
                        isInputValid = true;
                        Monster temp = activeMonster;
                        this.activeMonster = this.nonActiveMonsters.get(input-1);
                        this.nonActiveMonsters.remove(input-1);
                        this.nonActiveMonsters.add(temp);
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    isInputValid = true;
                    System.out.println("Can't do switch monster");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("[Input Exception] : Invalid input value, please input in option range");
            } catch (Exception e) {
                System.out.println("[Input Exception] : Wrong input type, please input in integer type");
            }
        }
    }
    
    // [3] Show Monster Info
    public void showMonsterInGameInfo() {
        System.out.println("\n[ MONSTER INFO ]");
        this.showAliveMonsters();
        boolean isInputValid = false;
        while (!isInputValid) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.printf("Select monster to inspect : ");
                int input = sc.nextInt();
                if (input >= 1 && input <= this.monsters.size()) {
                    isInputValid = true;
                    this.monsters.get(input-1).showMonsterInfo();
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
    
    // [4] Show Game Info
    public void showPlayerInfo() {
        System.out.printf("PLAYER %d INFO\n", this.id);
        System.out.printf("Player name : %s\n", this.name);
        this.showActiveMonster();
        this.showNonActiveMonster();
    }
    
    public void showAliveMonsters() {
        System.out.println("Alive monsters:");
        for (int i = 0; i < this.monsters.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, this.monsters.get(i).getName());
        }
    }

    public void showActiveMonster() {
        System.out.println("Active monster:");
        if (this.activeMonster != null) {
            System.out.println(this.activeMonster.getName());
        } else {
            System.out.println("There is no active monster right now");
        }
    }

    public void showNonActiveMonster() {
        System.out.println("Inactive monsters:");
        if (this.nonActiveMonsters.size() > 0) {
            for (int i = 0; i < this.nonActiveMonsters.size(); i++) {
                System.out.printf("[%d] %s\n", i+1, this.nonActiveMonsters.get(i).getName());
            }   
        } else {
            System.out.println("There is no non-active monsters right now");
        }
    }

}
