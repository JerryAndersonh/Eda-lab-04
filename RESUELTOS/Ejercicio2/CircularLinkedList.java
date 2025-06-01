public class CircularLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> tail;
    private int size;

    public CircularLinkedList() {
        tail = null;
        size = 0;
    }

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void printList() {
        if (tail == null) return;
        
        Node<E> current = tail.next;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != tail.next);
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        list.printList();
    }
}