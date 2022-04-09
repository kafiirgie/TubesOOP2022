package com.monstersaku.monsters;

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

    // SETTER
    public void setHealthPoint(int healthPoint) { this.healthPoint = healthPoint; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void setSpecialAttack(int specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefense(int specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeed(int speed) { this.speed = speed; }

    // GETTER
    public int getHealthPoint() { return this.healthPoint; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getSpecialAttack() { return this.specialAttack; }
    public int getSpecialDefense() { return this.specialDefense; }
    public int getSpeed() { return this.speed; }

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