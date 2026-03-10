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
        s1.setA(p1);
        s1.setB(p2);
        System.out.println("s1 = " + s1);
        System.out.println("długość s1 = " + s1.length());
        System.out.println(s1.toSvg());

        Segment[] odcinki = new Segment[3];
        // (0, 0) -- (0, 40); dlugosc = 40
        odcinki[0] = new Segment(new Point(0, 0), new Point(0, 40));
        // (60, 30) -- (30, 70); dlugosc 50
        odcinki[1] = s1;
        // (0, 0) -- (33, 0)
        odcinki[2] = new Segment();
        odcinki[2].setA(new Point(0, 0)); // trzeba utworzyć bo domyślnie jest null
        odcinki[2].setB(new Point(33, 0));

        // metodę statyczną maxLength wywołujemy za pomocą nazwy klasy
        Segment max = Segment.maxLength(odcinki);
        System.out.println("najdluższy: " + max);
        System.out.println();

        System.out.println("s1 przed modyfikacjami: " + s1);
        s1.setA(new Point(0, 0));   // zamienimy punkt a segmentu s1 na nowy punkt; powinno zmienić
        p2.setX(70);    // nie chcemy, żeby zmodyfikowało obiekt s1
        System.out.println("s1 po modyfikacjach: " + s1);
        System.out.println("najdluzszy po modyfikacjach: " + max);

    }
}