package Tries;

import java.util.*;

public class Trie {
    private class Node {
        public static int ALPHABET_SIZE = 26;
        private char ch;
        private HashMap<Character, Node> children = new HashMap<>();
        private boolean isEndOfWord;

        public Node(char ch) {
            this.ch = ch;
        }

        @Override
        public String toString() {
            return "ch=" + ch;
        }

        private boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        private void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        private Node getChild(char ch) {
            return children.get(ch);
        }

        private boolean isLeaf() {
            return children.isEmpty();
        }

        private void removeChild(char ch) {
            children.remove(ch);
        }

        private Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }
    }

    private Node root = new Node(' ');

    public void insert(String word) {
        insert(word, 0, root);
    }

    private void insert(String word, int indexOfWord, Node current) {
        char ch = word.charAt(indexOfWord);
        if (!current.hasChild(ch)) {
            current.addChild(ch);
        }

        if (indexOfWord == word.length() - 1) {
            current.getChild(ch).isEndOfWord = true;
            return;
        }

        insert(word, indexOfWord + 1, current.getChild(ch));
    }

    public boolean contains(String word) {
        return contains(word, root);
    }

    private boolean contains(String word, Node current) {
        if (word == null)
            return false;

        char ch = word.charAt(0);
        if (!current.hasChild(ch))
            return false;

        if (word.length() == 1 && !current.getChild(ch).isEndOfWord)
            return false;
        else if (word.length() == 1)
            return true;

        return contains(word.substring(1), current.getChild(ch));
    }

    public void remove(String word) {
        if (word == null || word.isEmpty())
            return;
        remove(word, root);
    }

    private void remove (String word, Node node) {
        if (node.isLeaf())
            return;

        char ch = word.charAt(0);
        var child = node.getChild(ch);
        if (word.length() > 1)
            remove (word.substring(1), child);

        if (word.length() == 1)
            child.isEndOfWord = false;

        if (child.isLeaf() && !child.isEndOfWord)
            node.removeChild(ch);
    }

    public List<String> findWords(String word) {
        var words = new ArrayList<String>();

        var current = findLastNodeOf(word);
        if (current == null)
            return words;

        findWords(word, current, words);
        return words;
    }

    private Node findLastNodeOf(String prefix) {
        var current = root;
        for (var each : prefix.toCharArray()) {
            current = current.getChild(each);
            if (current == null)
                return current;
        }

        return current;
    }

    private void findWords(String word, Node node, List<String> words) {
        if (node.isEndOfWord) {
            words.add(word);
        }
        if (node.isLeaf()) {
            return;
        }

        for (var each : node.children.keySet()) {
            String tempWord = word + each;
            findWords(tempWord, node.getChild(each), words);
        }
    }

    public int countWords() {
        if (root == null)
            return 0;

        return countWords(root);
    }

    private int countWords(Node node) {
        int sum = 0;
        if (node.isEndOfWord)
            sum = 1;

        for (var each : node.getChildren()) {
            sum += countWords(each);
        }

        return sum;
    }

    public static String longestCommonPrefix (String[] words) {
        if (words == null)
            return "";

        var trie = new Trie();
        for (var each : words)
            trie.insert(each);

        return longestCommonPrefix(trie.root, "");
    }

    private static String longestCommonPrefix (Node node, String prefix) {
        if (node.getChildren().length != 1 || node.isEndOfWord)
            return prefix;

        Node child = null;
        String appendedPrefix = prefix;
        for (var each : node.getChildren()) {
            child = each;
            appendedPrefix += each.ch;
        }

        return longestCommonPrefix(child, appendedPrefix);

    }







}


