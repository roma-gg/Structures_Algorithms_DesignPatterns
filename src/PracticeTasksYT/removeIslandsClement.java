package PracticeTasksYT;

import java.util.Arrays;

public class removeIslandsClement {
    public static void removeIslands() {
        int[][] array = {
                {1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0},
                {1, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0, 1}
        };

        //[1, 0, 0, 0, 0, 0]
        // [0, 0, 0, 1, 1, 1]
        // [0, 0, 0, 0, 1, 0]
        // [1, 1, 0, 0, 1, 0]
        // [1, 0, 0, 0, 0, 0]
        // [1, 0, 0, 0, 0, 1]

        var result = removeIslands(array);
        System.out.println(Arrays.deepToString(result));
    }

    public static int[][] removeIslands(int[][] array) {
        // x for lines;
        // y for columns;
        for (int x = 1; x < array.length - 1; x++) {
            for (int y = 1; y < array[0].length - 1; y++) {
                if (!isConnectedWithBorder(array,x,y,x,y)) {
                    array[x][y] = 0;
                }
            }
        }
        return array;
    }


    public static boolean isConnectedWithBorder(int[][] array, int x, int y, int previousX, int previousY) {
        if (isZero(array, x, y))
            return false;
        else if (isBorder(array, x, y))
            return true;

        if (x - 1 == previousX) {
            return isConnectedWithBorder(array, x+1, y, x, y) ||
                    isConnectedWithBorder(array, x, y - 1, x, y) ||
                    isConnectedWithBorder(array, x, y + 1, x, y);
        }
        else if (x + 1 == previousX) {
            return isConnectedWithBorder(array, x-1, y, x, y) ||
                    isConnectedWithBorder(array, x, y - 1, x, y) ||
                    isConnectedWithBorder(array, x, y + 1, x, y);
        }
        else if (y - 1 == previousY)
            return isConnectedWithBorder(array, x-1, y, x, y) ||
                    isConnectedWithBorder(array, x+1, y, x, y) ||
                    isConnectedWithBorder(array, x, y + 1, x, y);
        else if (y + 1 == previousY)
            return isConnectedWithBorder(array, x-1, y, x, y) ||
                    isConnectedWithBorder(array, x+1, y, x, y) ||
                    isConnectedWithBorder(array, x, y - 1, x, y);
        else
            return isConnectedWithBorder(array, x-1, y, x, y) ||
                    isConnectedWithBorder(array, x+1, y, x, y) ||
                    isConnectedWithBorder(array, x, y - 1, x, y) ||
                    isConnectedWithBorder(array, x, y + 1, x, y);
    }

    public static boolean isBorder(int[][] array, int x, int y) {
        return x == 0 || x == array.length - 1 || y == 0 || y == array[0].length - 1;
    }

    public static boolean isZero(int[][] array, int x, int y) {
        return array[x][y] == 0;
    }

}
