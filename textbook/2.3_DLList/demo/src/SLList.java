// 上一版SLList中，addLast方法需要遍历整个list，效率较差，参考size的实现方式，记录下last变量即可
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
    private IntNode last;
    private int size;  // 维护了size后，size()方法就快多了，不过addFirst()会相应变慢一点，存储空间也会上升

    public SLList(int x) {
        sentinel.next = new IntNode(x, null);
        last = sentinel.next;
        size = 1;
    }

    // 原先对于IntNode不好定义空的list，借助SLList就能定义了
    public SLList() {
        size = 0;
        last = sentinel;
    }

    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    public int size() {
        return size;
    }

    /*
    加入last变量后，addLast看上去快了不少，但removeLast还有问题，因为需要找到last的前一个节点
    再维护last的前一个节点也是没用的，因为在removeLast的时候要同时更新secondLast，本质上还是要走一遍完整的list
    要么改造一下IntNode？除了next再维护上prev？这样removeLast的时候可以通过last找到secondLast，直接解决问题？
    这样确实可以快速实现removeLast，不过新增了special case，removeLast的时候需要操作last.prev
    但对于空list来说last就是sentinel，sentinel初始化肯定是类似new IntNode(null, 63, null)，其prev是null
    2种方式可以解决此问题：
    1）再加一个sentinel，类似于之前的sentinel指向first，新的sentinel指向last
    2）也可以只用一个sentinel，直接让sentinel的prev指向list的最后一个节点，最后一个节点的next指向sentinel，构成循环
    */
    public void addLast(int x) {
        last.next = new IntNode(x, null);
        last = last.next;
        size += 1;
    }

    public static void main(String[] args) {
        SLList L = new SLList();
        L.addLast(2);
        L.addLast(4);
        L.addFirst(10);
        System.out.println(L);
    }
}

/*
SLList(Single Linked List)
DLList(Doubly Linked List)
 */
