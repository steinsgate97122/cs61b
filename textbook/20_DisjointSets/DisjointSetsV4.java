/**
 如何提升connect操作的效率？V3版本中将 {0, 1, 2, 4} {3, 5} {6} 表示为 [0, 0, 0, 3, 0, 3, 6]
 在上面表示方式下，必须将索引3和索引5的元素变成0，遍历所有元素不可避免
 换一个思路，array中的每个元素不再分配一个id，而是分配一个parent
 例如，元素0 1 4的parent都是0，2的parent是1，3 5的parent都是3，6的parent是6，就可以表示为 [0, 0, 1, 3, 0, 3, 6]
 那么第一个set的根节点就是0，第二个set的根节点是3，第三个set的根节点是6，分出来了3个set
 如果执行connect(5, 2)，那么先找到5所在set的根节点，就是3，2所在set的根节点则是0，那么将3作为0的子节点即可
 */
public class DisjointSetsV4 {
    private int[] a;

    public DisjointSetsV4(int num) {
        /*构造方法与V3版本完全一致，还是θ(N)*/
        a = new int[num];
        for (int i = 0; i < num; i++) {
            a[i] = i;
        }
    }

    private int findRoot(int e) {
        /*这个要看情况，最坏情况下要找N次才能找到，但是最好情况下1次就能找到，取决于树构造成什么样，复杂度O(N)
        * 下面的connect和isConnected都用到了findRoot，复杂度也是O(N)
        * 所以就connect操作来说，V4版本可能优于V3，但在最坏情况下可能还不如V3，主要原因就是tree可能太高了
        * eg: connect(4,3) connect(3,2) connect(2,1) connect(1,0) ，那么要找4的root，就要找4次
        * 但connect时只需要修改一个指针，这相比V3版本更有潜力，会在后面进一步优化
         * */
        while (a[e] != e) {
            e = a[e];
        }
        return e;
    }

    void connect(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        a[rootP] = rootQ;
    }

    boolean isConnected(int p, int q) {
        int rootP = findRoot(p);
        int rootQ = findRoot(q);
        return rootP == rootQ;
    }
}
