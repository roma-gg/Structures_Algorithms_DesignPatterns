package Graphs;

import java.util.*;

public class Graph {
    private List<LinkedList<Integer>> list = new ArrayList<>();
    private Map mapLabel = new HashMap<String, Integer>();
    private Map mapIndex = new HashMap<Integer, String>();

    //array with linked lists
    //nodes and it's indexes in hashmap
    private class Node {
        private String label;

        public Node(String label) {
            this.label = label;
        }
    }

    public void addNode(String label) {
        if (label == null)
            throw new IllegalArgumentException();

        if (!mapLabel.containsKey(label)) {
            mapLabel.put(label, list.size());
            mapIndex.put(list.size(), label);
            list.add(new LinkedList<>());
        }
    }

    public void removeNode(String label) {
        if (mapLabel.containsKey(label)) {
            int index = (int) mapLabel.get(label);
            mapLabel.remove(label);
            mapIndex.remove(index);
            list.remove(index);

            for (var each : list) {
                each.removeIf(m -> m == index);
            }
        }
    }

    public void addEdge(String from, String to) {
        if (mapLabel.containsKey(from) && mapLabel.containsKey(to)) {
            int indexFrom = (int) mapLabel.get(from);
            int indexTo = (int) mapLabel.get(to);
            list.get(indexFrom).add(indexTo);
        }
    }

    public void print() {
        for (var each : mapLabel.keySet()) {
            int index = (int)mapLabel.get(each);
            List<String> connections = new ArrayList<>();
            for (var value : list.get(index)) {
                connections.add((String)mapIndex.get(value));
            }

            String result = each + " is connected with " + list.get(index);
            System.out.println(result);
        }

    }


}
