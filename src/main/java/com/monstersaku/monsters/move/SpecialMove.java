package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public class SpecialMove extends Move{
    // Attributes
    private double basePower;
    
    // Constructor
    public SpecialMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, double basePower) {
        super(id, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = basePower;
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
        System.out.println("It's special move!");
    }
}
