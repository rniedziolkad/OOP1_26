public abstract class Shape implements Svg{
    protected Style style;

    public Shape(Style style) {
        this.style = style;
    }

    // metoda abstrakcyjna, mówi, że klasy dziedziczące
    // muszą ją zaimplementować
    public abstract BoundingBox boundingBox();
}
