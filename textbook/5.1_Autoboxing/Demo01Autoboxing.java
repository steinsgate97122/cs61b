import java.util.ArrayList;

/*
类定义时可以使用<>来使用泛型变量 generic type
当实例化对象时，需要用具体的类来替换泛型
对于java的8个primitive type，ArrayDeque<int>是错误语法，需要使用ArrayDeque<Integer>
byte -> Byte
short -> Short
int -> Integer
long -> Long
float -> Float
double -> Double
boolean -> Boolean
char -> Character
于是，实际的代码应该形如：
    ArrayList<Integer> L = new ArrayList<Integer>();
    L.add(new Integer(5));
    L.add(new Integer(6));
    int first = L.get(0).valueOf();
不过，java可以implicitly convert between primitive and wrapper types，所以代码形如下面这样就行：
    ArrayList<Integer> L = new ArrayList<Integer>();
    L.add(5);
    L.add(6);
    int first = L.get(0);
如果需要Integer，但实际提供了int，那么会自动执行new Integer(20)
如果需要int，但实际提供了Integer，也会自动调用valueOf()方法
 */
public class Demo01Autoboxing {
    public static void main(String[] args) {
        ArrayList<Integer> L = new ArrayList<Integer>();
        L.add(5);
        L.add(6);
        int first = L.get(0);
        /*
        注：
        1、数组没有auto-box和auto-unbox功能，int[]不允许直接放进Integer[]里面
        2、auto-box和auto-unbox会影响性能
        3、包装类也比primitive type占用更多内存空间
         */
    }
}
