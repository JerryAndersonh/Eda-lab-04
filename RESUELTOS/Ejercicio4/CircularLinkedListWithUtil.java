import java.util.LinkedList;

public class CircularLinkedListWithUtil {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i);
        }
        // Simular comportamiento circular
        list.addFirst(list.removeLast());
        System.out.println(list);
    }
}