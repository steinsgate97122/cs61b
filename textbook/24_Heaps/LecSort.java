import java.util.PriorityQueue;

public class LecSort {
    // 附加条件：no element is more than k slots away from its position in the sorted array
    public static void zorkSort(int[] A, int k) {
        int i;
        int n = A.length;
        i = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (i < k) {
            pq.add(A[i]);
            i += 1;
        }
        while (i < n) {
            A[i - k] = pq.remove();
            pq.add(A[i]);
            i += 1;
        }
        while (i < n + k) {
            A[i - k] = pq.remove();
            i += 1;
        }
    }
    /* 上面这个函数的running time? 第一次循环，klogk，第二次(n-k)logk，第三次klogk，加起来就是(n+k)logk
    但还有个条件 k<<n 所以直接写nlogk就行了
     */
}
