import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person implements Comparable<Person> {
    private String name, last_name;
    private LocalDate birthDayDate, deathDate;
    private Set<Person> children;

    public static List<Person> fromCsv(String filePath) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // tutaj wczyta się linia nagłówka, można zignorować
            // wczytujemy linie aż do końca pliku
            // kiedy to metoda readLine() zwróci null
            while ( (line = reader.readLine()) != null) {
                System.out.println("wczytana linia: " + line);
            }

        } catch (IOException e) {
            System.out.println("nie udało się czytać z pliku: " + e.getMessage());
        }
        return personList;
    }

    public static Person fromCsvLine(String line) {
        // rodzielanie linii csv na elementy
        String[] elements = line.split(",", -1);
        String fullName = elements[0];
        // rozdzielanie na imie i nazwisko
        String[] nameParts = fullName.split(" ", 2);

        String birthDayString = elements[1];
        LocalDate birthDay = LocalDate.parse(birthDayString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        String deathDayString = elements[2];
        LocalDate deathDay = null;
        if (!deathDayString.isEmpty()) {
            deathDay = LocalDate.parse(deathDayString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        Person created = new Person(nameParts[0], nameParts[1], birthDay, deathDay);
        return created;
    }

    public boolean adopt(Person person) {
        boolean success = children.add(person);
        return success;
    }
    public Person getYoungestChild () {
        Person youngest = null;
        for (Person child : children) {
            if (youngest == null) {
                youngest = child;
            } else if (youngest.compareTo(child) < 0) {
                youngest = child;
            }
        }
        return youngest;
    }

    @Override
    public int compareTo(Person other) {
        if (this.birthDayDate.getYear() == other.birthDayDate.getYear()){
            return this.birthDayDate.getDayOfYear() - other.birthDayDate.getDayOfYear();
        }
        return this.birthDayDate.getYear() - other.birthDayDate.getYear();
    }

    public Person(String name,
                  String last_name,
                  LocalDate birthDayDate,
                  LocalDate deathDate) {
        this.name = name;
        this.birthDayDate = birthDayDate;
        this.deathDate = deathDate;
        this.last_name = last_name;
        this.children = new HashSet<>();
    }

    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", birthDayDate=" + birthDayDate +
                ", deathDate=" + deathDate +
                '}';
    }

    public List<Person> getChildren() {
        return this.children.stream().sorted().toList();
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }
}
