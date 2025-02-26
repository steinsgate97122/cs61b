package disc03;

public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int item, IntNode next) {
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;

    public void addFirst(int x) {
        first = new IntNode(x, first);
    }

    public void insert(int item, int position) {
        // position保证非负 在position插入item 超过数组长度时插入在最后
        if (position == 0 || first == null) {
            first = new IntNode(item, first);
            return;
        }
        IntNode prev = first;
        IntNode cur = first.next;
        while (cur != null) {
            // PS: 其实insert操作直接在循环外面做就行，循环内只需要操作position和cur，代码能简洁很多
            position -= 1;
            if (position == 0) {
                prev.next = new IntNode(item, cur);
                return;
            }
            prev = cur;
            cur = cur.next;
        }
        prev.next = new IntNode(item, null);
    }

    public void reverseV1() {
        if (first == null || first.next == null) {
            return;
        }
        IntNode prev = first;
        IntNode cur = first.next;
        IntNode n = cur.next;
        first.next = null;
        while (n != null) {
            cur.next = prev;
            prev = cur;
            cur = n;
            n = n.next;
        }
        cur.next = prev;
        first = cur;
    }

    public void reverse() {
        IntNode frontNode = null;
        IntNode nextToAdd = first;
        while (nextToAdd != null) {
            IntNode remainOfOrigin = nextToAdd.next;
            nextToAdd.next = frontNode;
            frontNode = nextToAdd;
            nextToAdd = remainOfOrigin;
        }
        first = frontNode;
    }

    public void reverseRecursive() {
        first = reverseHelper(first);
    }

    private IntNode reverseHelper(IntNode x) {
        if (x == null || x.next == null) {
            return x;
        }
        IntNode reverseNext = reverseHelper(x.next);
//        IntNode cur = reverseNext;
//        while (cur.next != null) {
//            cur = cur.next;
//        }
//        cur.next = x;
//        x.next = null;
        // 完全不需要上面这么循环来找reverseNext的最后一个元素，x.next就是最后一个
        x.next.next = x;
        x.next = null;
        return reverseNext;
    }

    public static void main(String[] args) {
        SLList list = new SLList();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.reverseRecursive();
        System.out.println(list);
    }
}
