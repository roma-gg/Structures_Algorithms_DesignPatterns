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

    // map Node, Integer distances
    // map Node, Node previousNodes
    // for each neighbour
    // if found shorter path
    // update distances table
    // push neighbour to queue

    public int getShortestDistance(String from, String to) {
        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparing(m -> m.priority));
        var distances = new HashMap<Node, Integer>();
        var previousNode = new HashMap<Node, Node>();

        for (var node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        var fromNode = nodes.get(from);
        distances.put(fromNode, 0);

        getShortestDistance(fromNode, distances, previousNode, queue);

        var toNode = nodes.get(to);
        return distances.get(toNode);


    }

    //from A to D
    private void getShortestDistance(Node current, Map<Node, Integer> distances,
                                     Map<Node, Node> previousNode, PriorityQueue<NodeEntry> queue) {

        for (var edge : current.getEdges()) {
            Node child = edge.to;

            int distanceToChild = edge.weight + getDistanceFromStart(current, distances, previousNode);

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

    // B 1 E
    // D 5 E
    //

    //getting shortest distance to the child Node
    private int getDistanceFromStart(Node current, Map<Node, Integer> distances, Map<Node, Node> previousNode) {
        int distance = 0;
        while (previousNode.containsKey(current) &&
                distances.get(current) != Integer.MAX_VALUE) {
            distance += distances.get(current);
            current = previousNode.get(current);
        }
        return distance;
    }

    private void clearDistanceTrace(HashMap<Node, Integer> distances, Map<Node, Node> previousNode, PriorityQueue<NodeEntry> queue) {
        distances.clear();
        previousNode.clear();
        queue.clear();
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
