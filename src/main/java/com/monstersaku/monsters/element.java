import java.lang.annotation.ElementType;

public class element {
    public final ElementType type;
    public final ElementType target;
    public final double effectivity;

    // konstruktor
    public element(ElementType type, ElementType target, double effectivity){
        this.type = type;
        this.target = target;
        this.effectivity = effectivity;
    }
}
