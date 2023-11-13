import Graphs.Graph;
import Graphs.GraphMosh;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        var graph = new GraphMosh();
        graph.addNode("Ballyclare");
        graph.addNode("Antrim");
        graph.addNode("Belfast");
        graph.addEdge("Ballyclare", "Antrim");
        graph.addEdge("Ballyclare", "Belfast");
        graph.addEdge("Belfast", "Ballyclare");

        graph.print();
        System.out.println("======================");
        graph.removeNode("Belfast");
        graph.print();




    }

    
}

