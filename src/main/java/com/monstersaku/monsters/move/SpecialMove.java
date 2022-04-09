package com.monstersaku.monsters.move;

import java.lang.Math;
import java.util.Random;

import com.monstersaku.monsters.ElementEffKey;
import com.monstersaku.monsters.Monster;
import com.monstersaku.monsters.statuscondition.StatusConditionType;
import com.monstersaku.util.Config;

public class SpecialMove extends Move{
    // CHILD CLASS ATTRIBUTE
    private final double basePower;
    
    // CONSTRUCTOR
    public SpecialMove(String name,
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
    public void doMove(Monster monster, Monster monsterTarget) {
        System.out.println("Using special move");
        
        // Calculate damage
        System.out.println("calculating damage...");
        double damage = calculateDamage(monster, monsterTarget);
        monsterTarget.takeDamage(damage); // TODO:monsterTarget take damage

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
        double damage = (this.basePower * (monster.getStats().getSpecialAttack() / monsterTarget.getStats().getSpecialDefense()) + 2) * random * effectivity * burn;
        return Math.floor(damage);
    }

}
