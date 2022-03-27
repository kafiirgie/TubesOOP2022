package com.monstersaku.players;

import java.util.ArrayList;
import java.util.List;
// import java.util.Random;


import com.monstersaku.monsters.*;
import com.monstersaku.config.*;

public class Player {
    // ATTRIBUTES
    private final int id;
    private String name;
    private List<Monster> monsters;
    private Monster activeMonster;
    private List<Monster> nonActiveMonsters;
    //private boolean isCurrentTurn;

    // CONSTRUCTOR
    public Player(int id, String name) {
        // Set id and name
        this.id = id;
        this.name = name;
        // Set random monster
        for (int i = 0; i < 5; i++) {
            int min = 0;
            int max = Configuration.getListOfMonster().size();
            int randomId = (int)Math.floor(Math.random()*(max-min+1)+min);
            // Random random = new Random();
            // int randomId = random.nextInt(Configuration.getListOfMonster().size());
            this.monsters.add(Configuration.getListOfMonster().get(randomId));
        }
    }
    // METHODS
}
