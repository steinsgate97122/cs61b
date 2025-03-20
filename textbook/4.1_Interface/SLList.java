public class SLList<T> implements List61B<T> {
    private class Node {
        T item;
        Node next;
        Node prev;
        public Node(Node prev, T item, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
        public Node(T item) {
            this.item = item;
        }
    }

    private final Node sentinel;
    private int size;

    public SLList() {
        Node sentry = new Node(null);
        sentry.next = sentry;
        sentry.prev = sentry;
        this.sentinel = sentry;
        size = 0;
    }

    public SLList(SLList<T> other) {
        this();
        Node cur = sentinel.next;
        while (cur != sentinel) {
            addLast(cur.item);
            cur = cur.next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addFirst(T item) {
        Node firstNode = new Node(item);
        firstNode.next = sentinel.next;
        firstNode.prev = sentinel;
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node lastNode = new Node(item);
        lastNode.next = sentinel;
        lastNode.prev = sentinel.prev;
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public T getFirst() {
        return get(0);
    }

    @Override
    public T getLast() {
        return get(size - 1);
    }

    /*
     print在List61B里面有default方法，但那边的方法效率比较差，所以override掉
     Q. java如何得知调用哪个print()？ A. List61B<String> lst = new SLList<String>();
     上面的代码中，lst是一个SLList，同时也是List61B，这称为dynamic type
     称为dynamic也是合理的，假如现在 lst = new AList<String>(); 那么这个时候lst就不再是SLList
     换句话说，lst基于当前引用的对象来确定其类型，因此称为dynamic type
     当java执行Override的方法时，会优先找其dynamic type中的method signature来运行

     static type：lst的static type一直都是List61B，即编译期的类型
     dynamic type：运行期的类型，可以通过对象赋值来修改
     */
    @Override
    public void print() {
        Node cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.item);
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.next;
        removeNode.next.prev = sentinel;
        sentinel.next = removeNode.next;
        removeNode.prev = null;
        removeNode.next = null;
        size -= 1;
        return removeNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.prev;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        removeNode.prev = null;
        removeNode.next = null;
        size -= 1;
        return removeNode.item;
    }

    public T get(int index) {
        Node cur = sentinel.next;
        while (cur != sentinel) {
            if (index == 0) {
                return cur.item;
            }
            index -= 1;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void insert(T x, int position) {

    }
}
