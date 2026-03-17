import java.util.Arrays;

public class Polygon {
    private Point[] points;

    public Polygon(Point[] points) {
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

    public String toSvg() {
        String pointsString = "";
        for (Point p : points) {
            pointsString += p.getX() + "," + p.getY() + " ";
        }

        return String.format("<polygon points=\"%s\" " +
                "style=\"fill:lime;stroke:purple;stroke-width:3\" />",
                pointsString
                );
    }
}
