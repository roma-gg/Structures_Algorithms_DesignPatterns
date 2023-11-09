package Queues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Stack;

public class ArrayPriorityQueue {

    private int[] array = new int[5];
    private int count = 0;



    public void add(int element) {
        if (isFull()) {
            int[] newArray = new int[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }

        if (isEmpty()) {
            array[0] = element;
            count++;
        }
        else {
            for (int i = count++ - 1; i >= 0; i--) {
                if (element <= array[i]) {
                    array[i + 1] = array[i];
                    if (i == 0)
                        array[0] = element;
                }
                else {
                    array[i + 1] = element;
                    break;
                }
            }
        }
    }

    public void reverseToNth(int nth) {
        Stack<Integer> stack = new Stack<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < array.length; i++) {
            if (i < nth)
                stack.push(array[i]);
            else
                deque.add(array[i]);
        }

        for (int i = 0; i < array.length; i++) {
            if (i < nth)
                array[i] = stack.pop();
            else
                array[i] = deque.remove();
        }
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
