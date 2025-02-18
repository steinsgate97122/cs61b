package exercise;

public class Triangle {

    public static void drawTriangle(int n) {
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= i; c++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle(10);
//        for (int i = 1; i <= 5; i++) {
//            for (int c = 1; c <= i; c++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
    }
}
