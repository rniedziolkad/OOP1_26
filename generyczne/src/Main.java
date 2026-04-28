public class Main {
    public static void main(String[] args) {
        CustomList<Double> customList = new CustomList<>();
        customList.addLast(0.0);
        customList.addLast(1.0);
        customList.addLast(5.0);
        customList.addLast(-3.0);
        customList.addFirst(100.0);
        customList.addLast(33.0);

        System.out.println(customList.getFirst());
        System.out.println(customList.getLast());
        System.out.println(customList.size());
    }
}