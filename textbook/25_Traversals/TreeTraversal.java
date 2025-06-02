import java.io.File;

/*
磁盘中的文件目录就是一个树结构，假设希望查询文件总共用了多少空间
不同于list，tree有很多常见的遍历方式，将其称为tree traversal（可以当成tree iteration）
常见的比如level order（从上到下，从左到右）DFT（depth first traversals，深度优先）
深度优先的思想是，将一条路径完全走完之后，再考虑其兄弟，包括preorder inorder postorder

Q：这些traversal有什么用？
1.preorder 先访问自己，再访问左右子节点，用来打印目录结构
2.postorder 先处理所有子节点再处理自己，用来统计目录大小，子元素都加总后再算自己
            或者删除树结构的时候，先删子节点再删父节点
3.inorder  左 根 右，比如二叉排序树（BST），遍历后可以将元素以升序排序的方式输出
 */
public class TreeTraversal {
    public static class BSTNode {
        int key;
        int size;
        BSTNode left;
        BSTNode right;
        public BSTNode[] children() {
            return null;  // 这里不实现了
        }
    }

    void preOrder(BSTNode x) {
        if (x == null) {
            return;
        }
        System.out.println(x.key);
        preOrder(x.left);
        preOrder(x.right);
    }

    void inOrder(BSTNode x) {
        if (x == null) {
            return;
        }
        inOrder(x.left);
        System.out.println(x.key);
        inOrder(x.right);
    }

    void postOrder(BSTNode x) {
        if (x == null) {
            return;
        }
        postOrder(x.left);
        postOrder(x.right);
        System.out.println(x.key);
    }

    /*
    用postorder来gather file sizes
     */
    int postOderGather(BSTNode x) {
        if (x == null) {
            return 0;
        }
        int total = 0;
        for (BSTNode child : x.children()) {
            total += postOderGather(child);
        }
        total += x.size;
        return total;
    }

    /*
    这里写一个实际可以测算某个特定文件夹总大小的函数
     */
    public static long getFolderSize(File file) {
        if (file == null) {
            return 0;
        }

        // file为文件时直接返回，为目录时递归处理
        if (file.isFile()) {
            return file.length();
        }

        long total = 0;
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                total += getFolderSize(child);
            }
        }

        return total;
    }

    public static void main(String[] args) {
        File file = new File("D:\\Codes\\cs61b\\textbook");
        long folderSize = getFolderSize(file);
        System.out.println(folderSize);
    }
}
