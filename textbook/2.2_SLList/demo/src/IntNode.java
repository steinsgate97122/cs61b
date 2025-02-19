public class IntNode {
    // 和前面的IntList一样，换了个名字鹅已
    public int item;
    public IntNode next;

    public IntNode(int i, IntNode n) {
        item = i;
        next = n;
    }

    public void addFirst(int x) {
        // 先创建一个当前node的副本
        IntNode curNode = new IntNode(item, next);
        item = x;
        next = curNode;
    }
}
