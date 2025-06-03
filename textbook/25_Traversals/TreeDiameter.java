import org.junit.Assert;
import org.junit.Test;
import utils.Tree;

public class TreeDiameter {
    /*
    the height of an empty tree is -1, that of tree with one node is 0
     */
    public static int height(Tree T) {
        if (T == null) {
            return -1;
        } else {
            return Math.max(height(T.left), height(T.right)) + 1;
        }
    }

    public static int diameter(Tree T) {
        // left和right可以不用判空
        if (T == null || T.left == null && T.right == null) {
            return 0;
        } else {
            int diameterWithRoot = height(T.left) + height(T.right) + 2;
            int diameterWithoutRoot = Math.max(diameter(T.left), diameter(T.right));
            return Math.max(diameterWithRoot, diameterWithoutRoot);
        }
    }

    @Test
    public void testDiameter1() {
        Tree t1 = new Tree("A");
        Tree t2 = new Tree("B");
        Tree t3 = new Tree("C");
        Tree t4 = new Tree("D");
        Tree t5 = new Tree("E");
        Tree t6 = new Tree("F");
        Tree t7 = new Tree("G");
        t5.right = t7;
        t2.right = t5;
        t2.left = t4;
        t1.left = t2;
        t1.right = t3;
        t3.left = t6;
        Assert.assertEquals(diameter(t1), 5);
    }

    @Test
    public void testDiameter2() {
        Tree t1 = new Tree("A");
        Tree t2 = new Tree("B");
        Tree t3 = new Tree("C");
        Tree t4 = new Tree("D");
        Tree t5 = new Tree("E");
        Tree t6 = new Tree("F");
        Tree t7 = new Tree("G");
        Tree t8 = new Tree("H");
        Tree t9 = new Tree("I");
        t1.right = t3;
        t1.left = t2;
        t2.left = t4;
        t4.left = t6;
        t6.right = t8;
        t2.right = t5;
        t5.right = t7;
        t7.right = t9;
        Assert.assertEquals(diameter(t1), 6);
    }
}
