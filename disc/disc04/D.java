public class D {
    public static void main(String[] args) {
        // A是父类，不能直接赋给子类
//        B a0 = new A();
//        a0.m1();
//        a0.m2(16);
        A b0 = new B();
        System.out.println(b0.x);  // 5
        b0.m1();  // 优先用B自己的，B没有m1()，所以找到A； Am1-> 5
        b0.m2();  // Bm2y-> 5
        // A里面没有m2(int y)这个方法
//        b0.m2(61);
        B b1 = new B();
        b1.m2(61);  // Bm2y-> 61
        b1.m3();  // Bm3-> called

        A c0 = new C();
        c0.m2();  // Cm2-> 5
        // 转成A赋给C非法
//        C c1 = (A) new C();
        A a1 = (A) c0;
        C c2 = (C) a1;
        c2.m3();  // Bm3-> called
        c2.m4();  // do nothing, m4语法本身有问题
        c2.m5();  // Cm5-> 6
        ((C) c0).m3();  // Bm3-> called
        // c0前面强转成了A，所以没有m3()
//        (C) c0.m3();
        b0.update();  // 无事发生，A的x变成99
        b0.m1();  // Am1-> 99
    }
}
