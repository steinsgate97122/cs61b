public class DemoExample02 {
    /*
    1.Find the runtime of running print_fib with for arbitrary large n.
    斐波那契数列的通项公式的order of growth是a^n（用特征方程来解）
    虽然fib()的执行了n次，但最后一次占用了绝大多数时间，因此print_fib的时间复杂度还是a^n
     */
    public void print_fib(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(fib(i));
        }
    }

    // v2版本中，每次执行的都是fib(n)，这个时候时间复杂度应该是(n*a^n)
    public void print_fib_v2(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(fib(n));
        }
    }

    public int fib(int n){
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        else {
            return fib(n-1) + fib(n-2);
        }
    }

    // 3.对下面这个melo，主要取决于N*N*N，所以时间复杂度为N*N*N
    public void melo(int N){
        for(int i = 0; i < N*N; i++){
            System.out.println("Gelo is fruit pudding");
        }
        for(int i = 0; i < N*N*N; i++){
            System.out.println("Zo Two the Warriors");
        }
    }

    /* 4.根据函数可以写出操作数的递推表达式 C(N)=N+C(N/2)+2C(N/4)
    C(N/2)展开后的第一项为N/2  C(N/4)展开后的第一项为N/4  C(N/2)+2C(N/4)的第一项可以组成一个N
    画出递归树后，每一层的工作量都是N，树的深度为lgN，因此总的时间复杂度为N*lgN
     */
    public void grigobreath(int N){
        for(int i  = 0; i < N; i++){
            System.out.println("Gul-great");
        }
        grigobreath(N * 1/2);
        grigobreath(N * 1/4);
        grigobreath(N * 1/4);
    }

    /*
    对于下面这个问题，根节点执行N次循环，第1层有4个节点，每个执行N/2次，总共2N次，第2层4N次
    第k层操作(2^k)N次操作，总共有logN层，等比数列求和可得时间复杂度为N^2
    每个子问题的占比小于1，并且每层合并工作量等于1时时间复杂度就是NlogN，进一步可查阅Master Theorem
     */
    public void grigobreath_v2(int N){
        for(int i  = 0; i < N; i++){
            System.out.println("Gul-great");
        }
        grigobreath_v2(N * 1/2);
        grigobreath_v2(N * 1/2);
        grigobreath_v2(N * 1/2);
        grigobreath_v2(N * 1/2);
    }

    /*
    第0层N，第1层N/2，第2层N/4，第k层N/(2^k)，求和结果为2N，时间复杂度降为N
     */
    public void grigobreath_v3(int N){
        for(int i  = 0; i < N; i++){
            System.out.println("Gul-great");
        }
        grigobreath_v3(N * 1/4);
        grigobreath_v3(N * 1/4);
    }
}
