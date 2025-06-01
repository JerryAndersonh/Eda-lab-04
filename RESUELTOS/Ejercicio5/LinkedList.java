import java.util.Scanner;

public class LinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<E> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void insert(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = newNode;
        }
        size++;
    }

    public void printList() {
        Node<E> currNode = head;
        System.out.print("LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
        System.out.println();
    }

    public boolean deleteByKey(E key) {
        Node<E> currNode = head, prev = null;

        if (currNode != null && currNode.data.equals(key)) {
            head = currNode.next;
            size--;
            return true;
        }

        while (currNode != null && !currNode.data.equals(key)) {
            prev = currNode;
            currNode = currNode.next;
        }

        if (currNode != null) {
            prev.next = currNode.next;
            size--;
            return true;
        }

        return false;
    }

    public boolean deleteAtPosition(int index) {
        if (index < 0 || index >= size) return false;

        Node<E> currNode = head, prev = null;

        if (index == 0) {
            head = currNode.next;
            size--;
            return true;
        }

        int counter = 0;
        while (currNode != null) {
            if (counter == index) {
                prev.next = currNode.next;
                size--;
                return true;
            } else {
                prev = currNode;
                currNode = currNode.next;
                counter++;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public E removeFirst() {
        if (head == null) return null;
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }

    public E removeLast() {
        if (head == null) return null;
        
        if (head.next == null) {
            E data = head.data;
            head = null;
            size--;
            return data;
        }

        Node<E> prev = null;
        Node<E> curr = head;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        
        E data = curr.data;
        prev.next = null;
        size--;
        return data;
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E data) {
        insert(data);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        
        // Llenar la lista con valores del 1 al 10
        for (int i = 1; i <= 10; i++) {
            list.insert(i);
        }

        int option;
        do {
            System.out.println("\n--- Menú de Operaciones ---");
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