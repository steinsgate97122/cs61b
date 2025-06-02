import utils.Action;
import utils.Tree;

/*
怎么实现levelOrder？
目标就是先遍历第0层，然后第1层，见下面的levelOrder，每次处理一个深度，称为iterative deepening
 */
public class LevelOrder {
    public void levelOrder(Tree T, Action todo) {
        for (int i = 0; i < T.height(); i++) {
            visitLevel(T, i, todo);
        }
    }

    private void visitLevel(Tree T, int i, Action todo) {
        if (T == null) {
            return;
        }
        if (i == 0) {
            todo.visit(T);
        }
        visitLevel(T.left, i - 1, todo);
        visitLevel(T.right, i - 1, todo);
    }
}
