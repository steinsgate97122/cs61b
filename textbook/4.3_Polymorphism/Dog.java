import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    public String name;
    public int size;

    public Dog(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void bark() {
        System.out.println(name + " woof");
    }

    /*
    直接实现Comparable的compareTo方法，这种排序称为Natural order
    Dog的natural order就是根据size排序，如果希望实现其他的排序方式，可以定义一个内部类，这个类实现Comparator接口
     */
    @Override
    public int compareTo(Dog o) {
        return this.size - o.size;
    }

    /*
    Comparator接口需要实现compare方法，规则与compareTo方法一致：
    1、当o1 < o2时，返回负数
    2、当o1 == o2时，返回0
    3、当o1 > o2时，返回正数
    由于NameComparator不需要使用外部的任何属性，所以定义成static
     */
    private static class NameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog o1, Dog o2) {
            // String类已经定义了compareTo方法，可以直接用
            return o1.name.compareTo(o2.name);
        }
    }

    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}

/*
总结：
先考虑callback这种设计模式，是指A函数在执行过程中调用了B函数，而B函数的行为需要在A函数调用时定义
核心思想是由调用方来定义执行逻辑
例如在python中，函数直接作为参数传递，这种情况下就是回调
而java中，由于不能直接传递函数，因此才通过接口实现回调，上面的compareTo方法是在对象中定义，实际上不是典型的回调
假设max接收了一个Comparator接口，然后用匿名函数直接在调用时实现compare()，这样算是典型的callback，比如下面这段
int result = max(5, 3, (a, b) -> a - b);
回到public static Comparable max(Comparable[] items) 这个max函数，函数内部需要compareTo方法，而这个未必已经实现了
不过接口指定了调用方需要实现compareTo方法，因此在max函数内容就能直接使用
Comparable接口可以当成是我本身希望和其他对象比较
Comparator接口更加像是一个第三方工具，来比较2个对象
 */
