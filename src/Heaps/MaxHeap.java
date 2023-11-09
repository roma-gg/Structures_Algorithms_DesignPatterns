package Heaps;

import java.util.Arrays;

public class MaxHeap {
    private int[] array = new int[10];
    private int size = 0;

    public void insert (int value) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        array[size] = value;
        var indexOfNewValue = size;
        while (indexOfNewValue > 0 && isParentLower(indexOfNewValue)) {
            bubbleUp(indexOfNewValue);
            indexOfNewValue = indexOfParent(indexOfNewValue);
        }

        size++;
    }

    public int remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var removed = array[0];
        array[0] = array[--size];
        array[size] = 0;

        var indexOfLastValue = 0;
        while (indexOfLastValue <= size && (
                isLeftChildHigher(indexOfLastValue) ||
                isRightChildHigher(indexOfLastValue))) {

            if (isLeftHigherThanRight(indexOfLastValue)) {
                bubbleDownLeft(indexOfLastValue);
                indexOfLastValue = indexOfLeftChild(indexOfLastValue);
            }
            else {
                bubbleDownRight(indexOfLastValue);
                indexOfLastValue = indexOfRightChild(indexOfLastValue);
            }
        }

        return removed;
    }

    public void heapify(int[] array) {
        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    private boolean isLeftChildHigher(int parentIndex) {
        if (indexOfLeftChild(parentIndex) >= size)
            return false;
        return array[parentIndex] < array[indexOfLeftChild(parentIndex)];
    }

    private boolean isRightChildHigher(int parentIndex) {
        if (indexOfRightChild(parentIndex) >= size)
            return false;
        return array[parentIndex] < array[indexOfRightChild(parentIndex)];
    }

    private boolean isLeftHigherThanRight(int index) {
        if (indexOfRightChild(index) >= size)
            return true;
        return array[indexOfLeftChild(index)] >= array[indexOfRightChild(index)];
    }

    private boolean isParentLower(int childIndex) {
        return array[childIndex] > array[indexOfParent(childIndex)];
    }

    private void bubbleUp(int indexOfChild) {
        var temp = array[indexOfChild];
        array[indexOfChild] = array[indexOfParent(indexOfChild)];
        array[indexOfParent(indexOfChild)] = temp;
    }

    private void bubbleDownLeft(int indexOfParent) {
        var temp = array[indexOfLeftChild(indexOfParent)];
        array[indexOfLeftChild(indexOfParent)] = array[indexOfParent];
        array[indexOfParent] = temp;
    }

    private void bubbleDownRight(int indexOfParent) {
        var temp = array[indexOfRightChild(indexOfParent)];
        array[indexOfRightChild(indexOfParent)] = array[indexOfParent];
        array[indexOfParent] = temp;
    }

    private int indexOfParent(int index) {
        return (index - 1) /2;
    }

    private int indexOfLeftChild(int index) {
        return index * 2 + 1;
    }

    private int indexOfRightChild(int index) {
        return index * 2 + 2;
    }

    public boolean isFull() {
        return array.length == size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int max() {
        if (isEmpty())
            throw new IllegalStateException();

        return array[0];
    }

    public int kthHighest(int[]array, int kth) {
        var heap = new MaxHeap();
        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }
        for (int i = 0; i < kth - 1; i++) {
            heap.remove();
        }
        return heap.max();
    }

    public static boolean isMaxHeap(int[] array) {
        var heap = new MaxHeap();
        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }
        var heapArray = Arrays.copyOf(heap.array, heap.size);
        return Arrays.equals(array, heapArray);
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, size));
    }
}
