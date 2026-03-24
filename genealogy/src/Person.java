import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Person implements Comparable<Person> {
    private String name, last_name;
    private LocalDate birthDayDate;
    private Set<Person> children;

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
                  LocalDate birthDayDate) {
        this.name = name;
        this.birthDayDate = birthDayDate;
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
