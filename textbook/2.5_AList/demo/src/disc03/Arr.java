package disc03;

import java.util.Arrays;

public class Arr {
    private int[] items;
    private int size;

    /** Creates an empty list. */
    public Arr() {
        items = new int[3];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            double rFactor = 1.5;
            int[] newItems = new int[(int) (size * rFactor)];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int removedItem = items[size - 1];
        size -= 1;
        return removedItem;
    }

    public void insert(int item, int position) {
        if (position >= size) {
            addLast(item);
            return;
        }
        int[] newItems = new int[items.length + 1];
        if (position > 0) {
            System.arraycopy(items, 0, newItems, 0, position);
        }
        newItems[position] = item;
        System.arraycopy(items, position, newItems, position + 1, size - position);
        items = newItems;
        size += 1;
    }

    public static int[] insert(int[] arr, int item, int position) {
        int[] res = new int[arr.length + 1];
        if (position >= arr.length) {
            // 可以不这么特殊处理，加一段position = Math.min(arr.length, position)就能解决特殊case
            System.arraycopy(arr, 0, res, 0, arr.length);
            res[arr.length] = item;
            return res;
        }
        if (position > 0) {
            System.arraycopy(arr, 0, res, 0, position);
        }
        res[position] = item;
        System.arraycopy(arr, position, res, position + 1, arr.length - position);
        return res;
    }

    public static void reverse(int[] arr) {
        // 其实循环arr.length / 2次就够了，长度为3的循环1次就行，中间的就1个元素没必要换
        int loopTimes = (int) Math.ceil(arr.length / 2.0);
        for (int i = 0; i < loopTimes; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }

    public static int[] replicate(int[] arr) {
        // 第i个元素重复arr[i]次，可以假定arr[i]均为正数
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int[] res = new int[sum];
        int index = 0;
        // 建议下面的2层循环中，外层的用item，内层的用counter，含义清楚一点
        for (int i : arr) {
            for (int j = 0; j < i; j++) {
                res[index] = i;
                index += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] aList = {1, 2, 3};
        int[] res = replicate(aList);
        System.out.println(Arrays.toString(res));
    }
}
