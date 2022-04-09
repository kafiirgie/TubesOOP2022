package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;

public abstract class Move implements Cloneable {
    // ATTRIBUTES
    private final String name;
    private final ElementType elementType;
    private final int accuracy;
    private final int priority;
    private int ammunition;
    private final MoveTarget target;

    // CONSTRUCTOR
    public Move(String name,
                String elementType,
                String accuracy,
                String priority,
                String ammunition,
                String target) {
        this.name = name;
        this.elementType = ElementType.valueOf(elementType);
        this.accuracy = Integer.valueOf(accuracy);
        this.priority = Integer.valueOf(priority);
        this.ammunition = Integer.valueOf(ammunition);
        this.target = MoveTarget.valueOf(target);
    }
    
    // SETTER
    public void setAmunition(int ammunition) { this.ammunition = ammunition; }

    // GETTER
    public String getMoveName() { return this.name; }
    public ElementType getElementType() { return this.elementType; }
    public int getAccuracy() { return this.accuracy; }
    public int getPriority() { return this.priority; }
    public int getAmmunition() { return this.ammunition; }
    public MoveTarget getTarget() { return this.target; }

    public Object clone() throws CloneNotSupportedException {
        return (Move)super.clone();
    }
    // ABSTRACT METHOD
    public abstract void doMove(Monster monster, Monster monsterTarget);

}
