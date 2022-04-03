package com.monstersaku.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    // CONSTRUCTOR
    public Player(int id, String name) {
        // SET id and name
        this.id = id;
        this.name = name;

        // SET random monster
        Set<Integer> setOfIdMonster = getUniqueRandomInt(6, Config.getMapOfMonster().size());
        Integer[] arrOfIdMonster = setOfIdMonster.toArray(new Integer[setOfIdMonster.size()]);
        for (Integer idMonster : arrOfIdMonster) {
            this.monsters.add(Config.getMapOfMonster().get(Integer.valueOf(idMonster)));
        }
        
        // SET activeMonster
        this.activeMonster = this.monsters.get(0);

        // SET nonActiveMonster
        this.nonActiveMonsters = this.monsters;
        this.nonActiveMonsters.remove(0);
    }

    // METHODS
    public Set<Integer> getUniqueRandomInt(int size, int range) {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>(size);
        while(set.size() < size) {
            while (set.add(random.nextInt(range)) != true);
        }
        assert set.size() == size;
        return set;
    }

    public void selectMonsterMove() {}
    public void switchActiveMonster() {}
}
