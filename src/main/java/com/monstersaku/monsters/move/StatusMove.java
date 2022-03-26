package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public class StatusMove extends Move {
    // Constructor
    public StatusMove(String name, ElementType elementType, int accuracy, int priority, int ammunition) {
        super(name, elementType, accuracy, priority, ammunition);
    }
    
    // Methods
    public void doMove() {
        System.out.println("It's default move!");
    }
}
