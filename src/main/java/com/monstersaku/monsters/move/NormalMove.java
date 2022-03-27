package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public class NormalMove extends Move{
    // Attributes
    private double basePower;
    
    // Constructor
    public NormalMove(String id, String name, String elementType, String accuracy, String priority, String ammunition, String target, String basePower) {
        super(id, name, elementType, accuracy, priority, ammunition, target);
        this.basePower = Double.valueOf(basePower);
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
        System.out.println("It's normal move!");
    }
}
