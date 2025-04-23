public class Demo03 {
    public static void f4(int N) {
        if (N == 0) {
            return;
        }
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        f4(N / 2);
        g(N);
    }

    public static void g(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.println("1");
            }
        }
    }

    public static void p5(int N) {
        for (int i = 1; i < (N * N); i *= 2) {
            for (int j = 0; j < i; j++) {
                System.out.println("moo");
            }
        }
    }
}
