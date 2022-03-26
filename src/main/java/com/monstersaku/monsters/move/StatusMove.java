package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public class StatusMove extends Move {
    // Constructor
    public StatusMove(int id, String name, ElementType elementType, int accuracy, int priority, int ammunition, MoveTarget target, double basePower) {
        super(id, name, elementType, accuracy, priority, ammunition, target);
    }
    
    // Methods
    public void doMove() {
        System.out.println("It's default move!");
    }
}
