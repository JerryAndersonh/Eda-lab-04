public class DoublyLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void printList() {
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        list.printList();
    }
}