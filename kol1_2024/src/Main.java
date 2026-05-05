import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, City> v = City.parseFile("strefy.csv");
        DigitalClock digitalClock= new DigitalClock(DigitalClock.Type.H24, v.get("Warszawa"));
        digitalClock.setTime(21, 0, 0);
        digitalClock.setCity(v.get("Kijów"));
        System.out.println("Godzina: " + digitalClock.toString());
        digitalClock.setCity(v.get("Kijów"));
        System.out.println("Godzina: " + digitalClock.toString());
        System.out.println(v.get("Warszawa"));
    }
}