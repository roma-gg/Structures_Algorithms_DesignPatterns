package BinaryTrees;

import java.util.ArrayList;

public class Tree {
    private Node root;
    private int size = 0;

    public int size() {
        return size;
    }

    public void insert(int value) {

        if (find(value)) {
            return;
        }

        var node = new Node(value);
        size++;

        if (isEmpty()) {
            root = node;
            return;
        }


        var current = root;
        while (current != null) {
            if (current.value > value) {
                if (current.leftChild == null) {
                    current.leftChild = node;
                    break;
                }
                else
                    current = current.leftChild;
            }
            else
                if (current.rightChild == null) {
                    current.rightChild = node;
                    break;
                }
                else
                    current = current.rightChild;
        }

    }

    public boolean find(int value) {
        var current = root;
        while (current != null) {
            if (current.value == value)
                return true;
            else if (current.value > value)
                current = current.leftChild;
            else
                current = current.rightChild;
        }
        return false;
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(Node node, int value) {
        if (node == null)
            return false;

        if (node.value == value) {
            return true;
        }

        return contains(node.leftChild, value) || contains(node.rightChild, value);
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    public void traversePreOrder(Node root) {
        if (root == null)
            return;

        System.out.println(root.value);
        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
    }


    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public void traverseInOrder(Node root) {
        if (root == null)
            return;

        traversePreOrder(root.leftChild);
        System.out.println(root.value);
        traversePreOrder(root.rightChild);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }

    public void traversePostOrder(Node root) {
        if (root == null)
            return;

        traversePreOrder(root.leftChild);
        traversePreOrder(root.rightChild);
        System.out.println(root.value);
    }

    private int height(Node root) {
        if (isEmpty())
            return -1;

        if (root == null || isLeaf(root))
            return 0;

        return 1 + Math.max(height(root.leftChild), height(root.rightChild));
    }

    public int min() {
        return min(root);
    }

    private int min(Node root) {
        int right = 0;
        int left = 0;

        if (isLeaf(root)) {
            return root.value;
        }
        else if (root.leftChild == null)
            right = left = min(root.rightChild);
        else if (root.rightChild == null)
            right = left = min(root.leftChild);
        else {
            left = min(root.leftChild);
            right = min(root.rightChild);
        }

        return Math.min(Math.min(left,right), root.value);
    }

    public boolean equals(Tree tree) {
        if (tree == null)
            throw new IllegalStateException();

        return equals(this.root, tree.root);
    }

    private boolean equals(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;
        else if (node1 == null && node2 != null)
            return false;
        else if (node1 != null && node2 == null)
            return false;

        if (node1.value == node2.value) {
            return equals(node1.leftChild, node2.leftChild) &&
                    equals(node1.rightChild, node2.rightChild);
        }
        return false;

    }

    public boolean isBinarySearchTree() {
        if (root == null)
            throw  new IllegalStateException();

        return isBinarySearchTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBinarySearchTree(Node node, int min, int max) {
        if (node == null)
            return true;

        if (node.value > min && node.value < max) {
            return isBinarySearchTree(node.leftChild, min, node.value) &&
                    isBinarySearchTree(node.rightChild, node.value, max);
        }
        else
            return false;
    }


    public void swapRoot() {
        var temp = root.rightChild;
        root.rightChild = root.leftChild;
        root.leftChild = temp;
    }

    public ArrayList<Integer> nodesAtKthDistance(int kth) {
        var list = new ArrayList<Integer>();
        if (kth < 0)
            throw new IllegalArgumentException();
        return nodesAtKthDistance(root, kth, list);
    }

    private ArrayList<Integer> nodesAtKthDistance (Node node, int kth, ArrayList<Integer> list) {

        if (kth == 0) {
            list.add(node.value);
        }
        if (node.leftChild != null)
            nodesAtKthDistance(node.leftChild, kth - 1, list);
        if (node.rightChild != null)
            nodesAtKthDistance(node.rightChild, kth - 1, list);

        return list;
    }

    public void traverseLevelOrder() {
        var list = new ArrayList<Integer>();
        for (int i = 0; i <= height(root); i++) {
            list.addAll(nodesAtKthDistance(i));
        }
        System.out.println(list);
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;

        if (isLeaf(node))
            return 1;

        return countLeaves(node.leftChild) + countLeaves(node.rightChild);
    }

    public int maxValue() {
        if (root == null)
            throw new IllegalStateException();
        return maxValue(root);
    }

    private int maxValue(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        if (isLeaf(node))
            return node.value;

        return Math.max(
                Math.max(
                        maxValue(node.leftChild),
                        maxValue(node.rightChild)),
                node.value);
    }

    public boolean areSiblings (int value1, int value2) {
        return areSiblings(root, value1, value2);
    }

    private boolean areSiblings (Node node, int value1, int value2) {
        if (node == null)
            return false;

        if (isLeaf(node))
            return false;

        if (isWithBothChildren(node)) {
            if ((node.leftChild.value == value1 && node.rightChild.value == value2) ||
                    (node.leftChild.value == value2 && node.rightChild.value == value1))
                return true;
        }

        return areSiblings(node.leftChild, value1, value2) ||
                areSiblings(node.rightChild, value1, value2);
    }

    public boolean isWithBothChildren(Node node) {
        return node.leftChild != null && node.rightChild != null;
    }



    private boolean isLeaf(Node node) {
        return node.leftChild == null && node.rightChild == null;
    }

    public boolean isEmpty() {
        return root == null;
    }




    private class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node=" + value;
        }
    }
}
