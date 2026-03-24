import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Adam", "Kowalski" ,LocalDate.of(2000, 1, 1)));
        persons.add(new Person("Anna", "Kowalska" ,LocalDate.of(2018, 3, 13)));
        persons.add(new Person("Alicja", "Kowalska" ,LocalDate.of(2016, 7, 7)));

        for (Person person: persons) {
            System.out.println(person.toString());
        }

        persons.get(0).adopt(persons.get(1));
        persons.get(0).adopt(persons.get(2));

        Person youngestChild = persons.get(0).getYoungestChild();
        System.out.println("Najmłodsze dziecko: " + youngestChild);

        System.out.println("Dzieci: " + persons.get(0).getChildren());

        Family rodzina = new Family();
        rodzina.add(persons.get(0));
        rodzina.add(persons.get(1));
        rodzina.add(persons.get(2));

        System.out.println("Adam Kowalski w rodzinie: "+rodzina.get("Adam Kowalski"));
        System.out.println("Anna Kowalska w rodzinie: "+rodzina.get("Anna Kowalska"));
        System.out.println("Nie Ma w rodzinie: "+rodzina.get("Nie Ma"));
    }
}