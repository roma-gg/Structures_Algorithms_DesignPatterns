package Queues;

import java.util.LinkedList;

public class LinkedListQueue {
    LinkedList<Integer> linkedList = new LinkedList<>();

    //O(1)
    public void enqueue(int element) {
        linkedList.add(element);
    }

    //O(1)
    public int dequeue(int element) {
        var result = linkedList.removeFirst();
        return result;
    }

    //O(1)
    public int peek() {
        return linkedList.getFirst();
    }

    //O(1)
    public int size() {
        return linkedList.size();
    }

    //O(1)
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
