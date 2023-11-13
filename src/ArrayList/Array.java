package ArrayList;

import java.util.*;

public class Array {
    int[] array;

    private int count = 0;

    public Array(int length) {
        array = new int[length];
    }

    public void insert(int item) {
        if (array.length == count) {
            int[] newArray = new int[array.length * 2];

            for (int j = 0; j < array.length; j++) {
                newArray[j] = array[j];
            }
            array = newArray;
        }

        array[count++] = item;
    }

    public void removeAt(int index) {
        if (index < 0 || index >= array.length)
            throw new IllegalArgumentException();

        count--;
        for (int i = index; i < (array.length - 1); i++) {
            array[i] = array[i + 1];
        }

        int[] newArray = new int[array.length - 1];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    public int indexOf(int item) {
        //O(n)
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    //O(n)
    public int max() {
        int result = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1])
                result = array[i + 1];
        }
        return result;
    }


    public int[] intersect(Array array1) {
        List<Integer> resultList = new ArrayList<>();
        for (int num1 : array1.array) {
            for (int num2 : array) {
                if (num1 == num2) {
                    resultList.add(num1);
                    break;
                }
            }
        }
        var resultHash = new HashSet<>(resultList).toArray();
        int[] intersected = new int[resultHash.length];
        for (int i = 0; i < resultHash.length; i++) {
            intersected[i] = (int) resultHash[i];
        }
        return intersected;
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += array[i] + ", ";
        }
        return result.trim().substring(0, result.length() - 2);
    }
}
