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
        System.out.printf("%s's active monster died : %s\n", this.name, this.activeMonster.getName());
        for (Monster monster : this.monsters) {
            if (monster.getName().equals(this.activeMonster.getName())) {
                this.monsters.remove(monster);
            }
        }
        // change active monster
        System.out.println("Select another active monster");
        this.selectActiveMonster();
    }
    public void selectActiveMonster() {     // if active monster died
        showNonActiveMonster();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select monster : ");
        int input = sc.nextInt();
        this.activeMonster = this.nonActiveMonsters.get(input-1);
        this.nonActiveMonsters.remove(input-1);
    }
    
    // [1] Move Monster
    public int selectMonsterMove() {
        this.activeMonster.showMonsterMove();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select move : ");
        int input = sc.nextInt();
        return input;
    }
    
    // [2] Switch Monster
    public void switchActiveMonster() {
        showNonActiveMonster();
        if (this.nonActiveMonsters.size() > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.printf("Select monster : ");
            int input = sc.nextInt();
            Monster temp = activeMonster;
            this.activeMonster = this.nonActiveMonsters.get(input-1);
            this.nonActiveMonsters.remove(input-1);
            this.nonActiveMonsters.add(temp);
        }
    }
    
    // [3] Show Monster Info
    public void showMonsterInGameInfo() {
        this.showAliveMonsters();
        Scanner sc = new Scanner(System.in);
        System.out.printf("Select monster to inspect : ");
        int input = sc.nextInt();
        monsters.get(input-1).showMonsterInfo();
    }
    
    // [4] Show Game Info
    public void showPlayerInfo() {
        System.out.printf("PLAYER %d INFO\n", this.id);
        System.out.printf("Player name : %s\n", this.name);
        this.showAliveMonsters();
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
