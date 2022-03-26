package com.monstersaku.classes;
package com.monstersaku.classes;
package com.monstersaku.classes;
package com.monstersaku.classes;
package com.monstersaku.classes;
package com.monstersaku.classes;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import com.monsters.element;
import com.monsters.ElementType;
import com.monsters.move;
//import disini

public class Monster {
    private final String name;
    private List<ElementType> elements;
    private Stats stats;
    private List<Move> moves;
    private StateMonster state = StateMonster.ALIVE;

    //konstruktor
    public MonsterModel(String name, Stats stats){
        this.name = name;
        this.elements = new ArrayList<ElementType>();
        this.stats = stats;
        this.moves = new ArrayList<Move>();
    }

    //getName
    public String getName(){
        return this.name;
    }

    //getElement
    public List<ElementType> getElements(){
        return this.elements;
    }

    //getStateMonster
    public StateMonster getStateMonster(){
        return this.state;
    }

    //getMoves
    public List<Move> getMoves(){
        return this.moves;
    }

    //addElement to monster
    //addMove to monster
    //showMonsterMoves
    //monster takedamage
}
