public class LinkedListDeque<T> {
    private class Node {
        T item;  // 用到了外面的generic type, 所以class不能是静态的
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

    public LinkedListDeque() {
        Node sentry = new Node(null);
        sentry.next = sentry;
        sentry.prev = sentry;
        this.sentinel = sentry;
        size = 0;
    }

/*
    public LinkedListDeque(LinkedListDeque<T> other) {
        this();
        Node cur = sentinel.next;
        while (cur != sentinel) {
            addLast(cur.item);
            cur = cur.next;
        }
    }
*/

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        Node firstNode = new Node(item);
        firstNode.next = sentinel.next;
        firstNode.prev = sentinel;
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    public void addLast(T item) {
        Node lastNode = new Node(item);
        lastNode.next = sentinel;
        lastNode.prev = sentinel.prev;
        sentinel.prev.next = lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    public void printDeque() {
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

    public T getRecursive(int index) {
        // 用递归实现, 效果同get()
        return helpGetRecursive(index, sentinel.next);
    }

    private T helpGetRecursive(int index, Node node) {
        if (node == sentinel) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return helpGetRecursive(index - 1, node.next);
    }
}
