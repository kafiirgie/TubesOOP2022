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
    public void doMove(Monster monster, Monster monsterTarget) {
        System.out.println("Using default move");
        // Reduce Health Point
        System.out.println("reducing health point...");
        double reducingHealth = Math.floor(monster.getStats().getMaxHealthPoint() * 0.25);
        System.out.println("health point reduced " + reducingHealth + " points");

        double currentHealth = monster.getStats().getHealthPoint();
        double reducedHealth = currentHealth - reducingHealth;
        if (reducedHealth > 0) {
            monster.getStats().setHealthPoint(reducedHealth);
            System.out.println("healt point now is " + reducedHealth + " points");

            // Calculate damage
            System.out.println("calculating damage...");
            double damage = calculateDamage(monster, monsterTarget);
            monsterTarget.takeDamage(damage); // TODO:monsterTarget take damage
        } else {
            monster.setIsAlive(false);
            System.out.println("monster died X_X");
        }

        // Update ammunition
        super.setAmunition(super.getAmmunition() - 1);
    }

    private double calculateDamage(Monster monster, Monster monsterTarget) {
        // Random
        double rangeMin = 0.85;
        double rangeMax = 1;
        Random r = new Random();
        double random = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        // Element eff
        ElementEffKey key = new ElementEffKey(super.getElementType(), monsterTarget.getElements().get(0));
        double effectivity = Config.getMapOfElementEff().get(key);
        // Burn Factor
        double burn;
        if (monster.getStatus() == StatusConditionType.BURN) {
            burn = 0.5;
        } else {
            burn = 1;
        }
        // Damage
        // TODO:stats buff implementation for calculate damage
        double damage = (this.basePower * (monster.getStats().getAttack() / monsterTarget.getStats().getDefense()) + 2) * random * effectivity * burn;
        return Math.floor(damage);
    }
}
