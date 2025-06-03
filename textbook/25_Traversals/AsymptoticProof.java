public class AsymptoticProof {
    /*
    求下面这个func的时间复杂度
    log1+log2+log3+log4+...+log(n)=log(n!)
    可以证明 log(n!) = θ(nlog(n))
    先看上界，n!<<n^n, log(n!)<<nlog(n)
    再看下界，log(n!) = log1+log2+...+log(n) > log(n/2)+...+log(n) > (n/2)log(n/2)
    得证
     */
    public static void func(int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j = j * 2) {
                System.out.println(i + j);
            }
        }
    }
}
