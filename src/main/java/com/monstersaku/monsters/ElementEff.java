package com.monstersaku.monsters;

//import java.lang.annotation.ElementType;

public class ElementEff {
    // Attributes
    public final ElementType source;
    public final ElementType target;
    public final double effectivity;

    // Constructor
    public ElementEff(ElementType source, ElementType target, double effectivity){
        this.source = source;
        this.target = target;
        this.effectivity = effectivity;
    }

    // Getter
    public ElementType getTypeEff() {
        return this.source;
    }

    public ElementType getTargetEff() {
        return this.target;
    }

    public double getEffectivity() {
        return this.effectivity;
    }
}