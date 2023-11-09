package Queues;

import java.util.ArrayDeque;

public class StackWithTwoQueues {

    ArrayDeque<Integer> queue1 = new ArrayDeque<>();
    ArrayDeque<Integer> queue2 = new ArrayDeque<>();

    public void push (int element) {
        queue1.add(element);
    }

    public int pop() {
        if (queue2.isEmpty()) {
            var size = queue1.size();
            for (int i = 0; i < size - 1; i++) {
                queue2.add(queue1.remove());
            }
            var removed = queue1.remove();
            return removed;
        }
        else {
            var size = queue2.size();
            for (int i = 0; i < size - 1; i++) {
                queue1.add(queue2.remove());
            }
            var removed = queue2.remove();
            return removed;
        }
    }

    public int peek() {
        if (queue2.isEmpty())
            return queue1.peek();
        else
            return queue2.peek();
    }

    public int size() {
        if (queue2.isEmpty())
            return queue1.size();
        else
            return queue2.size();
    }

    public boolean isEmpty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }

    @Override
    public String toString() {
        if (queue2.isEmpty())
            return queue1.toString();
        else
            return queue2.toString();
    }
}
