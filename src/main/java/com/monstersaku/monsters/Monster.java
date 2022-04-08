package com.monstersaku.monsters;

import com.monstersaku.monsters.move.*;
import com.monstersaku.monsters.statuscondition.*;
import com.monstersaku.util.*;

import java.util.ArrayList;
import java.util.List;

public class Monster implements Burn, Poison, Sleep, Paralyze {
    // ATTRIBUTES
    private final String name;
    private final List<ElementType> elements = new ArrayList<ElementType>();
    private Stats stats;
    private final List<Move> moves = new ArrayList<Move>();
    private boolean isAlive = true;
    private StatusConditionType status = null;

    // CONSTRUCTOR
    public Monster(String name, String elements, String stats, String moves){
        // SET name
        this.name = name;
        // SET elements
        String[] element = elements.split(",");
        for (int i = 0; i < element.length; i++) {
            this.elements.add(ElementType.valueOf(element[i]));
        }
        // SET stats
        String[] stat = stats.split(",");
        this.stats = new Stats(Double.valueOf(stat[0]), Double.valueOf(stat[1]), Double.valueOf(stat[2]), Double.valueOf(stat[3]), Double.valueOf(stat[4]), Double.valueOf(stat[5]));
        // SET moves
        String[] move = moves.split(",");
        for (int i = 0; i < move.length; i++) {
            // read move first
            Config.addNewMove(Integer.valueOf(move[i]));
            this.moves.add(Config.getMapOfMove().get(Integer.valueOf(move[i])));
        }
        DefaultMove defaultMove = new DefaultMove();
        this.moves.add(defaultMove);
    }

    // GETTER
    public String getName() { return this.name; }
    public List<ElementType> getElements() { return this.elements; }
    public Stats getStats() { return this.stats; }
    public List<Move> getMoves() { return this.moves; }
    public boolean getIsAlive() { return this.isAlive; }
    public StatusConditionType getStatus() { return this.status; }

    // SETTER
    public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }
    public void setStatus(StatusConditionType status) { this.status = status; }

    // METHODS
    public void showMonsterInfo() {
        System.out.println("Name     : ");
        System.out.println(this.name);
        System.out.println("Elements : ");
        for (int i = 0; i < this.elements.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, this.elements.get(i));
        }
        System.out.println("Stats    : "); this.stats.showStats();
        System.out.println("Moves    : "); this.showMonsterMove();

    }
    public void showMonsterMove() {
        for (int i = 0; i < this.moves.size(); i++) {
            System.out.printf("[%d] %s\n", i+1, this.moves.get(i).getMoveName());
        }
    }
    
    public void takeDamage(double damage) {
        // TODO:implement take damage
    }

    // Methods for Status Condition -> pake try catch(?)
    public void burnStatusActive() {
        // TODO:implement burn status
    }
    public void poisonStatusActive() {
        // TODO:implement poison status
    }
    public void sleepStatusActive() {
        // TODO:implement sleep status
    }
    public void paralyzeStatusActive() {
        // TODO:implement paralyze status
    }
}