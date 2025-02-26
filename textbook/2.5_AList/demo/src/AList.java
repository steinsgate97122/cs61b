/*
考虑先insert 10000个item，再remove 9900个item的情况，这种情况下99%内存都是无用的
可以维护一个usage ratio，当其小于25%时就缩减数组
另外希望items不止能维护Integer，可以类似用generic的概念
数组定义时不能写成 T[] items = new T[5];  只能写作 T[] items = (T []) new Object[5];
改成generic之后，在removeLast的时候记得将对应数组元素变成null，否则垃圾回收机制不会回收这块内存
 */
public class AList {
    private int[] items;
    private int size;
    private final int RFACTOR = 2;

    /** Creates an empty list. */
    public AList() {
        items = new int[100];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == items.length) {
            // 每次+1的话，多次addLast时会进行频繁的创建和填充内存，每次可以多扩充一些
//            int[] newItems = new int[size + 1];
            int[] newItems = new int[size * RFACTOR];
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
}