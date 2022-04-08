package com.monstersaku.monsters;

public class ElementEffKey {
    // ATTRIBUTES
    public final ElementType source;
    public final ElementType target;

    // CONSTRUCTOR
    public ElementEffKey(String source, String target){
        this.source = ElementType.valueOf(source);
        this.target = ElementType.valueOf(target);
    }
    public ElementEffKey(ElementType source, ElementType target){
        this.source = source;
        this.target = target;
    }

    // GETTER
    public ElementType getSource() { return this.source; }
    public ElementType getTarget() { return this.target; }
}