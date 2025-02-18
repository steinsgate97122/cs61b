package exercise;

public class Maximum {
    /** Returns the maximum value from m. Assume that all of the numbers are greater than or equal to zero */
    public static int max(int[] m) {
        int res = 0, index = 0;
        while (index < m.length) {
            if(m[index] > res) {
                res = m[index];
            }
            index += 1;
        }
        return res;
    }

    public static int maxV2(int[] m) {
        int res = 0;
        for (int i = 0; i < m.length; i++) {
            if(m[i] > res) {
                res = m[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
        System.out.println(maxV2(numbers));
    }
}
