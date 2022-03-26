package com.monstersaku.monsters;

//import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

public class Monster {
    // Attributes
    private final String name;
    private List<ElementType> elements;
    private Stats stats;
    private List<Move> moves;
    private StateMonster state = StateMonster.ALIVE;

    // Constructor
    public MonsterModel(String name, Stats stats){
        this.name = name;
        this.elements = new ArrayList<ElementType>();
        this.stats = stats;
        this.moves = new ArrayList<Move>();
    }

    // Setter


    // Getter
    public String getName(){
        return this.name;
    }

    public List<ElementType> getElements(){
        return this.elements;
    }

    public StateMonster getStateMonster(){
        return this.state;
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    //addElement to monster
    //addMove to monster
    //showMonsterMoves
    //monster takedamage
}
