package Heaps;

import java.util.Arrays;

public class MinHeap {
    private Node[] nodes = new Node[10];
    private int size;

    @Override
    public String toString() {
        var array = Arrays.copyOf(nodes,size);
        return Arrays.toString(array);
    }

    private class Node {
        private int key;
        private String value;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public void insert(int key, String value) {
        if (isFull())
            throw new IllegalStateException();

        nodes[size++] = new Node(key, value);

        bubbleUp();
    }

    public String remove() {
        if (isEmpty())
            throw new IllegalStateException();

        var removed = nodes[0].value;
        nodes[0] = nodes[--size];
        bubbleDown();

        return removed;
    }

    private void bubbleUp() {
        var index = size - 1;
        while (index > 0 &&
                nodes[index].key < nodes[parentIndex(index)].key) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void bubbleDown() {
       var index = 0;
       while (index < size && !isValidParent(index)) {
           var smallerChild = smallerChildIndex(index);
           swap(index, smallerChild);
           index = smallerChild;
       }
    }

    private boolean isValidParent(int index) {
        if (isLeaf(index))
            return true;

        var parentKey = nodes[index].key;
        var rightChildIndex = index * 2 + 2;
        var leftChildKey = nodes[index * 2 + 1].key;

        if (!hasRightChild(index)) {
            return parentKey <= leftChildKey;
        }

        var rightChildKey = nodes[rightChildIndex].key;
        return parentKey <= leftChildKey &&
                parentKey <= rightChildKey;
    }

    private boolean hasLeftChild(int index) {
        return nodes[index * 2 + 1] != null && index * 2 + 1 < size;
    }

    private boolean hasRightChild(int index) {
        return nodes[index * 2 + 2] != null && index * 2 + 2 < size;
    }

    private int smallerChildIndex(int index) {
        if (!hasLeftChild(index))
            return index;

        if (!hasRightChild(index))
            return leftChildIndex(index);

        return (leftChild(index).key <= rightChild(index).key) ?
                leftChildIndex(index) :
                rightChildIndex(index);
    }

    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }

    private int parentIndex (int index) {
        return (index - 1) / 2;
    }

    private Node leftChild(int index) {
        return nodes[leftChildIndex(index)];
    }

    private Node rightChild(int index) {
        return nodes[rightChildIndex(index)];
    }

    private void swap (int firstIndex, int secondIndex) {
        var temp = nodes[firstIndex];
        nodes[firstIndex] = nodes[secondIndex];
        nodes[secondIndex] = temp;
    }

    private boolean isLeaf(int index) {
        var leftChildIndex = index * 2 + 1;
        var rightChildIndex = index * 2 + 2;

        return leftChildIndex >= size && rightChildIndex >= size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return nodes.length == size;
    }
}
