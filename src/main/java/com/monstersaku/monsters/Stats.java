package com.monstersaku.monsters;

public class Stats {
    // ATTRIBUTES
    private final double maxHealthPoint;
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    // CONSTRUCTOR
    public Stats(double healthPoint, double attack, double defense, double specialAttack, double specialDefense, double speed) {
        this.maxHealthPoint = healthPoint;
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    // SETTER
    public void setHealthPoint(double healthPoint) { this.healthPoint = healthPoint; }
    public void setAttack(double attack) { this.attack = attack; }
    public void setDefense(double defense) { this.defense = defense; }
    public void setSpecialAttack(double specialAttack) { this.specialAttack = specialAttack; }
    public void setSpecialDefense(double specialDefense) { this.specialDefense = specialDefense; }
    public void setSpeed(double speed) { this.speed = speed; }

    // GETTER
    public double getMaxHealthPoint() { return this.maxHealthPoint; }
    public double getHealthPoint() { return this.healthPoint; }
    public double getAttack() { return this.attack; }
    public double getDefense() { return this.defense; }
    public double getSpecialAttack() { return this.specialAttack; }
    public double getSpecialDefense() { return this.specialDefense; }
    public double getSpeed() { return this.speed; }

    // METHODS
    public void showStats() {
        System.out.println("Max Health Point : " + this.maxHealthPoint);
        System.out.println("Health Point     : " + this.healthPoint);
        System.out.println("Attack           : " + this.attack);
        System.out.println("Defense          : " + this.defense);
        System.out.println("Special Attack   : " + this.specialAttack);
        System.out.println("Special Defense  : " + this.specialDefense);
        System.out.println("Speed            : " + this.speed);
    }
    //public int getFinalHealthPoint() {}
}
