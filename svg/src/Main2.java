public class Main2 {
    public static void main(String[] args) {
        // Wielokąty -----------------------
        Point[] vertices = new Point[4];
        vertices[0] = new Point(10, 10);
        Point v2 = new Point(70, 10);
        vertices[1] = v2;
        Point v3 = new Point(50, 50);
        vertices[2] = v3;
        vertices[3] = new Point(10, 70);

        Polygon poly = new Polygon(vertices);
        System.out.println(poly);
        System.out.println(poly.toSvg());

        vertices[0] = new Point(100, 100);
        v2.setY(100);
        System.out.println("Po zmianie: " + poly);
        System.out.println();

        // Scena SVG
        SvgScene scene = new SvgScene();
        scene.addPolygon(poly);
        scene.addPolygon(new Polygon(new Point[] {
                new Point(120, 120),
                new Point(160, 160),
                new Point(120, 160)
        }));
        Style myStyle = new Style("red", "blue", 3);
        scene.addPolygon(new Polygon(new Point[] {
                new Point(10, 100),
                new Point(20, 200),
                new Point(80, 160)
        }, myStyle));

        Polygon square = Polygon.square(
                new Segment(
                        new Point(10, 10),
                        new Point(80, 80)
                ),
                myStyle
        );
        scene.addPolygon(square);

        System.out.println(scene.toSvg());
        scene.save("obrazek.svg");

    }
}
