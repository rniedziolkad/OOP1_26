import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class SvgScene {
    private Polygon[] polygons;
    private int i;  // index pod który wstawić kolejny element

    public void save(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            BoundingBox bb = this.boundingBox();
            // na początek znacznik svg zgodny z http://www.w3.org/2000/svg
            String svg = String.format(
                    Locale.ENGLISH,
                    "<svg height=\"%f\" width=\"%f\" xmlns=\"http://www.w3.org/2000/svg\">\n",
                    bb.height(), bb.width()
            );

            writer.write(svg);
            writer.write(this.toSvg());
            // na koniec znacznik </svg>
            svg = "</svg>";
            writer.write(svg);

            writer.close();
        } catch (IOException e) {
            System.err.println("Nie udało się otworzyć pliku: " + e.getMessage());
        }

    }


    private BoundingBox boundingBox() {
        double minWidth = 0;
        double minHeight = 0;
        for (Polygon p : polygons) {
            if (p == null) continue;

            BoundingBox bb = p.boundingBox();
            double width = bb.x() + bb.width();
            if (width > minWidth) minWidth = width;
            double height = bb.y() + bb.height();
            if (height > minHeight) minHeight = height;
        }

        return new BoundingBox(0, 0, minWidth, minHeight);
    }

    public SvgScene() {
        polygons = new Polygon[3]; // inicjalizacja w konstruktorze
        i = 0;
    }

    public void addPolygon(Polygon poly) {
        polygons[i] = poly;
        i = (i + 1) % 3;    // zwiększenie o 1 z zapętleniem: 0 -> 1 -> 2 -> 0 -> 1 -> ...
    }

    public String toSvg() {
        String svg = "";
        for (Polygon poly : polygons) {
            if (poly == null) continue; // zabezpieczenie przed pustymi wartościami

            svg += poly.toSvg() + "\n";
        }
        return svg;
    }
}
