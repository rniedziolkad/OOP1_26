public class SvgScene {
    private Polygon[] polygons;
    private int i;  // index pod który wstawić kolejny element

    public SvgScene() {
        polygons = new Polygon[3]; // inicjalizacja w konstruktorze
        i = 0;
    }

    public void addPolygon(Polygon poly) {
        polygons[i] = poly;
        i = (i + 1) % 3;    // zwiększenie o 1 z zapętleniem: 0 -> 1 -> 2 -> 0 -> 1 -> ...
    }


}
