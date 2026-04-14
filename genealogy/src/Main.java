

public class Main {
    public static void main(String[] args) {
        Person p = Person.fromCsvLine(
                "Tomasz Dąbrowski,24.01.1966,,Anna Dąbrowska,"
        );
        System.out.println(p);

        Person.fromCsv("family.csv");

    }
}