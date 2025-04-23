public class DemoExample {
    // Objective: Determine if a sorted array contains any duplicates.

    // Silly Duplicate: compare everything
    public static boolean dup1(int[] A) {
        for (int i = 0; i < A.length; i += 1) {
            for (int j = i + 1; j < A.length; j += 1) {
                if (A[i] == A[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // Better Duplicate: compare only neighbors
    public static boolean dup2(int[] A) {
        for (int i = 0; i < A.length - 1; i += 1) {
            if (A[i] == A[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
/*
方法1：直接运行比较时间，随着数组大小增加，可以发现dup1需要的时间更长
好处是衡量方便，含义清晰，坏处是测试时间太久了，根据不同的machine、compiler、input的效果都不同
方法2A：对一个N=10000的array，计算其操作数（赋值、加法、...）
好处是与机器无关，坏处首先计算麻烦，操作数本身不代表实际时间，某些操作的时间可能更快
方法2B：还是计算操作数，N改成变量
好处和2A一样，而且进一步可得知算法与N的关系，坏处是计算起来更加麻烦了
对比dup2和dup1，发现dup2更好，因为N²的增长率高于N，称其具有更好的asymptotic runtime behavior
对于一段代码，可将其运行时间表示为R(N)，其中N代表函数input的某些属性，一般N就是input size
并不实际计算R(N)，C(N)表示对应操作数，计算C(N)的order of growth f(N)，即C(N)属于Big-Theta(f(N))
如果操作占用常数时间，那么R(N)属于Big-Theta(f(N))
 */