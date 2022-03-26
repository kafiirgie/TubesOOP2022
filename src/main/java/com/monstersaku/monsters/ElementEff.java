package com.monstersaku.monsters;

//import java.lang.annotation.ElementType;

public class ElementEff {
    // Attributes
    public final ElementType type;
    public final ElementType target;
    public final double effectivity;

    // Constructor
    public ElementEff(ElementType type, ElementType target, double effectivity){
        this.type = type;
        this.target = target;
        this.effectivity = effectivity;
    }

    // Getter
    public ElementType getTypeEff() {
        return this.type;
    }

    public ElementType getTargetEff() {
        return this.target;
    }

    public double getEffectivity() {
        return this.effectivity;
    }
}