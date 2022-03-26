package com.monstersaku.monsters;

public class Stats {
    // Attributes
    private double healthPoint;
    private double attack;
    private double defense;
    private double specialAttack;
    private double specialDefense;
    private double speed;

    // Constructor
    public Stats(double healthPoint, double attack, double defense, double specialAttack, double specialDefense, double speed) {
        this.healthPoint = healthPoint;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    // Setter
    public void setHealthPoint(double healthPoint) {
        this.healthPoint = healthPoint;
    }
    public void setAttack(double attack) {
        this.attack = attack;
    }
    public void setDefense(double defense) {
        this.defense = defense;
    }
    public void setSpecialAttack(double specialAttack) {
        this.specialAttack = specialAttack;
    }
    public void setSpecialDefense(double specialDefense) {
        this.specialDefense = specialDefense;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Getter
    public double getHealthPoint() {
        return this.healthPoint;
    }

    public double getAttack() {
        return this.attack;
    }

    public double getDefense() {
        return this.defense;
    }

    public double getSpecialAttack() {
        return this.specialAttack;
    }

    public double getSpecialDefense() {
        return this.specialDefense;
    }

    public double getSpeed() {
        return this.speed;
    }

    // Methods

    
}
