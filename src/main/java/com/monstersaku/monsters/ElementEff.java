package com.monstersaku.classes;
import java.lang.annotation.ElementType;

public class ElementEff {
    public final ElementType type;
    public final ElementType target;
    public final double effectivity;

    // konstruktor
    public ElementEff(ElementType type, ElementType target, double effectivity){
        this.type = type;
        this.target = target;
        this.effectivity = effectivity;
    }
}
