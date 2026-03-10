public class Segment {
    private Point a, b;


    public Segment(Point a, Point b) {
        // używamy konstruktora kopiującego punkt
        this.a = new Point(a);
        this.b = new Point(b);
    }

    public Segment() {
        // jeśli ktoś nie chce podawać punktów podcza tworzenia
        // to niech będą null
        this.a = null;
        this.b = null;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        // używamy konstruktora kopiującego punkt
        this.a = new Point(a);
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        // używamy konstruktora kopiującego punkt
        this.b = new Point(b);
    }

    public double length() {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public String toSvg() {
        return "<line x1=\""+ a.getX() + "\" y1=\"" + a.getY() + "\" " +
                "x2=\"" + b.getX() + "\" y2=\"" + b.getY() +"\" " +
                "style=\"stroke:red;stroke-width:4\" />";
    }

    public static Segment maxLength(Segment[] segments) {
        Segment result = null;

        for(Segment seg: segments) {
            if ( result == null || seg.length() > result.length()) result = seg;
        }
        return result;
    }
}
