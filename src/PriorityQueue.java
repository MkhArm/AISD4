public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        Node(E value) {
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            Node<E> current = head;
            while (current != null && element.compareTo(current.value) <= 0) {
                current = current.next;
            }

            if (current == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.value;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return value;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void printQueue() {
        Node<E> current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
