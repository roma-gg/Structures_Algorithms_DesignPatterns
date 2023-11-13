package Graphs;

import java.util.*;

public class GraphMosh {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge (String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            throw new IllegalArgumentException();

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String label) {
        var node = nodes.get(label);
        if (node == null)
            return;

        nodes.remove(label);
        adjacencyList.remove(node);
        for (var each: adjacencyList.values()) {
            each.remove(node);
        }
    }

    public void removeEdge(String from, String to) {
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);
        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " + targets);
        }
    }

    public HashSet<String> traverseDFSRec(String label) {
        if (label == null)
            throw new IllegalArgumentException();

        var list = new HashSet<String>();
        if (!nodes.containsKey(label)) {
            return list;
        }

        var node = nodes.get(label);
        traverseDFSRec(node, list);

        return list;
    }

    private void traverseDFSRec(Node node, HashSet<String> list) {
        list.add(node.label);
        System.out.println(node.label);

        for (var each : adjacencyList.get(node)) {
            if (!list.contains(each.label)) {
                traverseDFSRec(each, list);
            }
        }
    }

    public void traverseDFSIter(String label) {
        var node = nodes.get(label);
        if (node == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.add(node);

        Set<Node> set = new HashSet<>();

        while (!stack.isEmpty()) {
            var current = stack.pop();
            set.add(current);
            System.out.println(current);

            for (var each : adjacencyList.get(current)) {
                if (!set.contains(each))
                    stack.add(each);
            }
        }
    }

    public void traverseBFSIter(String label) {
        var node = nodes.get(label);
        if (node == null)
            return;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        Set<Node> set = new HashSet<>();
        while (!queue.isEmpty()) {
            var current = queue.remove();
            set.add(current);
            System.out.println(current);

            for (var each : adjacencyList.get(current)) {
                if (!set.contains(each))
                    queue.add(each);
            }
        }
    }
}
