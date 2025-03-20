public class TypeCheck {
    public static void main(String[] args) {
        VengefulSLList<Integer> vs1 = new VengefulSLList<>();
        SLList<Integer> s1 = vs1;  // 由于VengefulSLList就是SLList，因此可以赋值

        s1.addLast(50);
        s1.removeLast();  // removeLast被VengefulSLList重写了，所以执行的是重写后的版本

        // 下面这行编译报错，printLostItems对于SLList不存在，虽然s1的dynamic type为VengefulSLList，但static type还是SLList
//        s1.printLostItems();
        // SLList类型不能赋值给VengefulSLList，不过java中可以通过casting来进行强制转换，这样相当于强制让编译器忽略检查
//        VengefulSLList<Integer> vs2 = s1;
        VengefulSLList<Integer> vs2 = (VengefulSLList<Integer>) s1;  // 像这样使用强制转换，如果转换失败会在运行时报ClassCastException
    }
}
