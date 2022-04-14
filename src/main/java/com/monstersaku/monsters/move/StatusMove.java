package com.monstersaku.monsters.move;

import java.util.Random;

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
    private StatsBuff statsBuff;
    
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
        if (status.equals("BUFF")) {
            this.status = null;
            this.statsBuff = new StatsBuff();
        } else if (status.equals("-")) {
            this.status = null;
            this.statsBuff = null;
        } else {
            this.status = StatusConditionType.valueOf(status);
            this.statsBuff = null;
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
        if (super.getTarget() == MoveTarget.OWN) {
            if (this.statsBuff != null) {
                this.doStatsBuff(monster);
            }
            if (this.status == StatusConditionType.BURN) {
                monster.burnStatusActive();
            } else if (this.status == StatusConditionType.POISON) {
                monster.poisonStatusActive();
            } else if (this.status == StatusConditionType.SLEEP) {
                monster.sleepStatusActive();
            } else if (this.status == StatusConditionType.PARALYZE) {
                monster.paralyzeStatusActive();
            }
            monster.getStats().setHealthPoint(monster.getStats().getHealthPoint() + this.effectHealthPoint);
            monster.getStats().setAttack(monster.getStats().getAttack() + this.effectAttack);
            monster.getStats().setDefense(monster.getStats().getDefense() + this.effectDefense);
            monster.getStats().setSpecialAttack(monster.getStats().getSpecialAttack() + this.effectSpecialAttack);
            monster.getStats().setSpecialDefense(monster.getStats().getSpecialDefense() + this.effectSpecialDefense);
            monster.getStats().setSpeed(monster.getStats().getSpeed() + this.effectSpeed);
        } else if (super.getTarget() == MoveTarget.ENEMY) {
            if (this.statsBuff != null) {
                this.doStatsBuff(monsterTarget);
            }
            if (this.status == StatusConditionType.BURN) {
                monsterTarget.burnStatusActive();
            } else if (this.status == StatusConditionType.POISON) {
                monsterTarget.poisonStatusActive();
            } else if (this.status == StatusConditionType.SLEEP) {
                monsterTarget.sleepStatusActive();
            } else if (this.status == StatusConditionType.PARALYZE) {
                monsterTarget.paralyzeStatusActive();
            }
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

    public void doStatsBuff(Monster monster) {
        // Random Stats Buff Value
        Random r = new Random();
        this.statsBuff.setAttackBuff(r.nextInt(9) - 4);
        this.statsBuff.setDefenseBuff(r.nextInt(9) - 4);
        this.statsBuff.setSpecialAttackBuff(r.nextInt(9) - 4);
        this.statsBuff.setSpecialDefenseBuff(r.nextInt(9) - 4);
        this.statsBuff.setSpeedBuff(r.nextInt(9) - 4);
        
        // Set New Stats for the Monster
        double buffedAttack = Math.floor(StatsBuff.getFactor(this.statsBuff.getAttackBuff()) * monster.getStats().getAttack());
        monster.getStats().setAttack(buffedAttack);
        System.out.printf("\n%s got attack buff, attack stats now is %f\n", monster.getName(), buffedAttack);
        
        double buffedDefense = Math.floor(StatsBuff.getFactor(this.statsBuff.getDefenseBuff()) * monster.getStats().getDefense());
        monster.getStats().setDefense(buffedDefense);
        System.out.printf("\n%s got defense buff, defense stats now is %f\n", monster.getName(), buffedDefense);
        
        double buffedSpecialAttack = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpecialAttackBuff()) * monster.getStats().getSpecialAttack());
        monster.getStats().setSpecialAttack(buffedSpecialAttack);
        System.out.printf("\n%s got special attack buff, special attack stats now is %f\n", monster.getName(), buffedSpecialAttack);
        
        double buffedSpecialDefense = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpecialDefenseBuff()) * monster.getStats().getSpecialDefense());
        monster.getStats().setSpecialDefense(buffedSpecialDefense);
        System.out.printf("\n%s got special defense buff, special defense stats now is %f\n", monster.getName(), buffedSpecialDefense);
        
        double buffedSpeed = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpeedBuff()) * monster.getStats().getSpeed());
        monster.getStats().setSpeed(buffedSpeed);
        System.out.printf("\n%s got speed buff, speed stats now is %f\n", monster.getName(), buffedSpeed);
    }

}
