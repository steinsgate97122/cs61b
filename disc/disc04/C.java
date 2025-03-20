public class C extends B {
    public int y = x + 1;

    public void m2() {
        System.out.println("Cm2-> " + super.x);
    }

    public void m4() {
        // java中不允许用super.super这样的语法直接访问组父类，只能访问直接父类，不允许的原因是为了封装性
//        System.out.println("Cm4-> " + super.super.x);
    }

    public void m5() {
        System.out.println("Cm5-> " + y);
    }
}
