import java.util.Arrays;

public class Polygon extends Shape {
    private Point[] points;

    // metoda wytwórcza: statyczna metoda zwracająca utworzony obiekt,
    // nie jest konstruktorem
    public static Polygon square(Segment seg, Style style) {
        Segment perp = seg.perpendicular();
        return new Polygon(new Point[] {
             seg.getA(), perp.getA(), seg.getB(), perp.getB()
        }, style);
    }

    public BoundingBox boundingBox() {
        Point p0 = new Point(points[0]); // lewy górny róg
        Point p1 = new Point(points[0]); // prawy dolny róg
        for (Point p : points) {
            if (p.getX() < p0.getX()) p0.setX(p.getX());
            if (p.getX() > p1.getX()) p1.setX(p.getX());
            if (p.getY() < p0.getY()) p0.setY(p.getY());
            if (p.getY() > p1.getY()) p1.setY(p.getY());
        }

        return new BoundingBox(
                p0.getX(),
                p0.getY(),
                p1.getX() - p0.getX(),
                p1.getY() - p0.getY()
        );

    }

    public Polygon(Point[] points) {
        this(points, new Style("none", "black", 1));
    }

    public Polygon(Point[] points, Style style) {
        // super() wywołuje konstruktor klasy nadrzędnej
        super(style);
        this.points = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            this.points[i] = new Point(points[i]);
        }
    }


    @Override
    public String toString() {
        return "Polygon{" +
                "points=" + Arrays.toString(points) +
                '}';
    }

    @Override
    public String toSvg() {
        String pointsString = "";
        for (Point p : points) {
            pointsString += p.getX() + "," + p.getY() + " ";
        }

        return String.format("<polygon points=\"%s\" " +
                "style=\"%s\" />",
                pointsString, style.toSvg()
                );
    }
}
