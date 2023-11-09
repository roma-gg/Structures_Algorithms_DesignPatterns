package Queues;

import java.util.Arrays;
import java.util.Stack;

// 10, 20, 30, 40, 50

public class StackQueue {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> tempStack = new Stack<>();

    public void enqueue(int element) {
        stack.push(element);
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();

        moveStackToTempStack();
        var removed = tempStack.pop();
        moveTempStackToStack();
        return removed;
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();

        moveStackToTempStack();
        var peeked = tempStack.peek();
        moveTempStackToStack();
        return peeked;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    private void moveStackToTempStack() {
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
    }

    private void moveTempStackToStack() {
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    @Override
    public String toString() {
        return String.valueOf(stack);
    }
}
