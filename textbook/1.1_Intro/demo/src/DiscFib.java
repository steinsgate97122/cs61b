public class DiscFib {
    public static int fib(int n) {
        int index = 2;
        int cur = 1;
        int previous = 0;
        while (n - index > 0) {
            int temp = cur;
            cur = cur + previous;
            previous = temp;
            index += 1;
        }
        return cur;
    }

    /** n表示前1个数, k表示当前数, f0临时存下一个, f1代表第几个斐波那契数 */
    public static int fib2(int n, int k, int f0, int f1) {
        for (int i = 2; i < f1; i++) {
            f0 = n + k;
            n = k;
            k = f0;
        }
        return n == 0 ? n : k;
    }

    /** 用递归也行, n代表第n个数, k从1开始追上n就结束, f0和f1分别维护当前和下一个斐波那契数*/
    public static int fib3(int n, int k, int f0, int f1) {
        if (n == k) {
            return f0;
        } else {
            return fib3(n, k + 1, f1, f0 + f1);
        }
    }

    public static void main(String[] args) {
        System.out.println(fib(9));
        System.out.println(fib2(0, 1, 0, 9));
        System.out.println(fib3(9, 1, 0, 1));
    }
}
