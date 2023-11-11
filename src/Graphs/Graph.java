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

    // O(1) hashmap traversing
    // O(1) hashmap.add
    // 0(1) arraylist.add
    public void addNode(String label) {
        if (label == null)
            throw new IllegalArgumentException();

        if (!mapLabel.containsKey(label)) {
            mapLabel.put(label, list.size());
            mapIndex.put(list.size(), label);
            list.add(new LinkedList<>());
        }
    }

    // O(1) traversing hashmap
    // 0(1) removing from map
    // O(1) removing from arraylist with index
    // O(V2) removing edges

    // lists:
    // 0 -> 1, 2;
    // 1 -> ;
    // 2 -> 0;

    //mapLabel
    //keys  "A", "B", "C", "D";
    //value  0    1    2    3
    //            ^

    //mapIndex
    //keys   0    1    2    3
    //value "A", "B", "C", "D";
    //            ^

    public void removeNode(String label) {
        if (mapLabel.containsKey(label)) {
            int index = (int) mapLabel.get(label);
            mapLabel.remove(label);
            shiftElementsMapLabel(index);
            shiftElementsMapIndex(index);

            list.remove(index);

            for (var each : list) {
                each.removeIf(m -> m == index);
            }

            shiftLinkedLists(index);
        }
    }

    private void shiftElementsMapLabel(int index) {
        for (var each : mapLabel.keySet()) {
            int value = (int)mapLabel.get(each);
            if (value > index)
                mapLabel.put(each, value - 1);
        }
    }

    //not sure about removing during iteration;
    private void shiftElementsMapIndex(int index) {
        for (var each : mapIndex.keySet()) {
            int keyIndex = (int)each;
            if (keyIndex > index) {
                mapIndex.put(keyIndex - 1, mapIndex.get(keyIndex));
                mapIndex.remove(keyIndex);
            }
        }
    }

    private void shiftLinkedLists(int index) {
        for (var each : list) {
            for (var value : each) {
                if (value > index) {
                    each.remove(value);
                    each.add(value - 1);
                }
            }
        }
    }

    // O(1) traversing maps
    // 0(1) adding to the LinkedList
    public void addEdge(String from, String to) {
        if (mapLabel.containsKey(from) && mapLabel.containsKey(to)) {
            int indexFrom = (int) mapLabel.get(from);
            int indexTo = (int) mapLabel.get(to);
            list.get(indexFrom).add(indexTo);
        }
    }

    // O(1) traversing maps
    // 0(n) removing from the LinkedList
    public void removeEdge(String from, String to) {
        if (mapLabel.containsKey(from) && mapLabel.containsKey(to)) {
            int indexFrom = (int) mapLabel.get(from);
            int indexTo = (int) mapLabel.get(to);
            list.get(indexFrom).removeIf(m -> m == indexTo);
        }
    }

    // O(V2)   because O(V) traversing map * O(V) traversing LinkedList

    public void print() {
        for (var each : mapLabel.keySet()) {
            int index = (int)mapLabel.get(each);
            List<String> connections = new ArrayList<>();
            for (var value : list.get(index)) {
                connections.add((String)mapIndex.get(value));
            }

            String result = each + " is connected with " + connections;
            System.out.println(result);
        }
    }


}
