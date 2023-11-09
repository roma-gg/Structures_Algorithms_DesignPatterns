package Queues;

import java.util.Arrays;

public class ArrayQueue {
    private int[] array = new int[5];
    private int indexOfFirst = 0;
    private int indexOfLast = 0;
    int count = 0;

    public void enqueue(int element) {
        if (isFull()) {
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[indexOfLast] = element;
        indexOfLast = (indexOfLast + 1) % array.length;
        count++;
    }

    public int dequeue() {
        if (isEmpty())
            throw new IllegalStateException();
        else {
            var item = array[indexOfFirst];
            array[indexOfFirst] = 0;
            indexOfFirst = (indexOfFirst + 1) % array.length;
            count--;
            return item;
        }
    }

    public int peek() {
        return array[indexOfFirst];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public void print() {
        System.out.println(Arrays.toString(Arrays.copyOfRange(array, indexOfFirst, indexOfLast)));
    }


    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
