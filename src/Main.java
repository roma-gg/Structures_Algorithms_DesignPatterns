import Graphs.WeightedGraph;

public class Main {
    public static void main(String[] args) {
        var graph = new WeightedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A", "B", 3);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 4);
        graph.addEdge("D", "C", 5);


        var minSpanTree = graph.getMinSpanningTree();
        minSpanTree.print();

    }

    
}

