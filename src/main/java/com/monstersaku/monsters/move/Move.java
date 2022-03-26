package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public abstract class Move {
    // Attributes
    private final String name;
    private final ElementType elementType;
    private final int accuracy;
    private final int priority;
    private final int ammunition;

    // Constructor
    public Move(String name, ElementType elementType, int accuracy, int priority, int ammunition) {
        this.name = name;
        this.elementType = elementType;
        this.accuracy = accuracy;
        this.priority = priority;
        this.ammunition = ammunition;
    }

    // Setter

    // Getter
    public String getMoveName() {
        return this.name;
    }

    public ElementType getElementType() {
        return this.elementType;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getPriority() {
        return this.priority;
    }

    public int getAmmunition() {
        return this.ammunition;
    }

    // Methods
    public abstract void doMove();

}
