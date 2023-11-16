import Graphs.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        var graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A", "B", 3);
        graph.addEdge("B", "C", 4);
        graph.addEdge("A", "D", 4);

        System.out.println(graph.hasCycle());


        // A - B
        // I   I
        // D   C

    }

    
}

