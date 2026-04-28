public class Main {
    public static void main(String[] args) {
        CustomList<Integer> customList = new CustomList<>();
        customList.addLast(0);
        customList.addLast(1);
        customList.addLast(5);
        customList.addLast(-3);
        customList.addFirst(100);
        customList.addLast(33);

        System.out.println(customList.getFirst());
        System.out.println(customList.getLast());
    }
}