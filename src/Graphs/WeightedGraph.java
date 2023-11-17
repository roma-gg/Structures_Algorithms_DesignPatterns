package Graphs;

import java.util.*;

public class WeightedGraph {

    private class Node {
        private String label;
        private List<Edge> edges = new ArrayList<>();

        public Node(String label) {
            this.label = label;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Node to, int weight) {
            var edge = new Edge(this, to, weight);
            edges.add(edge);
        }

        @Override
        public String toString() {
            return label;
        }

    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;
        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + "->" + to;
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();

    public void addNode(String label) {
        nodes.putIfAbsent(label, new Node(label));
    }

    public void addEdge(String from, String to, int weight) {
        if (nodes.get(from) == null || nodes.get(to) == null)
            return;

        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        var edge = new Edge(fromNode, toNode, weight);
        var edgeReversed = new Edge(toNode, fromNode, weight);
        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public Map<ArrayList<String>, Integer> getShortestPath(String from, String to) {
        var distances = new HashMap<Node, Integer>();
        for (var node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        var fromNode = nodes.get(from);
        distances.put(fromNode, 0);

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparing(m -> m.priority));
        var previousNode = new HashMap<Node, Node>();

        getShortestDistance(fromNode, distances, previousNode, queue);

        var shortestPath = getShortestPath(from, to, distances, previousNode);
        var shortestDistance = distances.get(nodes.get(to));
        var result = new HashMap<ArrayList<String>, Integer>();
        result.put(shortestPath, shortestDistance);

        return result;
    }

    private void getShortestDistance(Node current, Map<Node, Integer> distances,
                                     Map<Node, Node> previousNode, PriorityQueue<NodeEntry> queue) {
        for (var edge : current.getEdges()) {
            Node child = edge.to;
            int distanceToChild = edge.weight + distances.get(current);

            if (!previousNode.containsValue(child) && distances.get(child) > distanceToChild) {
                distances.put(child, distanceToChild);
                previousNode.put(child, current);
                queue.add(new NodeEntry(child, distanceToChild));
            }
        }
        while (!queue.isEmpty()) {
            getShortestDistance(queue.remove().node, distances, previousNode, queue);
        }
    }

    private ArrayList<String> getShortestPath(String from, String to, Map<Node, Integer> distances,Map<Node, Node> previousNode) {
        if (from == null || to == null || !nodes.containsKey(from) || !nodes.containsKey(to))
            throw new IllegalArgumentException();

        var current = nodes.get(to);
        var fromNode = nodes.get(from);
        var shortestPath = new ArrayList<String>();
        while (current != null) {
            shortestPath.add(0, current.label);
            current = previousNode.get(current);
        }

        return shortestPath;
    }

    public boolean hasCycle() {
        var visited = new HashMap<String, Node>();
        for (var parent : nodes.values()) {
            if (!visited.containsKey(parent.label)) {
                if (hasCycle(parent, parent, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean hasCycle (Node current, Node previous, Map<String, Node> visited) {
        visited.put(current.label, current);
        for (var edge : current.getEdges()) {
            var child = edge.to;
            if (child.label.equals(previous.label))
                continue;
            if (visited.containsKey(child.label))
                return true;
            if (hasCycle(child, current, visited))
                return true;
        }
        return false;
    }

    public WeightedGraph getMinSpanningTree() {
        var minSpanningTree = new WeightedGraph();
        var start = nodes.values().iterator().next();
        minSpanningTree.addNode(start.label);

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparing(m -> m.priority));
        getMinSpanningTree(start, minSpanningTree, queue);

        return minSpanningTree;
    }

    private void getMinSpanningTree(Node node, WeightedGraph graph, PriorityQueue<NodeEntry> queue) {
        //adding all unvisited childs to the queue
        for (var edge : node.getEdges()) {
            var child = edge.to;
            if (!graph.containsNode(child))
                queue.add(new NodeEntry(child, edge.weight));
        }

        //if nothing to add - we visited all;
        if (queue.isEmpty())
            return;

        //filtering out visited nodes
        var nextNodeEntry = queue.remove();
        while (!queue.isEmpty() && graph.containsNode(nextNodeEntry.node)) {
            nextNodeEntry = queue.remove();
        }

        var nextNode = nextNodeEntry.node;
        if (!graph.nodes.containsKey(nextNode.label)) {
            graph.addNode(nextNode.label);
            graph.addEdge(node.label, nextNode.label, nextNodeEntry.priority);
            getMinSpanningTree(nextNode, graph, queue);
        }
    }

    private boolean containsNode(Node node) {
        return nodes.containsKey(node.label);
    }

    public void print() {
        for (var node : nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty()) {
                System.out.println(node + " connected with " + edges);
            }
        }
    }
}
