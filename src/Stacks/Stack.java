package Stacks;

import java.util.Arrays;

public class Stack {
    int[] stack = new int[10];
    private int count = 0;

    public void push(int num) {
        if (stack.length == count) {
            int[] stack2 = new int[stack.length * 2];

            for (int i = 0; i < stack.length; i++)
                stack2[i] = stack[i];

            stack = stack2;
        }

        stack[count++] = num;
    }

    public int pop() {
        if (isEmpty())
            throw new IllegalStateException();

        var last = stack[--count];
        stack[count] = 0;
        return last;
    }

    public int peek() {
        if (isEmpty())
            throw new IllegalStateException();
        return stack[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private int min;
    public int min() {
        if (isEmpty())
            throw new IllegalStateException();
        min = stack[0];
        for (int i = 0; i < count; i++) {
            if (stack[i] < min) {
                min = stack[i];
            }
        }
        return min;
    }

    int indexOfMin = 0;
    public void popMin() {
        if (isEmpty())
            throw new IllegalStateException();

        min = stack[0];
        for (int i = 1; i < count; i++) {
            if (stack[i] < min) {
                min = stack[i];
                indexOfMin = i;
            }
        }

        for (int i = indexOfMin; i < count - 1; i++) {
            stack[i] = stack[i + 1];
        }
        stack[--count] = 0;
    }

    @Override
    public String toString() {
        var content = Arrays.copyOfRange(stack, 0, count);
        return Arrays.toString(content);
    }
}
