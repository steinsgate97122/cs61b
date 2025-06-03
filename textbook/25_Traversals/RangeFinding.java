import utils.Tree;

import java.util.HashSet;
import java.util.Set;

/*
假定希望返回一颗树中所有指定range内的元素，树本身当然是BST
1.naive approach就是使用常规遍历，在Action内判断当前item是否在范围内
这个方法需要遍历树中的所有元素，复杂度O(N)
2.如果当前遍历的元素小于min，那么这个元素的左子树可以直接skip掉
同样的，当前遍历的元素大于max，那么其右子树可以直接skip，这个方法称为pruning（剪枝）
下面实现一下这个方法
 */
public class RangeFinding {
    public static Set<String> findInRange(Tree T, String min, String max) {
        Set<String> s = new HashSet<>();
        findHelper(T, min, max, s);
        return s;
    }

    private static void findHelper(Tree T, String min, String max, Set<String> s) {
        if (T == null) {
            return;
        }
        if (T.label.compareTo(min) < 0) {
            findHelper(T.right, min, max, s);
        } else if (T.label.compareTo(max) > 0) {
            findHelper(T.left, min, max, s);
        } else {
            s.add(T.label);
            findHelper(T.left, min, max, s);
            findHelper(T.right, min, max, s);
        }
    }
}
/*
假设落在[min, max]内的元素总个数为R，BST节点总个数为N，证明pruning后时间复杂度为θ(R+logN)
大致分析下思路，剪枝操作需要θ(logN)，剩下的查找需要θ(R)，求和就是θ(R+logN)
 */