/*
Binary Search Trees
定义：For every node X in the tree:
    Every key in the left subtree is less than X’s key.
    Every key in the right subtree is greater than X’s key.
注意，这里p ≺ q读作p严格小于q，或者p strictly precedes q，不一定是整数大小的比较，也可能是字符串字典序，或者自定义的比较规则
另外按照定义，BST中No duplicate keys allowed

性能分析：
统计上，如果随机插入了N个节点，每次插入平均需要比较2*lnN次，树的高度平均为4.311*lnN
但是，如果用Hibbard deletion执行随机节点删除，树的平均高度变成了sqrt(N)，远高于logN，看上去这个操作会让树的性能退化
 */
public class BST01 {
    private int key;
    BST01 left, right;

    public BST01(int key, BST01 left, BST01 right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    /*
    总体原则，只要sk小于label，那么就找左子树；sk大于label，就找右子树
     */
    public static BST01 find(BST01 T, int sk) {
        if (T == null) {
            return null;
        }
        if (sk == T.key) {
            return T;
        }
        else if (sk < T.key) {
            return find(T.left, sk);
        }
        else {
            return find(T.right, sk);
        }
    }

    /*
    下面这种写法是标准函数式编程写法（完全不修改原结构）
    每次调用insert时，完全不修改输入的T，而是通过构造来返回一个新的BST
    java中一般会修改原结构，这种写法不太常见
     */
    public static BST01 insert(BST01 T, int ik) {
        if (T == null) {
            return new BST01(ik, null, null);
        }
        if (ik < T.key) {
            return new BST01(T.key, insert(T.left, ik), T.right);
        } else if (ik > T.key) {
            return new BST01(T.key, T.left, insert(T.right, ik));
        } else {
            return T; // no change, already exists
        }
    }

    /*
    命令式的递归风格，在调用insert时就修改了原结构，下面就直接替换了左右子树
    在使用这种风格写代码时，初学者可能会犯类似下面这样的错误
        if (T.left == null) {
            T.left = new TreeNode(key);
        } else {
            T.left = insert(T.left, key);
        }
    递归时并不是所有递归结果都需要接收的，else的分支内直接insert(T.left, key)就行，如果用了这种写法最开始就不需要判断T==null
    下面的V3版本给出这种思路的递归方法
     */
    public static BST01 insertV2(BST01 T, int ik) {
        if (T == null) {
            return new BST01(ik, null, null);
        }
        if (ik < T.key) {
            T.left = insertV2(T.left, ik);
        }
        else if (ik > T.key) {
            T.right = insertV2(T.right, ik);
        }
        return T;
    }

    /*
    下面这个纯粹教学用，算是一个概念辨析，实际上上面V2版本更加贴近实际
    因为Professor在课堂上需要强调递归可以直接修改结构，在T.left非空的情况下，insert的返回值其实可以没有
    因为insert方法本身就可以完成树结构的调整，返回值有用的就只是T.left为空的情况
     */
    public static BST01 insertV3(BST01 T, int ik) {
        if (ik < T.key) {
            if (T.left == null) {
                T.left = new BST01(ik, null, null);
            } else {
                insertV3(T.left, ik);
            }
        }
        else if (ik > T.key) {
            if (T.right == null) {
                T.right = new BST01(ik, null, null);
            } else {
                insertV3(T.right, ik);
            }
        }
        return T;
    }

    /*
    3 Cases:
    Deletion key has no children. 直接把待删除节点和父节点的连接断掉
    Deletion key has one child.  待删除节点的父节点连到待删除节点的子节点
    Deletion key has two children.  待删除节点替换成待删除节点左子树的最大节点 or 右子树的最小节点
    上面有2个children的思路是Hibbard deletion
    delete方法返回删除后新的树
     */
    public static BST01 delete(BST01 T, int dk) {
        if (T == null) {
            return null;
        }
        if (dk < T.key) {
            T.left = delete(T.left, dk);
        } else if (dk > T.key) {
            T.right = delete(T.right, dk);
        } else {
            // 当前的T待删除，如果no children，直接返回null，如果one child，就返回这个child
            if (T.left == null) {
                return T.right;
            }
            if (T.right == null) {
                return T.left;
            }
            // 改成删除左子树的最大节点，将找到的节点的key赋值给T，然后删掉找到的节点
            BST01 newT = max(T.left);
            T.key = newT.key;
            T.left = delete(T.left, newT.key);
        }
        return T;
    }

    private static BST01 max(BST01 T) {
        if (T.right == null) {
            return T;
        } else {
            return max(T.right);
        }
    }
}
