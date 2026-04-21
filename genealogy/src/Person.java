import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Person implements Comparable<Person>, Serializable {
    private String name, last_name;
    private LocalDate birthDayDate, deathDate;
    private Set<Person> children;

    public static List<Person> sortedByBirth(List<Person> personList) {
        return personList.stream()
                .sorted((p1, p2) -> {
                    if (p1.birthDayDate.isAfter(p2.birthDayDate)) {
                        return 1;
                    } else if (p1.birthDayDate.isBefore(p2.birthDayDate)) {
                        return -1;
                    } else {
                        return 0;
                    }
                })
                .toList();
    }

    public static List<Person> sortedByDeathAndLongOfLive(List<Person> personList) {
        return personList.stream()
                .filter((person) -> {
                    return person.deathDate != null;
                })
                .sorted((p1, p2) -> {
                    long p1longLive = ChronoUnit.DAYS.between(p1.deathDate, p1.birthDayDate);
                    long p2longLive = ChronoUnit.DAYS.between(p2.deathDate, p2.birthDayDate);
                    return (int)(p1longLive - p2longLive);
                })
                .toList();
    }

    public static Optional<Person> getOldPerson (List<Person> personList) {
        return personList.stream()
                .filter(person -> {
                    return person.deathDate == null;
                }).min((p1, p2) -> {
                    return p1.compareTo(p2);
                });
    }

    public static List<Person> filterSubstring(List<Person> personList, String substring) {
//        List<Person> result = new ArrayList<>();
//        for (Person p : personList) {
//            String name = p.name + " " + p.last_name;
//            if (name.contains(substring)) {
//                result.add(p);
//            }
//        }
//        return result;
        return personList.stream()
                .filter((person) -> {
                    String name = person.name + " " + person.last_name;
                    return name.contains(substring);
                })
                .toList();
    }

    public static String listToPlantUml(List<Person> personList) {
        String uml = "@startuml\n";
        for (Person p : personList) {
            uml += "object " + p.name + "_" + p.last_name + "\n";
        }

        for (Person p : personList) {
            for (Person child : p.children) {
                uml += child.name + "_" + child.last_name + " --> " + p.name + "_" + p.last_name + "\n";
            }
        }
        uml += "@enduml\n";
        return uml;
    }

    public String toPlantUml() {
        String uml = "object " + this.name + "_" + this.last_name +"\n";
        for (Person child : children) {
            uml += child.name + "_" + child.last_name + " --> " + this.name + "_" + this.last_name +"\n";
        }
        return uml;
    }

    public static void toBinaryFile(String filePath, List<Person> personList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(personList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Person> fromBinaryFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Person> loaded = (List<Person>) ois.readObject();
            return loaded;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Person> fromCsv(String filePath) {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // tutaj wczyta się linia nagłówka, można zignorować
            // wczytujemy linie aż do końca pliku
            // kiedy to metoda readLine() zwróci null
            while ( (line = reader.readLine()) != null) {
                // System.out.println("wczytana linia: " + line);
                Person parsed = fromCsvLine(line);
                for (Person p : personList) {
                    if(Objects.equals(p.name, parsed.name) && Objects.equals(p.last_name, parsed.last_name)){
                        throw new AmbiguousPersonException(parsed);
                    }
                }
                personList.add(parsed);
                String[] parts = line.split(",", -1);
                String parent1 = parts[3];
                String parent2 = parts[4];

                for (Person p : personList) {
                    String fullName = p.name + " " + p.last_name;
                    if (fullName.equals(parent1)) {
                        p.adopt(parsed);
                    }

                    if (fullName.equals(parent2)) {
                        p.adopt(parsed);
                    }
                }

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
            if (deathDay.isBefore(birthDay)) {
                throw new NegativeLifespanException(birthDay, deathDay);
            }
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
