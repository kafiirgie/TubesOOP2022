package com.monstersaku.monsters;

import java.util.Random;

public class StatsBuff {
    // ATTRIBUTES
    private static final int MAX_STATSBUFF = 4;
	private static final int MIN_STATSBUFF = -4;
    private int healthPoint = 0;
    private int attack = 0;
    private int defense = 0;
    private int specialAttack = 0;
    private int specialDefense = 0;
    private int speed = 0;

    // CONSTRUCTOR
    public StatsBuff(int healthPoint, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }
    public StatsBuff() {
        this.healthPoint = 0;
        this.attack = 0;
        this.defense = 0;
        this.specialAttack = 0;
        this.specialDefense = 0;
        this.speed = 0;
        // Random r = new Random();
        // this.healthPoint = r.nextInt(9) - 4;
        // this.attack = r.nextInt(9) - 4;
        // this.defense = r.nextInt(9) - 4;
        // this.specialAttack = r.nextInt(9) - 4;
        // this.specialDefense = r.nextInt(9) - 4;
        // this.speed = r.nextInt(9) - 4;
    }

    // SETTER
    public void setHealthPointBuff(int healthPoint) { this.healthPoint = healthPoint; }
    public void setAttackBuff(int attack) { this.attack = attack; }
    public void setDefenseBuff(int defense) { this.defense = defense; }
    public void setSpecialAttackBuff(int specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefenseBuff(int specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeedBuff(int speed) { this.speed = speed; }

    // GETTER
    public int getHealthPointBuff() { return this.healthPoint; }
    public int getAttackBuff() { return this.attack; }
    public int getDefenseBuff() { return this.defense; }
    public int getSpecialAttackBuff() { return this.specialAttack; }
    public int getSpecialDefenseBuff() { return this.specialDefense; }
    public int getSpeedBuff() { return this.speed; }

    // METHODS
    public static double getFactor(int statsBuffValue) {
        switch (statsBuffValue) {
            case -4:
                return ((double) 2/6);
            case -3:
                return ((double) 2/5);
            case -2:
                return ((double) 2/4);
            case -1:
                return ((double) 2/3);
            case 0:
                return ((double) 1);
            case 1:
                return ((double) 3/2);
            case 2:
                return ((double) 4/2);
            case 3:
                return ((double) 5/2);
            case 4:
                return ((double) 6/2);
            default:
                return ((double) 1);
        }
    }
    
}