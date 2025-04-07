import java.util.Iterator;

/*
所有class都继承了Object类，这里重点讨论以下两个方法：
String toString()
boolean equals(Object obj)

toString()提供了对象的string表达形式，System.out.println()隐式调用的对象的toString()
System.out.println(dog); 等效于 String s = dog.toString(); System.out.println(s);
Object类的toString()方法返回对象的内存地址，类似Arraylist这样的类会重写toString方法，因此System.out.println()会返回一个合理的结果

equals()和==在java中的功能完全不同；==是校验两个对象在内存中的地址是否一致
例如，对于Doge fido = new Doge(5, "Fido"); 和 Doge fidoTwin = new Doge(5, "Fido"); 这两个对象的地址是不同的
所以才需要equals()方法，Object中继承的默认equals()表现与==一样，也是比较内存地址，但可以重写这个方法
重写的时候需要注意：
1、reflexive: x.equals(x) is true
2、symmetric: x.equals(y) if and only if y.equals(x)
3、transitive: x.equals(y) and y.equals(z) implies x.equals(z)
equals方法必须接受一个Object参数，还要保证一致性（如果x.equals(y)，只要x和y没变，应该一致都是一致的）
 */

public class ArraySetV5<T> implements Iterable<T> {
    private T[] items;
    private int size; // the next item to be added will be at position size

    public ArraySetV5() {
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
        // StringBuilder提供了一个可变的string object，不需要创建一堆无用的string出来
        StringBuilder res = new StringBuilder();
        res.append("{");
        for (int i = 0; i < size - 1; i += 1) {
            res.append(items[i]).append(", ");
        }
        res.append(items[size - 1]).append("}");
        return res.toString();
    }


    @Override
    public boolean equals(Object other) {
        // Set是无序的，只需要比较两个对象的元素是否相同即可
        // 最开始还应该加上 if (this == other) ，最基本的就是保证x.equals(x)为true，之前写最前面提升效率
        if (!(other instanceof ArraySetV5)) {
            return false;
        }
        if (size != ((ArraySetV5<?>) other).size) {
            return false;
        }
        // 这里其实可以不用iterator，直接用 for(T item : this) {} 来遍历就行了
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (!((ArraySetV5<T>) other).contains(next)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ArraySetV5<Integer> aset = new ArraySetV5<>();
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
        ArraySetV5<Integer> aset2 = new ArraySetV5<>();
        aset2.add(5);
        aset2.add(23);
        aset2.add(42);

        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals("fish"));
        System.out.println(aset.equals(aset));
    }
}