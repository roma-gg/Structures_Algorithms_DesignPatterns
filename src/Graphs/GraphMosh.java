package Graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMosh {
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList

    public void addNode(String label) {
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
    }

    public void addEdge (String from, String to) {
        if (nodes.get(from) == null || nodes.get(to) == null)
            throw new IllegalArgumentException();

    }
}
