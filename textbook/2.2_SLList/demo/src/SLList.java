public class SLList {
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    // first改成sentinel，可以避免special case的出现，空SLList的sentinel也不为空
    private final IntNode sentinel = new IntNode(63, null);
    private int size;  // 维护了size后，size()方法就快多了，不过addFirst()会相应变慢一点，存储空间也会上升

    public SLList(int x) {
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    // 原先对于IntNode不好定义空的list，借助SLList就能定义了
    public SLList() {
        size = 0;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int size() {
        return size;
    }

    public void addLast(int x) {
        size += 1;
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(2);
        L.addLast(4);
        L.addFirst(10);
        System.out.println(L);
    }
}
