package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;
import com.monstersaku.monsters.statuscondition.*;

public class StatusMove extends Move {
    // CHILD CLASS ATTRIBUTES
    private StatusConditionType status;
    private double effectHealthPoint;
    private double effectAttack;
    private double effectDefense;
    private double effectSpecialAttack;
    private double effectSpecialDefense;
    private double effectSpeed;
    
    // CONSTRUCTOR
    public StatusMove(String name,
                      String elementType,
                      String accuracy,
                      String priority,
                      String ammunition,
                      String target,
                      String status,
                      String effects) {
        // Set super class attributes
        super(name, elementType, accuracy, priority, ammunition, target);
        // Set status
        if (!status.equals("-")) {
            this.status = StatusConditionType.valueOf(status);
        } else {
            this.status = null;
        }
        // Set effect
        String[] effect = effects.split(",");
        this.effectHealthPoint = Double.valueOf(effect[0]);
        this.effectAttack = Double.valueOf(effect[1]);
        this.effectDefense = Double.valueOf(effect[2]);
        this.effectSpecialAttack = Double.valueOf(effect[3]);
        this.effectSpecialDefense = Double.valueOf(effect[4]);
        this.effectSpeed = Double.valueOf(effect[5]);
    }
    
    // PARENT CLASS METHODS
    public void doMove(Monster monster, Monster monsterTarget) {
        System.out.println("Using status move");

        // TODO:implement stats buff modification
        if (super.getTarget().equals(MoveTarget.OWN)) {
            // if (this.status != null) {
            //     monster.setStatus(status);
            // }
            if (this.status.equals(StatusConditionType.BURN)) {
                monster.burnStatusActive();
            } else if (this.status.equals(StatusConditionType.POISON)) {
                monster.poisonStatusActive();
            } else if (this.status.equals(StatusConditionType.SLEEP)) {
                monster.sleepStatusActive();
            } else if (this.status.equals(StatusConditionType.PARALYZE)) {
                monster.paralyzeStatusActive();
            }
            monster.getStats().setHealthPoint(monster.getStats().getHealthPoint() + this.effectHealthPoint);
            monster.getStats().setAttack(monster.getStats().getAttack() + this.effectAttack);
            monster.getStats().setDefense(monster.getStats().getDefense() + this.effectDefense);
            monster.getStats().setSpecialAttack(monster.getStats().getSpecialAttack() + this.effectSpecialAttack);
            monster.getStats().setSpecialDefense(monster.getStats().getSpecialDefense() + this.effectSpecialDefense);
            monster.getStats().setSpeed(monster.getStats().getSpeed() + this.effectSpeed);
        } else if (super.getTarget().equals(MoveTarget.ENEMY)) {
            // if (this.status != null) {
            //     monsterTarget.setStatus(status);
            // }
            monsterTarget.getStats().setHealthPoint(monsterTarget.getStats().getHealthPoint() + this.effectHealthPoint);
            monsterTarget.getStats().setAttack(monsterTarget.getStats().getAttack() + this.effectAttack);
            monsterTarget.getStats().setDefense(monsterTarget.getStats().getDefense() + this.effectDefense);
            monsterTarget.getStats().setSpecialAttack(monsterTarget.getStats().getSpecialAttack() + this.effectSpecialAttack);
            monsterTarget.getStats().setSpecialDefense(monsterTarget.getStats().getSpecialDefense() + this.effectSpecialDefense);
            monsterTarget.getStats().setSpeed(monsterTarget.getStats().getSpeed() + this.effectSpeed);
        }

        // Update ammunition
        super.setAmunition(super.getAmmunition() - 1);
    }
}
