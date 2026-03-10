public class Main {


    public static void main(String[] args) {
        Point p1 = new Point();
        p1.setX(50.0);
        p1.setY(50.0);
        System.out.println("p1 = " + p1);
        System.out.println(p1.toSvg());

        p1.translate(10, -20);
        System.out.println("p1 = " + p1);
        System.out.println(p1.toSvg());

        Point p2 = p1.translated(-30, 40);
        System.out.println("p2 = " + p2);
        System.out.println(p2.toSvg());

        Segment s1 = new Segment();
        s1.a = p1;
        s1.b = p2;
        System.out.println("s1 = " + s1);
        System.out.println("długość s1 = " + s1.length());
        System.out.println(s1.toSvg());

        Segment[] odcinki = new Segment[3];
        // (0, 0) -- (0, 40); dlugosc = 40
        odcinki[0] = new Segment();
        odcinki[0].a = new Point(); // trzeba utworzyć bo domyślnie jest null
        odcinki[0].a.setX(0);
        odcinki[0].a.setY(0);
        odcinki[0].b = new Point();
        odcinki[0].b.setX(0);
        odcinki[0].b.setY(40);
        // (60, 30) -- (30, 70); dlugosc 50
        odcinki[1] = s1;
        // (0, 0) -- (33, 0)
        odcinki[2] = new Segment();
        odcinki[2].a = new Point(); // trzeba utworzyć bo domyślnie jest null
        odcinki[2].a.setX(0);
        odcinki[2].a.setY(0);
        odcinki[2].b = new Point();
        odcinki[2].b.setX(33);
        odcinki[2].b.setY(0);

        // metodę statyczną maxLength wywołujemy za pomocą nazwy klasy
        Segment max = Segment.maxLength(odcinki);
        System.out.println("najdluższy: " + max);
    }
}