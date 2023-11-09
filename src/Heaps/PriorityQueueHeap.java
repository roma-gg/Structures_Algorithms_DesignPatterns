package Heaps;

public class PriorityQueueHeap {
    MinHeap minHeap = new MinHeap();

    public void add(String value, int priority) {
        minHeap.insert(priority, value);
    }

    public String remove() {
        return minHeap.remove();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }
}
