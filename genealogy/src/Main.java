

public class Main {
    public static void main(String[] args) {
        Person p = Person.fromCsvLine(
                "Anna Dąbrowska,07.02.1930,22.12.1991,Ewa Kowalska,Marek Kowalski"
        );
        System.out.println(p);

    }
}