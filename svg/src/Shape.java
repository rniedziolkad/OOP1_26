public abstract class Shape {
    protected Style style;

    public Shape(Style style) {
        this.style = style;
    }

    // metoda abstrakcyjna, mówi, że klasy dziedziczące
    // muszą ją zaimplementować
    public abstract String toSvg();
}
