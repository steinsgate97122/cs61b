/**
 相比V4版本，希望生成的树稍微平衡一点
 例如对于[0,0,0,0,0,0,6,6,6,8]，执行connect(3, 8)
 首先root(3)=0, root(8)=6, 那么是执行a[0] = 6 还是 a[6] = 0
 这里比较树的大小，原则是将小的树挂在大的树下面，root为0的树包含6个节点，root为6的树包含4个节点，那么就是0作为根，即a[6] = 0
 上面比较时用到了树的大小，那么再额外维护一份size[]数组
 */
public class DisjointSetsV5 {
    private int[] a;
    private int[] size;

    public DisjointSetsV5(int num) {
        a = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = i;
            size[i] = 1;
        }
    }

    private int findRoot(int e) {
        while (a[e] != e) {
            e = a[e];
        }
        return e;
    }

    /**
     * 讨论一下connect出来的树的深度，假设一开始元素x的size为1，并且connect调用了N次，每次调用都让x的深度增加
     * 由于x的深度增加的前提是x所在树的size小于新树的size，那么可以说x的深度每次增加时，节点数量相比原先至少翻倍
     * 换句话说，x的深度为2时，树的大小至少2，x的深度为3时，树的大小至少4，x的深度为4时，树的大小至少8，这个是对数规律
     * 所以findRoot的最坏时间复杂度是O(logN)，同样connect和isConnected的最坏时间复杂度也是O(logN)
     */
    void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        // 根据size来决定谁来当根节点
        if (size[rootP] > size[rootQ]) {
            a[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            a[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
    }

    boolean isConnected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        return rootP == rootQ;
    }
}
