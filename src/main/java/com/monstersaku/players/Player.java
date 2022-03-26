package com.monstersaku.players;

import java.util.ArrayList;
import java.util.List;

import com.monstersaku.monsters.*;

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
        this.id = id;
        this.name = name;
        // random monster
    }
    // METHODS
}
