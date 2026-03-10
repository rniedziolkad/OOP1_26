public class Point {
    private double x, y;

    // konstruktor to specjalna metoda, którą używamy
    // do tworzenia obiektów
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //konstruktor bezargumentowy
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    // konstruktor kopiujący
    public Point(Point other) {
        this.x = other.x;
        this.y = other.y;
    }

    // akcesor (getter)
    public double getX() {
        return x;
    }
    // mutator (setter)
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + y + ")";
    }

    public String toSvg() {
        return "<circle r=\"2\" cx=\"" + x +
                "\" cy=\"" + y + "\" fill=\"black\" />";
    }

    public void translate(double dx, double dy) {
        this.x += dx;
        y += dy;
    }

    public Point translated(double dx, double dy) {
        Point newPoint = new Point();
        newPoint.x = this.x + dx;
        newPoint.y = this.y + dy;
        return newPoint;
    }
}
