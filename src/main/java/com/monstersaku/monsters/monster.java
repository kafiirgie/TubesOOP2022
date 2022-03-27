package com.monstersaku.monsters;

import com.monstersaku.monsters.move.*;
import com.monstersaku.monsters.statuscondition.*;
import com.monstersaku.config.*;

import java.util.ArrayList;
import java.util.List;

public class Monster implements Burn, Poison, Sleep, Paralyze {
    // ATTRIBUTES
    private final int id;
    private final String name;
    private final List<ElementType> elements = new ArrayList<ElementType>();
    private Stats stats;
    private List<Move> moves = new ArrayList<Move>();
    private boolean isAlive;
    private StatusConditionType status;

    // CONSTRUCTOR
    public Monster(String id, String name, String elements, String stats, String moves){
        // Set id
        this.id = Integer.valueOf(id);
        // Set name
        this.name = name;
        // Set elements
        String[] element = elements.split(",");
        for (int i = 0; i < element.length; i++) {
            this.elements.add(ElementType.valueOf(element[i]));
        }
        // Set stats
        String[] stat = stats.split(",");
        this.stats = new Stats(Double.valueOf(stat[0]), Double.valueOf(stat[1]), Double.valueOf(stat[2]), Double.valueOf(stat[3]), Double.valueOf(stat[4]), Double.valueOf(stat[5]));
        // Set moves
        String[] move = moves.split(",");
        for (int i = 0; i < move.length; i++) {
            this.moves.add(Configuration.getListOfMove().get(Integer.valueOf(move[i])-1));
            //this.moves.add(Integer.valueOf(move[i]));
        }
        // Set state
        this.isAlive = true;
        // Set status
        this.status = null;
    }

    // SETTER


    // GETTER
    public String getName(){
        return this.name;
    }

    public List<ElementType> getElements(){
        return this.elements;
    }

    public Stats getStats(){
        return this.stats;
    }

    public List<Move> getMoves(){
        return this.moves;
    }
    
    public boolean getIsAlive(){
        return this.isAlive;
    }

    public StatusConditionType getStatus(){
        return this.status;
    }

    // METHODS

    //addElement to monster
    //addMove to monster
    //showMonsterMoves
    //monster takedamage

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
