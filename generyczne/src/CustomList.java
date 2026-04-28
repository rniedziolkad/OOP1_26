import java.util.AbstractList;

public class CustomList<T> extends AbstractList<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;
        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    public CustomList() {
        this.head = this.tail = null;
    }

    @Override
    public int size() {
        int counter = 1;
        if (this.head == null) return 0;

        Node<T> current = this.head;
        while (current.next != null) {
            counter++;
            current = current.next;
        }
        return counter;
    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public boolean add(T t) {
        addLast(t);
        return true;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (tail == null) {
            this.tail = this.head = newNode; // lista była pusta pierwszy element
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }
    public T getLast() {
        if (tail == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        return tail.value;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            this.head = this.tail = newNode; // lista była pusta, pierwszy element
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
    }
    public T getFirst() {
        if (head == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        return head.value;
    }

    public T removeFirst() {
        if (head == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        T value = head.value;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;   // lista stała się pusta
        }
        return value;
    }

    public T removeLast() {
        if (tail == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        T value = tail.value;
        Node<T> current = this.head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        return value;
    }

}
