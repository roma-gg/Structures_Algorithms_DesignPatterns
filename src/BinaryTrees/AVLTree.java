package BinaryTrees;

public class AVLTree {
    private AVLNode root;


    private class AVLNode {
        int value;
        private AVLNode leftChild;
        private AVLNode rightChild;
        private int height;

        public AVLNode(int value) {
            this.value = value;
        }

    }

    public int height() {
        return root.height;
    }

    public void insert(int value) {
        if (find(root, value))
            return;

        root = insert(root, value);
    }

    private AVLNode insert(AVLNode node, int value) {

        if (node == null) {
            var newNode = new AVLNode(value);
            newNode.height = 0;
            return newNode;
        }

        if (node.value > value)
            node.leftChild = insert(node.leftChild, value);
        else
            node.rightChild = insert(node.rightChild, value);

        node.height = Math.max(height(node.leftChild), height(node.rightChild)) + 1;

        return balance(node);
    }

    private AVLNode balance (AVLNode node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.leftChild) < 0)
                node.rightChild = leftRotation(node.leftChild);
            return rightRotation(node);
        }
        else if (isRightHeavy(node)) {
            if (balanceFactor(node.rightChild) > 0)
                node.rightChild = rightRotation(node.rightChild);
            return leftRotation(node);
        }
        return node;
    }

    private AVLNode leftRotation(AVLNode node) {
        var newRoot = node.rightChild;
        node.rightChild = newRoot.leftChild;
        newRoot.leftChild = node;

        recalcHeight(node);
        recalcHeight(newRoot);

        return newRoot;
    }

    private AVLNode rightRotation(AVLNode node) {
        var newRoot = node.leftChild;
        node.leftChild = newRoot.rightChild;
        newRoot.rightChild = node;

        recalcHeight(node);
        recalcHeight(newRoot);

        return newRoot;
    }

    private boolean isLeftHeavy(AVLNode node) {
        return balanceFactor(node) > 1;
    }

    private boolean isRightHeavy(AVLNode node) {
        return balanceFactor(node) < -1;
    }

    private int balanceFactor(AVLNode node) {
        return height(node.leftChild) - height(node.rightChild);
    }

    private int height(AVLNode node) {
        return  (node == null) ? -1 :  node.height;
    }

    private void recalcHeight(AVLNode node) {
        node.height = calcHeight(node);
    }

    private int calcHeight(AVLNode node) {
        if (node == null)
            return -1;
        return Math.max(calcHeight(node.leftChild), calcHeight(node.rightChild)) + 1;
    }

    public boolean find(int value) {
        return find(root, value);
    }

    //        10
    //      5    15
    //     3 7  12 17
    //    1   9
    //       8

    public boolean isBalanced() {
        return isBalanced(root) <= 1 &&
                isBalanced(root) >= -1;

    }

    private int isBalanced(AVLNode node) {
        if (node == null)
            return -1;

        return isBalanced(node.leftChild) - isBalanced(node.rightChild);
    }

    public boolean isPerfect() {
        return isPerfect(root);
    }

    private boolean isPerfect(AVLNode node) {
        if (node == null)
            return false;
        if (isLeaf(node))
            return true;

        return isPerfect(node.leftChild) && isPerfect(node.rightChild);
    }

    private boolean find(AVLNode node, int value) {
        if (node == null)
            return false;

        if (node.value == value)
            return true;

        return find(node.leftChild, value) || find(node.rightChild, value);
    }

    private boolean isLeaf(AVLNode node) {
        return node.leftChild == null && node.rightChild == null;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
