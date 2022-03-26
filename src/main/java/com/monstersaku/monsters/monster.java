package com.monstersaku.monsters;

import com.monstersaku.monsters.move.*;
import com.monstersaku.monsters.statuscondition.*;

import java.util.ArrayList;
import java.util.List;

public class Monster implements Burn, Poison, Sleep, Paralyze {
    // ATTRIBUTES
    private final int id;
    private final String name;
    private List<ElementType> elements;
    private Stats stats;
    private List<Move> moves;
    private MonsterState state = MonsterState.ALIVE;

    // CONSTRUCTOR
    public Monster(int id, String name, Stats stats){
        this.id = id;
        this.name = name;
        this.elements = new ArrayList<ElementType>();
        this.stats = stats;
        this.moves = new ArrayList<Move>();
    }

    // SETTER


    // GETTER
    public String getName(){
        return this.name;
    }

    public List<ElementType> getElements(){
        return this.elements;
    }

    public MonsterState getStateMonster(){
        return this.state;
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    //addElement to monster
    //addMove to monster
    //showMonsterMoves
    //monster takedamage

    // METHODS
    // Methods for Status Condition -> pake try catch(?)
    public void burnStatusActive() {
        // soon
    }
    public void poisonStatusActive() {
        // soon
    }
    public void sleepStatusActive() {
        // soon
    }
    public void paralyzeStatusActive() {
        // soon
    }
}
