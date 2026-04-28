public class CustomList {
    private static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    public CustomList() {
        this.head = this.tail = null;
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            this.tail = this.head = newNode; // lista była pusta pierwszy element
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }
    public int getLast() {
        if (tail == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        return tail.value;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            this.head = this.tail = newNode; // lista była pusta, pierwszy element
        } else {
            newNode.next = this.head;
            this.head = newNode;
        }
    }
    public int getFirst() {
        if (head == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        return head.value;
    }

    public int removeFirst() {
        if (head == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        int value = head.value;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;   // lista stała się pusta
        }
        return value;
    }

    public int removeLast() {
        if (tail == null) {
            throw new RuntimeException("Lista jest pusta");
        }
        int value = tail.value;
        Node current = this.head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        return value;
    }

}
