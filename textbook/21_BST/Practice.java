public class Practice {
    public static class IntTree {
        public IntTree (int data, IntTree left, IntTree right) {
            this.data = data; this.left = left; this.right = right;
        }
        public final int data;
        public IntTree left, right;
    }

    /*
    需要实现一个destructive mergeRight方法，功能是combine两颗binary search tree
    输入的BST有一个sentinel node，其data不需要考虑，sentinel node的左指针指向树的根节点
    其中一棵树是right-leaning的，即一个有序链表，输出merge后的binary search tree
    PS：不能直接使用BST本身insert节点的方法
     */
    public IntTree mergeRight(IntTree T, IntTree L) {
        /* 怎么将L中的节点都插入到T的空位上去？？需要比直接insert的效率更高？？
        L是right-leaning，插入的元素已经完成了升序排序，怎么利用这个特性？？
        第一个元素插入完成之后，下一个元素的插入位置不需要再从根节点开始找，而是从上一个插入的位置继续
        把起始搜索点加入到函数签名？但可能要基于起始点的父节点继续找，看上去这个思路有点问题
        换个思路，插入到左边的时候left肯定是空，插入到右边的时候right肯定是空；插入左边的情况下，父节点提供了当前可以插入的上界
        那么L中所有小于这个上界的元素都可以直接搬到这个位置；如果插入右边，父节点的信息没啥用了，但更加往上的节点可能提供过上界信息
         */
        // 什么元素都可以往T里面插入，初始bound是无穷大
        return mergeWithBound(T.left, L, Integer.MAX_VALUE);
    }

    /**
     *
     * @param T binary search tree T，为了递归方便这里T就不用sentinel了
     * @param L right leaning tree L
     * @param upperBound 可以插入在T的元素的上界
     * @return 完成merge后的binary search tree，一般就是T，为了兼容T为null的情况，所以返回一下
     */
    private IntTree mergeWithBound(IntTree T, IntTree L, int upperBound) {
        if (L.left == null) {
            return T;
        }
        if (T != null) {
            if (T.data > L.left.data) {
                // 对左子树递归，考虑到T.left可能是null，这里直接更新
                T.left = mergeWithBound(T.left, L, T.data);
            } else {
                // 对右子树递归
                T.right = mergeWithBound(T.right, L, upperBound);
            }
        } else {
            // 开始拆L
            IntTree node = L.left;
            L.left = L.left.right;
            // node作为树，又可以执行递归处理L剩下的元素
            node.right = mergeWithBound(node, L, upperBound);
            return node;
        }
        return T;
    }
}
