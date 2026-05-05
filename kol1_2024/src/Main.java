import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        DigitalClock digitalClock= new DigitalClock(DigitalClock.Type.H12);
        digitalClock.setTime(23, 59, 59);
        System.out.println("Godzina: " + digitalClock.toString());
        Map<String, City> v = City.parseFile("strefy.csv");
        System.out.println(v);
    }
}