package Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class CustomHashTable {

    LinkedList<Input>[] array = new LinkedList[10];

    //O(n)
    public void put (int key, String value) {
        var bucket = getBucket(key);
        if (bucket == null) {
            bucket = array[hashFunction(key)] = new LinkedList<>();
        }

        var input = getInput(key);
        if (input != null) {
            input.setValue(value);
            return;
        }

        bucket.add(new Input(key, value));
    }

    //O(n)
    public String getValue (int key) {
        if (isEmpty(key))
            throw new IllegalStateException();

        var input = getInput(key);
        if (input != null)
            return input.getValue();

        throw new IllegalArgumentException();
    }

    //O(n)
    public String remove(int key) {
        if (isEmpty(key))
            throw new IllegalStateException();

        var input = getInput(key);
        var bucket = getBucket(key);

        if (input.getKey() != null) {
            bucket.remove(input);
            return input.getValue();
        }

        throw new IllegalStateException();
    }


    private int hashFunction(int key) {
        var result = key % array.length;
        return result;
    }

    public boolean isEmpty(int key) {
        var index = hashFunction(key);
        return array[index].isEmpty() || array[index] == null;
    }

    private Input getInput (int key) {
        var index = hashFunction(key);
        var bucket = array[index];
        if (!bucket.isEmpty()) {
            for (var each : bucket) {
                if (each.getKey() == key)
                    return each;
            }
        }
        return null;
    }

    private LinkedList<Input> getBucket (int key) {
        return array[hashFunction(key)];
    }

    public int mostFrequent (int[] array) {
        var hashMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            var element = array[i];
            if (hashMap.containsKey(element)) {
                var count = hashMap.get(element) + 1;
                hashMap.put(element, count);
            }
            else
                hashMap.put(element, 1);
        }

        int resultKey = 0;
        int resultValue = 0;
        for (var each : hashMap.keySet()) {
            if (hashMap.get(each) > resultValue) {
                resultValue = hashMap.get(each);
                resultKey = each;
            }
        }

        return resultKey;

    }

    public int countPairsWithDiff(int[] array, int difference) {
        var hashMap = new HashMap<Integer, Integer>();
        for (int each : array) {
            var count = 0;
            for (int each2 : array) {
                if (hashMap.containsKey(each2))
                    continue;
                if (each - each2 == difference || each2 - each == difference) {
                    count++;
                }
            }
            hashMap.put(each, count);
        }

        var max = 0;
        for (var each : hashMap.keySet()) {
            max += hashMap.get(each);
        }
        return max;
    }

    public int countPairsWithDiff2(int[] array, int difference) {
        var set = new HashSet<Integer>();
        for (int each : array) {
            set.add(each);
        }

        int count = 0;
        for (int each : array) {
            if (set.contains(each - difference))
                count++;
            if (set.contains(each + difference))
                count++;
            set.remove(each);
        }

        return count;
    }

    public int[] twoSum(int[] array, int target) {
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            var complement = target - array[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(array[i], i);
        }
        return null;
    }




    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null || array[i].isEmpty())
                continue;
            for (var each : array[i]) {
                result += each.getKey() + "=" + each.getValue()+ ", ";
            }
        }
        return result.substring(0, result.length() - 2) + "}";
    }

    private class Input {
        private Integer key;
        private String value;

        public Input(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }
        public void setKey(Integer key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }
}

