package Queues;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class QueueDemo {



    public void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.remove());
        }

        Queue<Integer> queueReversed = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            queueReversed.add(stack.pop());
        }

        System.out.println(queueReversed);
    }



    // queque 10, 20, 30
    // temp   10, 20, 30
}
