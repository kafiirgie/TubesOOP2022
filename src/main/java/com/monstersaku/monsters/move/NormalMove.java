package com.monstersaku.monsters.move;

import java.lang.Math;
import java.util.Random;

import com.monstersaku.monsters.ElementEffKey;
import com.monstersaku.monsters.Monster;
import com.monstersaku.monsters.statuscondition.StatusConditionType;
import com.monstersaku.util.Config;

public class NormalMove extends Move {
    // CHILD CLASS ATTRIBUTE
    private final double basePower;
    
    // CONSTRUCTOR
    public NormalMove(String name,
                      String elementType,
                      String accuracy,
                      String priority,
                      String ammunition,
                      String target,
                      String basePower) {
        super(name, elementType, accuracy, priority, ammunition, target);
        this.basePower = Double.valueOf(basePower);
    }

    // GETTER
    public double getBasePower() { return this.basePower; }
    
    // PARENT CLASS METHOD
    public void doMove(Monster monsterOwn, Monster monsterEnemy) {
        System.out.println("Using NORMAL MOVE...");
        // Calculate damage
        System.out.println("calculating damage...");
        double damage = calculateDamage(monsterOwn, monsterEnemy);
        monsterEnemy.takeDamage(damage);
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
