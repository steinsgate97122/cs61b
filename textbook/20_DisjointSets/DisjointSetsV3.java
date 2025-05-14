/**
 可以直接用array，int[] a中，索引i表示第i个元素，a[i]表示该元素对应的集合
 {0, 1, 2, 4} {3, 5} {6} 3个集合可以表示为[0, 0, 0, 3, 0, 3, 6]，connect(1, 3)的时候，1对应的集合为0，3对应的集合为3
 那么将所有元素3替换为元素0，即完成了集合的合并，要判断两个元素是否在同一个集合内，只需要判断 a[p] == a[q] 即可
 不过在下面的实现中，构造方法要用θ(N)，connect也要用θ(N)，isConnected是θ(1)，前两个操作还有提效空间
 */
public class DisjointSetsV3 {
    private int[] a;

    public DisjointSetsV3(int num) {
        a = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = i;
        }
    }

    void connect(int p, int q) {
        int setP = a[p];
        int setQ = a[q];
        if (setP != setQ) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] == setP) {
                    a[i] = setQ;
                }
            }
        }
    }

    boolean isConnected(int p, int q) {
        return a[p] == a[q];
    }
}
