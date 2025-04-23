/*
回顾List中的resize，众所周知，resize1会发生频繁的arraycopy比较消耗资源，resize2更加高效
add操作时可能会调用resize，现在问add操作的时间复杂度？
如果add的时候需要resize，时间复杂度就是O(N)，不需要resize的话，平均时间复杂度就是O(1)，这个在下面给出证明
严格证明用到了Amortized Analysis（摊销分析），核心思路是尽管resize操作时O(N)，但这种操作本身不频繁
因此我们不看单次的最坏情况，而是从整体角度来分析每次操作的平均成本

考虑第i次add
实际操作的代价记为ci，如果没有resize ci就是1，如果resize了就会是一个较大的数值
人为选定一个amortized cost ai，期望ai能够cover住ci实际操作的代价
再定义一个potential势能函数，定义为累计的ai-ci，只要这个势能函数恒定非负，那么amortized cost就是actual cost的上界

用上面的方法，正常add的开销记为1，需要resize的add的开销可以将每个元素的复制开销记为2，这种情况下，选择amortized cost为5即可
ai=5是通过观察后归纳出来的，列个表看一下不难发现

总结下来，potential函数将之前每次开销小的操作节省的成本预存下来，遇到类似resize这样高开销的操作时从potential中提取能量
只要potential非负，就说明整体上的开销可以用amortized cost来作为上界

由于很多数据结构的操作成本波动都非常大，单次成本可能很高，但整体比较高效
只考虑最坏情况是不准确的，因此引入了摊销分析，即Amortized Analysis
 */
public class Demo02 {
    public int[] resize1(int[] a) {
        int newSize = a.length + 1;
        int[] res = new int[newSize];
        System.arraycopy(a, 0, res, 0, a.length);
        return res;
    }

    public int[] resize2(int[] a) {
        int newSize = a.length * 2;
        int[] res = new int[newSize];
        System.arraycopy(a, 0, res, 0, a.length);
        return res;
    }
}
