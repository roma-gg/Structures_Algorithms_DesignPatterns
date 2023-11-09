package LinkedList;

import java.util.NoSuchElementException;

public class LinkedListDemo<T> {

    private class Node<T> {
        private T value;
        private Node next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    private Node first;
    private Node last;
    private int size = 0;

    public int size(){
        return size;
    }

    public void addFirst(T value) {
        var newNode = new Node<>(value);
        if (isEmpty()) {
            last = newNode;
        }
        else {
            newNode.next = first;
        }
        first = newNode;
        size++;
    }

    public void addLast(T value) {
        var newNode = new Node<>(value);
        if (isEmpty()) {
            first = newNode;
        }
        else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public void deleteFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (first == last) {
            first = last = null;
            size--;
        }
        else {
            var secondNode = first.next;
            first.next = null;
            first = secondNode;
            size--;
        }
    }

    public void deleteLast () {
        if (isEmpty())
            throw new NoSuchElementException();
        else if (first == last) {
            first = last = null;
            size--;
        }
        else {
            var preLastNode = first;
            while (preLastNode.next.next != null) {
                preLastNode = preLastNode.next;
            }
            preLastNode.next = null;
            last = preLastNode;
            size--;
        }

    }

    public boolean contains (T value) {
        return indexOf(value) != -1;
    }

    public int indexOf (T value) {
        int index = 0;
        var nodeToCheck = first;

        while (nodeToCheck != null) {
            if (nodeToCheck.value == value ||
                    nodeToCheck.value.equals(value))
                return index;
            nodeToCheck = nodeToCheck.next;
            index++;
        }
        return -1;
    }

    public void print() {
        var nodeToCheck = first;
        System.out.print("[");
        while (nodeToCheck.next != null) {
            System.out.print(nodeToCheck.value + ", ");
            nodeToCheck = nodeToCheck.next;
        }
        System.out.print(nodeToCheck.value + "]");
        System.out.println();
    }

    public void reverse() {
        if (isEmpty())
            return;
        else {
            var current = first;
            var current2 = first.getNext();
            while (current2 != null) {
                var current3 = current2.getNext();
                current2.setNext(current);
                current = current2;
                current2 = current3;
            }

            last = first;
            last.setNext(null);
            first = current;
        }
    }

    public T getKthFromTheEnd(int K) {
        if (isEmpty())
            throw new IllegalStateException();

        var pointer1 = first;
        var pointer2 = first;
        int count = 1;
        if (K > 0 && K <= size) {
            while (count < K) {
                pointer2 = pointer2.getNext();
                count++;
            }

            while (pointer2.getNext() != null) {
                pointer1 = pointer1.getNext();
                pointer2 = pointer2.getNext();
            }

            return (T) pointer1.getValue();
        }
        else
            throw new NoSuchElementException();

    }

    public void printMiddle() {
        if (isEmpty())
            throw new IllegalStateException();

        var pointer1 = first;
        var pointer2 = first.getNext();

        while (pointer2 != null && pointer2.getNext() != null) {
            pointer1 = pointer1.getNext();
            pointer2 = pointer2.getNext().getNext();
        }

        if (pointer2 == null)
            System.out.println(pointer1.getValue());
        else
            System.out.println(pointer1.getValue() + ", " + pointer1.getNext().getValue());

    }

    public boolean hasLoop() {
        //if quantity of nodes in linked list is even
        var pointer1 = first;
        var pointer2 = first;
        while (pointer2 != null) {
            pointer1 = pointer1.getNext();
            pointer2 = pointer2.getNext().getNext();
            if (pointer2 == pointer1)
                return true;
        }
        return false;
    }


    private boolean isEmpty() {
        return first == null;
    }
}








