public class Sort {
    /** Sorts strings destructively. */
    public static void sortV1(String[] x) {
        for (int sortPosition = 0; sortPosition < x.length - 1; sortPosition++) {
            // find the smallest item
            String smallest = x[sortPosition];
            int smallestIndex = sortPosition;
            for (int index = sortPosition + 1; index < x.length; index++) {
                if (x[index].compareTo(smallest) < 0) {
                    smallest = x[index];
                    smallestIndex = index;
                }
            }
            // move it to the front
            String tmp = x[sortPosition];
            x[sortPosition] = x[smallestIndex];
            x[smallestIndex] = tmp;
        }
    }

    public static void sort(String[] x) {
        sort(x, 0);
    }

    // 如果要递归，即对除了第一个元素之外的array进行排序，但java没有x[1:]这样的语法
    // 写一个helper函数，从第start项开始排序，这样就能递归了，对于本身无法递归的数据结构这是一种常见的处理方式
    private static void sort(String[] x, int start) {
        if (start == x.length - 1) {
            return;
        }
        // find the smallest item
        int smallestIndex = findSmallest(x, start);
        // move it to the front
        swap(x, start, smallestIndex);
        // recursion
        sort(x, start + 1);
    }

    public static int findSmallest(String[] x, int start) {
        int smallestIndex = start;
        for (int i = start; i < x.length; i++) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            if (cmp < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }
}