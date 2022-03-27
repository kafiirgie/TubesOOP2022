package com.monstersaku.monsters;

//import java.lang.annotation.ElementType;

public class ElementEff {
    // Attributes
    public final ElementType source;
    public final ElementType target;
    public final double effectivity;

    // Constructor
    public ElementEff(String source, String target, String effectivity){
        this.source = ElementType.valueOf(source);
        this.target = ElementType.valueOf(target);
        this.effectivity = Double.valueOf(effectivity);
    }

    // Getter
    public ElementType getSource() {
        return this.source;
    }

    public ElementType getTarget() {
        return this.target;
    }

    public double getEffectivity() {
        return this.effectivity;
    }
}