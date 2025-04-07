public class Add implements ComFunc {
    public int apply(int a, int b) {
        return a  + b;
    }

    public static int sumAll(int[] x) {
        return Combine.combine(new Add(), x);
    }

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4};
        System.out.println(sumAll(x));
    }
}
