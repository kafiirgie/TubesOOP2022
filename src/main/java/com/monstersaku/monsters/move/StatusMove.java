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
    public void doMove(Monster monsterOwn, Monster monsterEnemy) {
        System.out.println("Using STATUS MOVE...");
        if (super.getTarget() == MoveTarget.OWN) {
            // Status Condition Change
            this.doStatusChange(monsterOwn);
            // Stats Effect
            this.doStatsEffect(monsterOwn);
            // Stats Buff
            if (this.statsBuff != null) {
                this.doStatsBuff(monsterOwn);
            }
        } else if (super.getTarget() == MoveTarget.ENEMY) {
            // Status Condition Change
            this.doStatusChange(monsterEnemy);
            // Stats Effect
            this.doStatsEffect(monsterEnemy);
            // Stats Buff
            if (this.statsBuff != null) {
                this.doStatsBuff(monsterEnemy);
            }
        }
        // Update ammunition
        super.setAmunition(super.getAmmunition() - 1);
    }

    // OTHER METHODS
    private void doStatusChange(Monster monster) {
        System.out.printf("\nCheck the status condition change for %s :\n", monster.getName());
        if (this.status == StatusConditionType.BURN) {
            monster.burnStatusActive();
        } else if (this.status == StatusConditionType.POISON) {
            monster.poisonStatusActive();
        } else if (this.status == StatusConditionType.SLEEP) {
            monster.sleepStatusActive();
        } else if (this.status == StatusConditionType.PARALYZE) {
            monster.paralyzeStatusActive();
        } else {
            System.out.println("There is no status condition change");
        }
    }
    
    private void doStatsEffect(Monster monster) {
        System.out.printf("\nCheck the stats effect for %s :\n", monster.getName());
        // Effect for health point stats
        Double effectedHP = monster.getStats().getHealthPoint() + this.effectHealthPoint;
        Double updatedHP;
        if (effectedHP > monster.getStats().getMaxHealthPoint()) {
            updatedHP = monster.getStats().getMaxHealthPoint();
        } else if (effectedHP <= monster.getStats().getMaxHealthPoint() && effectedHP >= 0) {
            updatedHP = effectedHP;
        } else { // effectedHP < 0
            updatedHP = 0d;
        }
        monster.getStats().setHealthPoint(updatedHP);
        System.out.println("current HP          : " + monster.getStats().getHealthPoint());
        // Effect for attack stats
        monster.getStats().setAttack(monster.getStats().getAttack() + this.effectAttack);
        System.out.println("current ATK         : " + monster.getStats().getAttack());
        // Effect for defense stats
        monster.getStats().setDefense(monster.getStats().getDefense() + this.effectDefense);
        System.out.println("current DEF         : " + monster.getStats().getDefense());
        // Effect for special attack stats
        monster.getStats().setSpecialAttack(monster.getStats().getSpecialAttack() + this.effectSpecialAttack);
        System.out.println("current SPECIAL ATK : " + monster.getStats().getSpecialAttack());
        // Effect for special defense stats
        monster.getStats().setSpecialDefense(monster.getStats().getSpecialDefense() + this.effectSpecialDefense);
        System.out.println("current SPECIAL DEF : " + monster.getStats().getSpecialAttack());
        // Effect for speed stats
        monster.getStats().setSpeed(monster.getStats().getSpeed() + this.effectSpeed);
        System.out.println("current SPEED       : " + monster.getStats().getSpeed());
    }

    private void doStatsBuff(Monster monster) {
        System.out.printf("\n%s got stats buff :\n", monster.getName());
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
        System.out.printf("%s got attack buff, attack stats now is %f\n", monster.getName(), buffedAttack);
        
        double buffedDefense = Math.floor(StatsBuff.getFactor(this.statsBuff.getDefenseBuff()) * monster.getStats().getDefense());
        monster.getStats().setDefense(buffedDefense);
        System.out.printf("%s got defense buff, defense stats now is %f\n", monster.getName(), buffedDefense);
        
        double buffedSpecialAttack = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpecialAttackBuff()) * monster.getStats().getSpecialAttack());
        monster.getStats().setSpecialAttack(buffedSpecialAttack);
        System.out.printf("%s got special attack buff, special attack stats now is %f\n", monster.getName(), buffedSpecialAttack);
        
        double buffedSpecialDefense = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpecialDefenseBuff()) * monster.getStats().getSpecialDefense());
        monster.getStats().setSpecialDefense(buffedSpecialDefense);
        System.out.printf("%s got special defense buff, special defense stats now is %f\n", monster.getName(), buffedSpecialDefense);
        
        double buffedSpeed = Math.floor(StatsBuff.getFactor(this.statsBuff.getSpeedBuff()) * monster.getStats().getSpeed());
        monster.getStats().setSpeed(buffedSpeed);
        System.out.printf("%s got speed buff, speed stats now is %f\n", monster.getName(), buffedSpeed);
    }

}
