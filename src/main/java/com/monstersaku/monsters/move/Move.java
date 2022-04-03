package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public abstract class Move {
    // Attributes
    //private final int id;
    private final String name;
    private final ElementType elementType;
    private final int accuracy;
    private final int priority;
    private final int ammunition;
    private final MoveTarget target;

    // Constructor
    public Move(String name, String elementType, String accuracy, String priority, String ammunition, String target) {
        //this.id = Integer.valueOf(id);
        this.name = name;
        this.elementType = ElementType.valueOf(elementType);
        this.accuracy = Integer.valueOf(accuracy);
        this.priority = Integer.valueOf(priority);
        this.ammunition = Integer.valueOf(ammunition);
        this.target = MoveTarget.valueOf(target);
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
