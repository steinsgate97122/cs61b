/*
类似auto-boxing，对于一个primitive类型java可以自动加宽
例如方法需要一个T2的基本类型，但提供了T1类型，当T2的值范围大于T1时，变量会被隐式转换为T2
但如果需要一个T1的基本类型，提供了T2，就需要使用cast来显示转换了
 */
public class Demo02Widen {
    public static void blahDouble(double x) {
        System.out.println(x);
    }

    public static void blahInt(int x) {
        System.out.println(x);
    }

    public static void main(String[] args) {
        int x = 20;
        // 下面这行代码等价于Demo02Widen.blahDouble((double) x)
        Demo02Widen.blahDouble(x);

        double y = 10;
        Demo02Widen.blahInt((int) y);
    }
}
