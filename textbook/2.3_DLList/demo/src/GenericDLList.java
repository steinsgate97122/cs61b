/*
DLList d2 = new DLList("hello");
之前的这版代码这么写了之后代码会直接崩溃，因为构造方法只能接收Integer的参数
为了解决此类问题，java在2004年加入了generics，语法是在类定义加上<>，里面塞上任意名称，之后希望使用任意类型的时候就用这个名称
加入generics后，在初始化类的时候语法也有变化，declaration的时候加上期望type，instantiation的时候加上空的<>
eg：DLList<String> d2 = new DLList<>("hello");    DLList<Integer> d1 = new DLList<>(5);
其实instantiation的时候加上type也是合法的，但没有必要，例如：DLList<Integer> d1 = new DLList<Integer>(5);
 */
public class GenericDLList<BleepHaoHao> {
    private IntNode sentinel;
    private int size;

    public class IntNode {
        public IntNode prev;
        public BleepHaoHao item;
        public IntNode next;
    }

    public static void main(String[] args) {
        GenericDLList<String> dlList = new GenericDLList<>();
    }
}