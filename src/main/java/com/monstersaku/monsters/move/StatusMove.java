package com.monstersaku.monsters.move;

import com.monstersaku.monsters.*;
import com.monstersaku.monsters.statuscondition.*;

public class StatusMove extends Move {
    // Attributes
    private StatusConditionType status;
    private double effectHealthPoint;
    private double effectAttack;
    private double effectDefense;
    private double effectSpecialAttack;
    private double effectSpecialDefense;
    private double effectSpeed;
    // Constructor
    public StatusMove(String id, String name, String elementType, String accuracy, String priority, String ammunition, String target, String status, String effects) {
        // Set super class attributes
        super(id, name, elementType, accuracy, priority, ammunition, target);
        // Set status
        if (!status.equals("-")) {
            this.status = StatusConditionType.valueOf(status);
        } else {
            this.status = null;
        }
        // Set effect
        String[] effect = effects.split(",");
        this.effectHealthPoint = Double.valueOf(effect[0]);
        this.effectAttack = Double.valueOf(effect[1]);
        this.effectDefense = Double.valueOf(effect[2]);
        this.effectSpecialAttack = Double.valueOf(effect[3]);
        this.effectSpecialDefense = Double.valueOf(effect[4]);
        this.effectSpeed = Double.valueOf(effect[5]);
    }
    
    // Methods
    public void doMove() {
        System.out.println("It's status move!");
    }
}
