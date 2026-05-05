import org.w3c.dom.xpath.XPathResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class City {
    private String city_name;
    private int clock_region;
    private String longitude;
    private String latitude;

    public int getClock_region() {
        return clock_region;
    }

    public City(String city_name, int clock_region, String longitude, String latitude) {
        this.city_name = city_name;
        this.clock_region = clock_region;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static Map<String, City> parseFile (String file_name) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(file_name));
        Map<String, City> result = new HashMap<>();
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            City parseLine = parseLine(line);
            result.put(parseLine.city_name, parseLine);
        }
        return result;
    }

    private static City parseLine (String file_line) {
        String[] parts = file_line.split(",");
        return new City(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
    }

    @Override
    public String toString() {
        return "City{" +
                "city_name='" + city_name + '\'' +
                ", clock_region=" + clock_region +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
