package Graphs;

import java.util.*;

public class Graph {
    private List<LinkedList<Integer>> list = new ArrayList<>();
    private Map mapLabel = new HashMap<String, Integer>();
    private Map mapIndex = new HashMap<Integer, String>();

    // O(1)
    public void addNode(String label) {
        if (label == null)
            throw new IllegalArgumentException();

        if (!mapLabel.containsKey(label)) {
            mapLabel.put(label, list.size());
            mapIndex.put(list.size(), label);
            list.add(new LinkedList<>());
        }
    }

    //O(V2)
    public void removeNode(String label) {
        if (mapLabel.containsKey(label)) {
            int index = (int) mapLabel.get(label);
            mapLabel.remove(label);
            //O(V)
            shiftElementsMapLabel(index);
            //O(V)
            shiftElementsMapIndex(index);
            list.remove(index);
            //O(V)
            for (var each : list) {
                each.removeIf(m -> m == index);
            }

            // O(V2)
            shiftEdges(index);
        }
    }

    private void shiftElementsMapLabel(int index) {
        for (var each : mapLabel.keySet()) {
            int value = (int)mapLabel.get(each);
            if (value > index)
                mapLabel.put(each, value - 1);
        }
    }

    private void shiftElementsMapIndex(int index) {
        for (var each : mapIndex.keySet()) {
            int keyIndex = (int)each;
            if (keyIndex > index) {
                mapIndex.put(keyIndex - 1, mapIndex.get(keyIndex));
                mapIndex.remove(keyIndex);
            }
        }
    }

    private void shiftEdges(int index) {
        for (var each : list) {
            for (var value : each) {
                if (value > index) {
                    each.remove(value);
                    each.add(value - 1);
                }
            }
        }
    }

    // 0(1)
    public void addEdge(String from, String to) {
        if (mapLabel.containsKey(from) && mapLabel.containsKey(to)) {
            int indexFrom = (int) mapLabel.get(from);
            int indexTo = (int) mapLabel.get(to);
            list.get(indexFrom).add(indexTo);
        }
    }

    // 0(V) removing from the LinkedList
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
