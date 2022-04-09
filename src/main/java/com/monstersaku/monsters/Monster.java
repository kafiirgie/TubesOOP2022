package com.monstersaku.monsters;

import com.monstersaku.monsters.move.*;
import com.monstersaku.monsters.statuscondition.*;
import com.monstersaku.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster implements Burn, Poison, Sleep, Paralyze {
    // ATTRIBUTES
    private final String name;
    private final List<ElementType> elements = new ArrayList<ElementType>();
    private Stats stats;
    private final List<Move> moves = new ArrayList<Move>();
    private boolean isAlive = true;
    private StatusConditionType status = null;
    private int sleepCounter = 0;

    // CONSTRUCTOR
    public Monster(String name, String elements, String stats, String moves) throws CloneNotSupportedException {
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
            // clone move object from map of move
            Move newMove = (Move)Config.getMapOfMove().get(Integer.valueOf(move[i])).clone();
            this.moves.add(newMove);
            //this.moves.add(Config.getMapOfMove().get(Integer.valueOf(move[i])));
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
    public int getSleepCounter() { return this.sleepCounter; }

    // SETTER
    public void setIsAlive(boolean isAlive) { this.isAlive = isAlive; }
    public void setStatus(StatusConditionType status) { this.status = status; }
    public void setSleepCounter(int sleepCounter) { this.sleepCounter = sleepCounter; }

    // METHODS
    public void showMonsterInfo() {
        System.out.printf("Name     :  ");
        System.out.println(this.name);
        System.out.println("Elements : ");
        for (int i = 0; i < this.elements.size(); i++) {
            System.out.printf("> [%d] %s\n", i+1, this.elements.get(i));
        }
        System.out.println("Stats    : "); this.stats.showStats();
        System.out.println("Moves    : "); this.showMonsterMove();

    }
    public void showMonsterMove() {
        for (int i = 0; i < this.moves.size(); i++) {
            System.out.printf("> [%d] %s\n", i+1, this.moves.get(i).getMoveName());
        }
    }
    
    public void takeDamage(double damage) {
        System.out.printf("\n%s got damage %f points\n", this.name, damage);
        double damagedHealthPoint = this.stats.getHealthPoint() - damage;
        if (damagedHealthPoint > 0) {
            this.stats.setHealthPoint(damagedHealthPoint);
            System.out.printf("%s current health point is %f\n", this.name, damagedHealthPoint);
        } else {
            this.isAlive = false;
            System.out.printf("%s died\n", this.name);
        }
    }

    // Methods for Status Condition
    public void burnStatusActive() {
        this.status = StatusConditionType.BURN;
        System.out.println(this.name + "got BURN status condition");
        // reducing HP implemented in Game.java (after damage calculation)
        // reducing damage implemented in Move (damage calculation)
    }
    public void poisonStatusActive() {
        this.status = StatusConditionType.POISON;
        System.out.println(this.name + "got POISON status condition");
        // reducing HP implemented in Game.java (after damage calculation)
    }
    public void sleepStatusActive() {
        this.status = StatusConditionType.SLEEP;
        System.out.println(this.name + "got SLEEP status condition");
        // Random sleep counter
        Random r = new Random();
        this.sleepCounter = r.nextInt(7) + 1;
        // Implementation for 'monster cant move' in Game.java
    }
    public void paralyzeStatusActive() {
        this.status = StatusConditionType.PARALYZE;
        System.out.println(this.name + "got PARALYZE status condition");
        // Reduce monster speed
        double recucedSpeed = Math.floor(this.stats.getSpeed() / 2);
        this.stats.setSpeed(recucedSpeed);
        // Implementation for 'monster cant move' in Game.java
    }
}
