import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TODO: Zadanie 6 "Pliki, wyjątki"
        List<Person> loaded = Person.fromCsv("family.csv");
        Person.toBinaryFile("binary.data", loaded);
        System.out.println("Loaded list");
        List<Person> fromBinary = Person.fromBinaryFile("binary.data");
        if (fromBinary != null) {
            for (Person p : fromBinary) {
                System.out.println(p);
                System.out.println("dzieci: " + p.getChildren());
            }
        } else {
            System.out.println("Nie odczytano z pliku binarnego");
        }

    }
}