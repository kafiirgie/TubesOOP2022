package com.monstersaku.monsters.move;

import com.monstersaku.monsters.ElementType;

public class DefaultMove extends Move {
    // Attributes
    private double basePower;
    
    // Constructor
    public DefaultMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, double basePower) {
        super(id, name, elementType, 100, 0, 999999, target);
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
