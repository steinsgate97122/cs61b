public class Combine {
    public static int combine(ComFunc f, int[] x) {
        return helper(f, x, 0);
    }

    private static int helper(ComFunc f, int[] x, int index) {
        if (index >= x.length) {
            return 0;
        }
        if (index == x.length - 1) {
            return x[index];
        }
        return f.apply(x[index], helper(f, x, index + 1));
    }
}
