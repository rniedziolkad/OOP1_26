import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
//        List<Double> customList = new CustomList<>();
//        customList.addLast(0.0);
//        customList.addLast(1.0);
//        customList.addLast(5.0);
//        customList.addLast(-3.0);
//        customList.addFirst(100.0);
//        customList.addLast(33.0);
//
//        System.out.println(customList.getFirst());
//        System.out.println(customList.getLast());
//        System.out.println(customList.size());

        List<Object> objects = new ArrayList<>();
        objects.add("Ala ma kota");
        objects.add(true);
        objects.add(150);
        objects.add(3.97);
        objects.add("Ala ma psa");
        List<String> filter = filterObject(objects, String.class);

        System.out.println(filter);
    }

    public static <T, R> List<R> filterObject(List<T> input, Class<R> tClass) {
        return (List<R>) input.stream().filter(tClass::isInstance).toList();
    }

}