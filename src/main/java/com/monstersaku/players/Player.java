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
        System.out.println(setOfIdMonster);
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
        // delete active monster from monsters
        for (Monster monster : monsters) {
            if (monster.getName().equals(activeMonster.getName())) {
                monsters.remove(monster);
            }
        }
    }
    public void selectActiveMonster() {     // if active monster died
        showMonsterAlive();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select monster : ");
        int input = sc.nextInt();
        this.activeMonster = this.nonActiveMonsters.get(input-1);
        this.nonActiveMonsters.remove(input-1);
    }
    public void switchActiveMonster() {
        showMonsterAlive();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select monster : ");
        int input = sc.nextInt();
        Monster temp = activeMonster;
        this.activeMonster = this.nonActiveMonsters.get(input-1);
        this.nonActiveMonsters.remove(input-1);
        this.nonActiveMonsters.add(temp);
    }
    public void showMonsterAlive() {
        System.out.printf("All monsters: \n");
        for (int i = 0; i < this.monsters.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, this.monsters.get(i).getName());
        }
        System.out.printf("Your inactive monsters: \n");
        for (int i = 0; i < this.nonActiveMonsters.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, this.nonActiveMonsters.get(i).getName());
        }
    }

    public void selectMonsterMove() {
        this.activeMonster.showMonsterMove();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select move : ");
        int input = sc.nextInt();
        this.activeMonster.getMoves().get(input-1).doMove();
    }
}
