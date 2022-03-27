package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public class StatusMove extends Move {
    // Constructor
    public StatusMove(String id, String name, String elementType, String accuracy, String priority, String ammunition, String target, double basePower) {
        super(id, name, elementType, accuracy, priority, ammunition, target);
    }
    
    // Methods
    public void doMove() {
        System.out.println("It's default move!");
    }
}
