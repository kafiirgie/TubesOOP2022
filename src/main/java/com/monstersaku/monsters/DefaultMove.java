package com.monstersaku.monsters;

public class DefaultMove extends Move {
    // Attributes
    private double basePower;
    
    // Constructor
    public DefaultMove(String name, ElementType elementType) {
        super(name, elementType, 100, 0, 999999);
        this.basePower = 50;
    }

    // Setter
    public void setBasePower(double basePower) {
        this.basePower = basePower;
    }

    // Getter
    public double getBasePower() {
        return this.basePower;
    }
    
    // Methods
    public void doMove() {
        System.out.println("It's default move!");
    }
}
