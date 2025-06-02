/*
下面这种模式称为visitor pattern
思路：遍历树的方式固定，但访问每个Node时需要做的事情交给Action来决定
只需要编写不同的Action实现类，就可以实现不同操作
如果不用这种模式直接编写代码会变成下面这样
find(Tree T) {
    if (T == null) return false;
    if ("pig".equals(T.label)) return true;
    return find(T.left) || find(T.right);
}
使用VisitorPattern即将遍历逻辑与访问逻辑分离，代码职责更加清晰
 */
public class VisitorPattern {
    public static class Tree {
        String label;
        Tree left;
        Tree right;
    }

    /*
    除了下面FindPig的Action，还可以定义其他的Action
    比如打印节点label，统计节点数量，查找某些符合要求的label名称，and so on
     */
    public static void preorderTraverse(Tree T, Action whatToDo) {
        if (T == null) {
            return;
        }
        whatToDo.visit(T);
        preorderTraverse(T.left, whatToDo);
        preorderTraverse(T.right, whatToDo);
    }

    public interface Action {
        void visit(Tree T);
    }

    public static class FindPig implements Action {
        boolean found = false;
        @Override
        public void visit(Tree T) {
            if ("pig".equals(T.label)) {
                found = true;
            }
        }
    }

    public static void main(String[] args) {
        Tree T = new Tree();
        preorderTraverse(T, new FindPig());
    }
}
