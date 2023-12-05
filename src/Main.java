public class Main {
    public static void main(String[] args) {
        // 1. Очередь с приоритетом
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        // Добавление элементов
        priorityQueue.add(5);
        priorityQueue.add(3);
        priorityQueue.add(8);
        priorityQueue.add(1);
        priorityQueue.add(7);

        // Вывод содержимого очереди
        System.out.println("Очередь с приоритетом:");
        priorityQueue.printQueue();

        // Размер очереди
        System.out.println("Размер: " + priorityQueue.size());

        // Просмотр максимального элемента без удаления
        System.out.println("Верхний элемент: " + priorityQueue.peek());

        // Извлечение максимального элемента
        System.out.println("Извлечение: " + priorityQueue.poll());

        // Вывод содержимого очереди после извлечения
        System.out.println("Очередь после извлечения:");
        priorityQueue.printQueue();

        // Размер очереди после извлечения
        System.out.println("Размер после извлечения: " + priorityQueue.size());

        // Добавление нового элемента
        priorityQueue.add(10);

        // Просмотр максимального элемента после добавления
        System.out.println("Верхний элемент после добавления: " + priorityQueue.peek());

        // Вывод содержимого очереди после добавления
        System.out.println("Очередь после добавления:");
        priorityQueue.printQueue();

        // Извлечение максимального элемента после добавления
        System.out.println("Извлечение после добавления: " + priorityQueue.poll());

        // Вывод содержимого очереди после извлечения
        System.out.println("Очередь после извлечения:");
        priorityQueue.printQueue();

        // Размер очереди после извлечения
        System.out.println("Размер после извлечения: " + priorityQueue.size());

        System.out.println();
        System.out.println();

        // 2. Максимальная куча
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(20);
        maxHeap.add(8);
        maxHeap.add(15);

        // Вывод матрицы кучи
        System.out.println("\nМаксимальная куча:");
        maxHeap.printHeapMatrix();

        // Размер кучи
        System.out.println("Размер кучи: " + maxHeap.size());

        // Максимальный элемент
        System.out.println("Максимальный элемент: " + maxHeap.peek());

        // Добавление нового элемента в максимальную кучу
        maxHeap.add(18);

        // Вывод матрицы кучи после добавления элемента
        System.out.println("\nМаксимальная куча после добавления 18:");
        maxHeap.printHeapMatrix();

        // Удаление максимального элемента из кучи
        System.out.println("Извлечение из максимальной кучи: " + maxHeap.poll());

        // Вывод матрицы кучи после удаления элемента
        System.out.println("Максимальная куча после извлечения:");
        maxHeap.printHeapMatrix();

        // Размер кучи после удаления элемента
        System.out.println("Размер кучи после извлечения: " + maxHeap.size());

        // Вывод элементов кучи в пре-порядке
        System.out.println("Preorder кучи:");
        maxHeap.getRoot().preOrder().forEach(node -> System.out.print(node.getKey() + " "));
        System.out.println();

        // Максимальный элемент
        System.out.println("Максимальный элемент: " + maxHeap.peek());

        System.out.println();
        System.out.println();

        // 3. Очередь с приоритетом на основе кучи
        PriorityQueueWithHeap<Integer> priorityQueueWithHeap = new PriorityQueueWithHeap<>();

        // Добавление элементов для демонстрации
        priorityQueueWithHeap.enqueue(10);
        priorityQueueWithHeap.enqueue(5);
        priorityQueueWithHeap.enqueue(20);
        priorityQueueWithHeap.enqueue(8);

        // Вывод
        System.out.println("\nPriorityQueueWithHeap:");
        maxHeap.printHeapMatrix();

        // Удаление пары элементов и добавление новых
        maxHeap.poll();
        maxHeap.poll();

        maxHeap.add(25);
        maxHeap.add(12);

        // Вывод матрицы кучи после изменений
        System.out.println("\nPriorityQueueWithHeap после удаления пары и добавления новых элементов:");
        maxHeap.printHeapMatrix();

        // Извлечение элементов
        System.out.println("\nИзвлечение элементов из Очереди с приоритетом на основе кучи:");
        while (!priorityQueueWithHeap.isEmpty()) {
            System.out.print(priorityQueueWithHeap.dequeue() + " ");
        }

        // Вывод размера после извлечения элементов
        System.out.println("\nРазмер Очереди с приоритетом на основе кучи после извлечения: " + priorityQueueWithHeap.size());

        // Добавление элементов для анализа производительности
        for (int i = 0; i < 1000; i++) {
            priorityQueueWithHeap.enqueue(i);
        }

        // Вывод размера перед анализом производительности
        System.out.println("\nРазмер Очереди с приоритетом на основе кучи перед анализом производительности: " + priorityQueueWithHeap.size());

        // Анализ производительности
        System.out.println("\nАнализ производительности:");

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            priorityQueueWithHeap.enqueue(i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Время вставки: " + (endTime - startTime) + " мс");

        startTime = System.currentTimeMillis();
        while (!priorityQueueWithHeap.isEmpty()) {
            priorityQueueWithHeap.dequeue();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Время извлечения: " + (endTime - startTime) + " мс");

    }
}
