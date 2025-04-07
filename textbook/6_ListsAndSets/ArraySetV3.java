import java.util.Iterator;

/*
    Set<String> s = new HashSet<>();
    ...
    for (String city : s) {
        ...
    }
对于上面这段增强for代码，可等效翻译成下面的代码：
    Set<String> s = new HashSet<>();
    ...
    Iterator<String> seer = s.iterator();
    while (seer.hasNext()) {
        String city = seer.next();
        ...
    }
显然，Set接口中有定义签名：public Iterator<E> iterator();
然后Iterator接口支持hasNext()和next()
hasNext()检查是否存在未检查的元素
next()返回下一个元素，并且将iterator需要检查元素的向前移动
 */
public class ArraySetV3<T> {
    T[] items = (T[]) new Object[8];
    int size;

    public ArraySetV3() {
        size = 0;
    }

    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    public void add(T x) {
        if (x == null) {
            throw new IllegalArgumentException("cannot add null");
        }
        if (contains(x)) {
            return;
        }
        items[size] = x;
        size += 1;
    }

    public int size() {
        return size;
    }

    /** return an Iterator for me */
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        // 当前需要一个wizard position
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;  // 初始状态下，wizard的位置是0
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;  // 只要wizard的位置小于size，就还有其他元素可以获取
        }

        @Override
        public T next() {
            T curItem = items[wizPos];
            wizPos += 1;
            return curItem;
        }
    }

    public static void main(String[] args) {
        ArraySetV3<String> s = new ArraySetV3<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        Iterator<String> seer = s.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        /*
        for (String i : s) {
            System.out.println(i);
        }
        但是，现在的实现还不支持上面这段代码，因为java不知道s支持了iterator()
        Iterable接口提供了这个能力，这个接口需要实现Iterator方法，可参考下一版本
         */
    }
}
