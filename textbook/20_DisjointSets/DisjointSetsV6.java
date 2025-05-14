/**
 V5版本中findRoot()的时间复杂度为O(logN)，这个可以进一步提升
 采用Path Compression的方法，每次执行findRoot()时，可以将这次find时遍历的所有节点都直接挂在根节点上，这样下次再find这个节点就只需要1次操作
 显然这个方法会大幅降低其amortized cost，可以证明findRoot()的时间复杂度下降到O(α(N))，其中α is the inverse Ackermann function
 α(N)对于任意大的N，其结果也不会超过5，直接当成常数时间即可
 */
public class DisjointSetsV6 {
    private int[] a;
    private int[] size;

    public DisjointSetsV6(int num) {
        a = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 直接递归，findRoot()返回根节点的index，直接把遍历到的a[e]都指向根节点
     */
    private int findRoot(int e) {
        if (a[e] != e) {
            a[e] = findRoot(a[e]);
        }
        return a[e];
    }

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
