public class Demo {
    /*
    immutability（不变性）
    定义：An immutable data type is a data type whose instances cannot change in any observable way after instantiation
    例如，String是不变的，在实例化一个String后，调用其任何方法，不会修改其本身；ArrayDeque、Planet都是可变的类
    只要是非private修饰的变量都是可变的，final修饰的变量不可变，不过要保证不可变性不一定需要final修饰
    final修饰变量后，在这个变量第一次赋值后，就不再可变，例如下面Date类，实例化后不可修改任意一个变量

    注：final定义对象后，只能保证引用不可变，指向的对象完全可以改变；用反射的话private修饰的变量也可变，这里假定不用这种语法
    加上final修饰后，不用担心这个变量在什么地方被偷偷摸摸改掉；天生线程安全（final变量修改过程中其他线程不可访问）；也能提高性能
     */
    public static class Date {
        public final int month;
        public final int day;
        public final int year;
        private boolean contrived = true;
        public Date(int m, int d, int y) {
            month = m; day = d; year = y;
        }
    }
}
