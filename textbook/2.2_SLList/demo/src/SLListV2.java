public class SLListV2 {
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int size;  // 维护了size后，size()方法就快多了，不过addFirst()会相应变慢一点，存储空间也会上升

    public SLListV2(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    // 原先对于IntNode不好定义空的list，借助SLList就能定义了
    public SLListV2() {
        first = null;
        size = 0;
    }

    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    public int size() {
        return size;
    }

    public void addLast(int x) {
        size += 1;
        if (first == null) {
            // 类似这种if加特殊case的尽量避免，不是一种合适的实现方式，下一个版本给一种更好的实现方式
            first = new IntNode(x, null);
            return;
        }
        IntNode p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public static void main(String[] args) {
        SLListV2 L = new SLListV2();
        L.addLast(2);
        L.addLast(4);
        System.out.println(L);
    }
}
