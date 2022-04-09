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

    // EQUALS OVERRIDE
    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
        // Check if o is an instance of Complex or not
        // "null instanceof [type]" also returns false
        if (!(o instanceof ElementEffKey)) {
            return false;
        }
        // typecast o to ElementEffKey so that we can compare data members
        ElementEffKey c = (ElementEffKey) o;
         
        // Compare the data members and return accordingly
        return this.source == c.source && this.target == c.target;
    }
}