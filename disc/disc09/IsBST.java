public class IsBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    /*
    下面这个实现有问题，只考虑了T和其左右节点，但是T还需要和节点下面的所有元素比较
     */
    public static boolean isBSTBad(TreeNode T) {
        if (T == null) {
            return true;
        } else if (T.left != null && T.left.val > T.val) {
            return false;
        } else if (T.right != null && T.right.val < T.val) {
            return false;
        } else {
            return isBSTBad(T.left) && isBSTBad(T.right);
        }
    }

    /*
    在处理当前树的时候，除了考虑当前节点的父节点，还要考虑父节点的所有父节点
    method signature中可以包含当前树期望的范围，每个元素都在这个范围内即可
    例如根节点是100，那么左子树的上限是100，右子树的下限是100
     */
    public static boolean isBSTGood(TreeNode T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTHelper(TreeNode T, int lowerBound, int upperBound) {
        if (T == null) {
            return true;
        }
        if (T.left != null && (T.left.val > T.val || T.left.val < lowerBound)) {
            return false;
        }
        if (T.right != null && (T.right.val < T.val || T.right.val > upperBound)) {
            return false;
        }
        return isBSTHelper(T.left, lowerBound, T.val) && isBSTHelper(T.right, T.val, upperBound);
    }

    /*
    上面这个版本稍微有点啰嗦，其实直接判断T.val就行了
     */
    public static boolean isBSTHelperV2(TreeNode T, int lowerBound, int upperBound) {
        if (T == null) {
            return true;
        }
        if (T.val < lowerBound || T.val > upperBound) {
            return false;
        }
        return isBSTHelperV2(T.left, lowerBound, T.val) && isBSTHelperV2(T.right, T.val, upperBound);
    }
}
