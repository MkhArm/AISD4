public class Main {
    public static void main(String[] args) {

        // 1 задание

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Добавление элементов
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(8);
        priorityQueue.add(1);
        priorityQueue.add(7);

        priorityQueue.printQueue();

        // Размер очереди
        System.out.println("Size: " + priorityQueue.size());

        // Просмотр максимального элемента без удаления
        System.out.println("Peek: " + priorityQueue.peek());

        // Извлечение максимального элемента
        System.out.println("Poll: " + priorityQueue.poll());

        priorityQueue.printQueue();

        // Размер очереди после извлечения
        System.out.println("Size after poll: " + priorityQueue.size());

        // Добавление нового элемента
        priorityQueue.add(10);

        // Просмотр максимального элемента после добавления
        System.out.println("Peek after add: " + priorityQueue.peek());

        priorityQueue.printQueue();

        // Извлечение максимального элемента после добавления
        System.out.println("Poll after add: " + priorityQueue.poll());

        priorityQueue.printQueue();

        // Размер очереди после извлечения
        System.out.println("Size after poll: " + priorityQueue.size());


        // 2 задание

        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        maxHeap.add(8);
        maxHeap.add(15);

        maxHeap.printHeapMatrix();

        System.out.println("Max Heap Size: " + maxHeap.size());
        System.out.println("Max Element: " + maxHeap.peek());

        System.out.println("Heap in Pre-order:");
        maxHeap.getRoot().preOrder().forEach(node -> System.out.print(node.getKey() + " "));
    }
}
