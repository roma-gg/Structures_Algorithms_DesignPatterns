import Graphs.GraphMosh;

public class Main {
    public static void main(String[] args) {
        var graph = new GraphMosh();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("D", "C");

        graph.traverseBFSIter("D");




    }

    
}

