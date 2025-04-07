import java.util.Iterator;

public class ArraySetV4<T> implements Iterable<T> {
    T[] items = (T[]) new Object[8];
    int size;

    public ArraySetV4() {
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

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T curItem = items[wizPos];
            wizPos += 1;
            return curItem;
        }
    }

    public static void main(String[] args) {
        ArraySetV4<String> s = new ArraySetV4<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());

        for (String i : s) {
            System.out.println(i);
        }
    }
}
