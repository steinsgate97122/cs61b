/*
V2版本中，当用户视图add null时，抛出异常
在python中使用raise来抛出异常，java中Exceptions被视为对象，语法为：throw new ExceptionObject(parameter1, ...)
比起前面将null当成合法输入存储，这里抛出异常，另外add(null)的时候不报错，什么都不执行也可以
以上都是合理的实现方式，但需要让用户知道预期结果，所以documentation很重要
常见异常包括：OutOfMemory ClassCast OutOfIndex NullPointer
但如果在代码中throw new IOException("hi"); 并且没有catch，那么编译都过不了，这种属于不能接受通过编译的异常
对于IOException，2种处理方式，要么自己catch掉，要么自己throws出去给外面处理，throws出去之后，让外面的调用者知道调用这个方法很危险，需要catch
 */
public class ArraySetV2<T> {
    T[] items = (T[]) new Object[8];
    int size;

    public ArraySetV2() {
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

    public static void main(String[] args) {
        ArraySetV2<String> s = new ArraySetV2<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size());
    }

    /*
        Set<String> S = new HashSet<>();
        S.add("Tokyo");
        S.add("Lagos");
        for (String s : S) {
            System.out.println(s);
        }
        类似的代码换成ArraySet会有问题，如何兼容？下个版本中解决
     */
}
