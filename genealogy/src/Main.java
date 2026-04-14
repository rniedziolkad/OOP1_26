import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> loaded = Person.fromCsv("family.csv");
        System.out.println("Loaded list");
        for (Person p : loaded) {
            System.out.println(p);
            System.out.println("dzieci: " + p.getChildren());
        }

    }
}