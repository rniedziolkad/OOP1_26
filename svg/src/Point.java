public class Point {
    public double x, y;

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
