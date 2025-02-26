import java.math.BigDecimal;

public class ArrayDeque<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;  // 必须加, 空数组和满数组的指针情况完全一样, 不加的话不好区分

    public ArrayDeque() {
        this.array = (T[]) new Object[8];
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    private void arrayExpansion() {
        T[] newArray = (T[]) new Object[array.length * 2];
        int originFirst = nextFirst + 1;
        if (originFirst > array.length - 1) {
            originFirst = 0;
        }
        int originLast = nextLast - 1;
        if (originLast < 0) {
            originLast = array.length - 1;
        }
        System.arraycopy(array, originFirst, newArray, 0, array.length - originFirst);
        System.arraycopy(array, 0, newArray, array.length - originFirst, size - (array.length - originFirst));
        array = newArray;
        nextFirst = array.length - 1;
        nextLast = size;
    }

    private void arrayReduction() {
        if (array.length >= 16) {
            int usageFactorReciprocal = array.length / size;
            if (usageFactorReciprocal >= 4) {
                T[] newArray = (T[]) new Object[array.length / 2];
                int originFirst = nextFirst + 1;
                if (originFirst > array.length - 1) {
                    originFirst = 0;
                }
                int originLast = nextLast - 1;
                if (originLast < 0) {
                    originLast = array.length - 1;
                }
                if (originFirst < originLast) {
                    System.arraycopy(array, originFirst, newArray, 0, size);
                } else {
                    System.arraycopy(array, originFirst, newArray, 0, array.length - originFirst);
                    System.arraycopy(array, 0, newArray, array.length - originFirst, size - (array.length - originFirst));
                }
                array = newArray;
                nextFirst = array.length - 1;
                nextLast = size;
            }
        }
    }

    public void addFirst(T item) {
        if (size == array.length) {
            arrayExpansion();
        }
        array[nextFirst] = item;
        nextFirst = nextFirst - 1;
        if (nextFirst < 0) {
            nextFirst = array.length - 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == array.length) {
            arrayExpansion();
        }
        array[nextLast] = item;
        nextLast = nextLast + 1;
        if (nextLast > array.length - 1) {
            nextLast = 0;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        int first = nextFirst + 1;
        if (first > array.length - 1) {
            first = 0;
        }
        for (int index = 0; index < size; index++) {
            int actualIndex = (first + index) % array.length;
            System.out.print(array[actualIndex] + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        arrayReduction();
        nextFirst = nextFirst + 1;
        if (nextFirst > array.length - 1) {
            nextFirst = 0;
        }
        T result = array[nextFirst];
        array[nextFirst] = null;
        size -= 1;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        arrayReduction();
        nextLast = nextLast - 1;
        if (nextLast < 0) {
            nextLast = array.length - 1;
        }
        T result = array[nextLast];
        array[nextLast] = null;
        size -= 1;
        return result;
    }

    public T get(int index) {
        int first = nextFirst + 1;
        if (first > array.length - 1) {
            first = 0;
        }
        int actualIndex = (first + index) % array.length;
        return array[actualIndex];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 900; i++) {
            deque.removeFirst();
        }
        deque.printDeque();
    }
}
