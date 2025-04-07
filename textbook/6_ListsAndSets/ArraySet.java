import java.util.ArrayList;
import java.util.Iterator;

/*
前一个版本的toString()的实现看上去比较冗余，又是循环又是各种append
1.现在的版本先优化一下toString()的实现
2.官方提供的列表构建支持 List.of("Java", "is", "cool") 现在希望让ArraySet也支持这样来构建对象
 */

public class ArraySet<T> implements Iterable<T> {
    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i += 1) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map.
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("can't add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * returns an iterator (a.k.a. seer) into ME
     */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public String toString() {
        // 在python中可以用",".join()这种语法，希望这里也用类似方式实现toString，String类提供了join方法
        // join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
        // 先构建一个String的ArrayList，然后就可以将这个List的所有元素组合起来
        ArrayList<String> strList = new ArrayList<>();
        for (T item : this) {
            strList.add(item.toString());
        }
        return "{" + String.join(", ", strList) + "}";
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArraySet)) {
            return false;
        }
        if (size != ((ArraySet<?>) other).size) {
            return false;
        }
        for(T item : this) {
            if (!((ArraySet<T>) other).contains(item)) {
                return false;
            }
        }
        return true;
    }

    /*
    这里由于是static method，因此用不上class内定义的T，函数维度定义一个新的泛型即可
     */
    public static <GG> ArraySet<GG> of(GG... elements) {
        ArraySet<GG> resultSet = new ArraySet<>();
        for (GG element : elements) {
            resultSet.add(element);
        }
        return resultSet;
    }

    public static void main(String[] args) {
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

        //iteration
        for (int i : aset) {
            System.out.println(i);
        }

        //toString
        System.out.println(aset);

        //equals
        ArraySet<Integer> aset2 = new ArraySet<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
        System.out.println(aset.equals(aset));

        System.out.println(ArraySet.of(44, 33, 55));
    }
}