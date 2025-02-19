/*
下面的SLList可以看作用户侧和底层的递归数据之间的中间层
 */
public class SLListV1 {
    // IntNode纯粹是一个SLList内的辅助class，允许直接嵌套定义在里面，用嵌套类对代码性能不会产生任何影响，就只是好看一点
    // IntNode用static修饰后，就无法访问SLList内的任何成员，这样IntNode不需要再维护任何访问SLList的路径，能省很多内存
    public static class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;  // 避免用户操作first.next乱动底层数据，这样在外面通过first再也访问不了next了
    // 看上去从public换成private纯负作用，让原先能编译通过的代码没法通过
    // 但在大型项目中加上private就是让外部用户忽略用private修饰的内容，而public表示该方法对外部用户是可用的
    // 在创建了一个public成员后，必须保证该成员的外部表现永远不会发生变化

    public SLListV1(int x) {
        // 要创建一个单节点的list，现在只需要new SLList(5)，看上去比new IntNode(5, null)要方便
        first = new IntNode(x, null);
    }

    private SLListV1(IntNode node) {
        first = node;
    }

    public void addFirst(int x) {
        // 类似的addFirst在隔壁IntNode也写了一版，效率更差，理解起来也更麻烦
        this.first = new IntNode(x, this.first);
    }

    public int getFirst() {
        return this.first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        if (first.next == null) {
            first.next = new IntNode(x, null);
            return;
        }
        SLListV1 listWithoutFirst = new SLListV1(first.next);
        listWithoutFirst.addLast(x);
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        // 这个方法在list变大时会特别慢，下一个版本直接将size维护在SLList的类成员内
        if (first.next == null) {
            return 1;
        }
        return new SLListV1(first.next).size() + 1;
    }

    public static void main(String[] args) {
        SLListV1 L = new SLListV1(1);
        L.addFirst(2);
        L.addLast(3);
        L.addLast(4);
        System.out.println(L.size());
    }
}
