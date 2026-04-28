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

}
