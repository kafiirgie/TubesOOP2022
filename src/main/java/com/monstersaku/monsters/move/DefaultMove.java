package com.monstersaku.monsters.move;

import java.lang.Math;
import java.util.Random;

import com.monstersaku.monsters.ElementEffKey;
import com.monstersaku.monsters.Monster;
import com.monstersaku.monsters.statuscondition.StatusConditionType;
import com.monstersaku.util.Config;

public class DefaultMove extends Move {
    // CHILD CLASS ATTRIBUTE
    private final double basePower;
    
    // CONSTRUCTOR
    public DefaultMove() {
        super("Default", "NORMAL", "100", "0", "999999", "ENEMY");
        this.basePower = 50;
    }

    // GETTER
    public double getBasePower() { return this.basePower; }
    
    // PARENT CLASS METHOD
    public void doMove(Monster monsterOwn, Monster monsterEnemy) {
        System.out.println("Using DEFAULT MOVE...");
        // Calculate damage
        System.out.println("calculating damage...");
        double damage = calculateDamage(monsterOwn, monsterEnemy);
        monsterEnemy.takeDamage(damage);
        // Reduce health point
        System.out.println("reducing health point...");
        double reducingHealth = Math.floor(monsterOwn.getStats().getMaxHealthPoint() * 0.25);
        System.out.println("health point reduced " + reducingHealth + " points");
        double reducedHealth = monsterOwn.getStats().getHealthPoint() - reducingHealth;
        if (reducedHealth > 0) {
            monsterOwn.getStats().setHealthPoint(reducedHealth);
            System.out.println("health point now is " + reducedHealth + " points");
        } else {
            monsterOwn.getStats().setHealthPoint(0);
            monsterOwn.setIsAlive(false);
            System.out.println("monster died X_X before do default move");
        }
        // Update ammunition
        super.setAmunition(super.getAmmunition() - 1);
    }

    // OTHER METHOD
    private double calculateDamage(Monster monsterOwn, Monster monsterEnemy) {
        // Random
        double rangeMin = 0.85;
        double rangeMax = 1;
        Random r = new Random();
        double random = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        // Element Effectivity
        double effectivity = 1;
        for (int i = 0; i < monsterOwn.getElements().size(); i++) {
            ElementEffKey key = new ElementEffKey(super.getElementType(), monsterEnemy.getElements().get(i));
            effectivity *= Config.getMapOfElementEff().get(key);
        }
        // Burn Factor
        double burn;
        if (monsterOwn.getStatus() == StatusConditionType.BURN) {
            burn = 0.5;
        } else {
            burn = 1;
        }
        // Damage
        double damage = (this.basePower * (monsterOwn.getStats().getAttack() / monsterEnemy.getStats().getDefense()) + 2) * random * effectivity * burn;
        return Math.floor(damage);
    }
}
