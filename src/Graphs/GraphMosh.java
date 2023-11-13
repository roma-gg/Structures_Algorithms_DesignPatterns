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

    public List<String> topologicalSort() {
        var list = new ArrayList<String>();
        var stack = new Stack<String>();
        var visited = new HashSet<Node>();

        for (var each : nodes.values()) {
            topologicalSort(each, stack, visited);
        }

        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }

        return list;

    }

    private void topologicalSort(Node node, Stack<String> stack, HashSet<Node> visited) {
        if (visited.contains(node))
            return;

        visited.add(node);

        for (var each : adjacencyList.get(node)) {
            topologicalSort(each, stack, visited);
        }

        stack.push(node.label);

    }

    public boolean hasCycle() {
        var visiting = new HashSet<Node>();
        var visited = new HashSet<Node>();
        var all = new HashSet<Node>(nodes.values());

        var temp = Set.copyOf(all);
        for (var each : temp) {
            hasCycle(each, all, visiting, visited);
        }

        return !visiting.isEmpty();
    }

    private void hasCycle(Node node, HashSet<Node> all, HashSet<Node> visiting, HashSet<Node> visited) {
        //if node is leaf, removing from all (in case if node is root) and from visiting;
        if (adjacencyList.get(node).isEmpty()) {
            all.remove(node);
            visiting.remove(node);
            visited.add(node);
        }

        //if node is already in visiting - it's a cycle;
        if (visiting.contains(node))
            return;
        //if node is in all - it's a first touch, moving to visiting and checking children;
        if (all.contains(node)) {
            visiting.add(node);
            all.remove(node);
            for (var each : adjacencyList.get(node)) {
                hasCycle(each, all, visiting, visited);
            }
        }

        //after checking children - if all are visited - node is visited as well, if not - there is a cycle;
        if (visited.containsAll(adjacencyList.get(node))) {
            visiting.remove(node);
            visited.add(node);
        }
    }

    public boolean hasCycleMosh() {
        var visiting = new HashSet<Node>();
        var visited = new HashSet<Node>();
        var all = new HashSet<Node>(nodes.values());

        while(!all.isEmpty()) {
            var current = (Node)all.toArray()[0];
            if (hasCycleMosh(current, all, visiting, visited))
                return true;
        }
        return false;
    }

    private boolean hasCycleMosh(Node node, HashSet<Node> all, HashSet<Node> visiting, HashSet<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (var each : adjacencyList.get(node)) {
            if (visited.contains(node))
                continue;

            if (visiting.contains(each))
                return true;

            if (hasCycleMosh(each, all, visiting, visited))
                return true;

        }

        visiting.remove(node);
        visited.remove(node);

        return false;
    }

    public void print() {
        for (var source : adjacencyList.keySet()) {
            var targets = adjacencyList.get(source);
            if (!targets.isEmpty())
                System.out.println(source + " is connected to " + targets);
        }
    }


}
