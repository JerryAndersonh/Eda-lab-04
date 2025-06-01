import java.util.Scanner;

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

    public void insert(E data) {
        Node<E> newNode = new Node<>(data);
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
        if (tail == null) {
            System.out.println("Lista vacía");
            return;
        }
        
        Node<E> current = tail.next;
        System.out.print("Lista circular: ");
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != tail.next);
        System.out.println();
    }

    public boolean deleteByKey(E key) {
        if (tail == null) return false;

        Node<E> current = tail.next;
        Node<E> prev = tail;

        do {
            if (current.data.equals(key)) {
                if (current == current.next) { // Único nodo
                    tail = null;
                } else {
                    prev.next = current.next;
                    if (current == tail) tail = prev;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != tail.next);

        return false;
    }

    public boolean deleteAtPosition(int index) {
        if (index < 0 || index >= size) return false;
        if (tail == null) return false;

        Node<E> current = tail.next;
        Node<E> prev = tail;

        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }

        if (current == current.next) { // Único nodo
            tail = null;
        } else {
            prev.next = current.next;
            if (current == tail) tail = prev;
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public E removeFirst() {
        if (tail == null) return null;
        
        Node<E> head = tail.next;
        E data = head.data;
        
        if (head == tail) { // Único nodo
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return data;
    }

    public E removeLast() {
        if (tail == null) return null;
        
        E data = tail.data;
        
        if (tail.next == tail) { // Único nodo
            tail = null;
        } else {
            Node<E> current = tail.next;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = tail.next;
            tail = current;
        }
        size--;
        return data;
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
        size++;
    }

    public void addLast(E data) {
        insert(data);
    }

    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Llenar la lista con valores del 1 al 12
        for (int i = 1; i <= 12; i++) {
            list.insert(i);
        }

        int option;
        do {
            System.out.println("\n--- Menú de Operaciones (Lista Circular) ---");
            System.out.println("1. Imprimir lista");
            System.out.println("2. Eliminar por valor");
            System.out.println("3. Eliminar por posición");
            System.out.println("4. Obtener tamaño");
            System.out.println("5. Eliminar primer elemento");
            System.out.println("6. Eliminar último elemento");
            System.out.println("7. Agregar al inicio");
            System.out.println("8. Agregar al final");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();
            
            switch (option) {
                case 1:
                    list.printList();
                    break;
                case 2:
                    System.out.print("Ingrese el valor a eliminar: ");
                    int value = scanner.nextInt();
                    if (list.deleteByKey(value)) {
                        System.out.println("Valor eliminado");
                    } else {
                        System.out.println("Valor no encontrado");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese la posición a eliminar: ");
                    int position = scanner.nextInt();
                    if (list.deleteAtPosition(position)) {
                        System.out.println("Elemento en posición " + position + " eliminado");
                    } else {
                        System.out.println("Posición inválida");
                    }
                    break;
                case 4:
                    System.out.println("Tamaño de la lista: " + list.size());
                    break;
                case 5:
                    Integer first = list.removeFirst();
                    if (first != null) {
                        System.out.println("Primer elemento eliminado: " + first);
                    } else {
                        System.out.println("Lista vacía");
                    }
                    break;
                case 6:
                    Integer last = list.removeLast();
                    if (last != null) {
                        System.out.println("Último elemento eliminado: " + last);
                    } else {
                        System.out.println("Lista vacía");
                    }
                    break;
                case 7:
                    System.out.print("Ingrese el valor a agregar al inicio: ");
                    int newFirst = scanner.nextInt();
                    list.addFirst(newFirst);
                    System.out.println("Valor agregado al inicio");
                    break;
                case 8:
                    System.out.print("Ingrese el valor a agregar al final: ");
                    int newLast = scanner.nextInt();
                    list.addLast(newLast);
                    System.out.println("Valor agregado al final");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (option != 0);
        
        scanner.close();
    }
}