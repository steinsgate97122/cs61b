package exercise;

public class BreakContinue {
    /**
     * each element a[i] with the sum of a[i] through a[i + n], but only if a[i] is positive valued
     * @param a array
     * @param n sum number
     */
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                continue;
            }
            int sum = a[i];
            for (int j = i + 1; j < a.length; j++) {
                sum += a[j];
                if (j == i + n) {
                    break;
                }
            }
            a[i] = sum;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
