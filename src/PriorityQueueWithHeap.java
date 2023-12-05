public class PriorityQueueWithHeap<E extends Comparable<E>> {
    private MaxHeap<E> maxHeap;

    public PriorityQueueWithHeap() {
        this.maxHeap = new MaxHeap<>();
    }

    public void enqueue(E element) {
        maxHeap.add(element);
    }

    public E dequeue() {
        return maxHeap.poll();
    }

    public int size() {
        return maxHeap.size();
    }

    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

}
