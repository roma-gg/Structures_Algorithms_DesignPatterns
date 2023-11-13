package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
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
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Edge>> adjacencyList = new HashMap<>();


    public void addNode(String label) {
        var node = new Node(label);
        nodes.put(label, node);
        adjacencyList.put(node, new ArrayList<>());
    }

    public void addEdge(Node from, Node to, int weight) {
        var edge = new Edge(from, to, weight);
        adjacencyList.get(from).add(edge);
    }
}
