import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // TODO: Zadanie 6 "Pliki, wyjątki"
        List<Person> loaded = Person.fromCsv("family.csv");
        Person.toBinaryFile("binary.data", loaded);
        System.out.println("Loaded list");
        List<Person> fromBinary = Person.fromBinaryFile("binary.data");
        String uml = Person.listToPlantUml(fromBinary);

        PlantUMLRunner.setJarPath("/home/student/Pobrane/plantuml-1.2026.2.jar");
        PlantUMLRunner.generateUML(uml, "/home/student/Pobrane/", "output");


        List<Person> filtered = Person.filterSubstring(fromBinary, "Kowal");
        System.out.println("Osoby z 'Kowal':");
        for (Person p : filtered) {
            System.out.println(p);
        }


    }
}