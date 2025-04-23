/*
big theta用来描述函数的order of growth, 也用来描述一段代码的rate of growth of the runtime
eg: binary search的worst case runtime为 BigTheta(logN)
big theta可以等同于equals，相应的big O可以认为是less than or equal，即big O只规定了上界
另外还有一个big Omega，可以当成greater than or equal，即big Omega只规定了下界
 */
public class Demo01 {
    /*
    令R(N)表示runtime of the code，则下面这段代码中，R(N)属于BidTheta(1)，因为第一次循环就直接出去了
     */
    public boolean dup3(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (a[i] == a[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    下面这段代码的R(N)无法确定，取决于输入
     */
    public boolean dup4(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i += 1) {
            for (int j = i + 1; j < N; j += 1) {
                if (a[i] == a[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    Big Theta expresses exact order of growth for runtime in terms of N
    但runtime可能除了N之外还取决于其他因素，dup4的最好情况为BigTheta(1)，最坏情况为BigTheta(N*N)
    因此可以直接用bigO，避免讨论复杂化，runtime of dup4 is O(N*N)，这样讲就没问题了

    The worst case runtime is Θ(N²)
    The runtime is O(N²)
    上面两段语句中，第一句所给的信息量更大，dup3和dup4的runtime都是O(N²)，但只有dup(4)满足第一句话

    现实中，虽然BigO的信息量不如BigTheta，但还是BigO更加常用，很多时候知道一个上限就够了，要证明BigO也更加容易
    PS：BigO并不意味着worst case，但在很多地方被滥用误认为是这样

    最后是BigOmega，一般和BigO一起使用，用来证明BigTheta，会比直接证明BigTheta容易
    另外会用BigOmega来找一个算法的最好情况，即下界
     */
}
