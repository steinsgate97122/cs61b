import java.util.LinkedList;

public class Demo01AbstractDataType {
    /*
    Write a Stack class using a Linked List as its underlying data structure.
    You only need to implement a single function: push(Item x).
    Make sure to make the class generic with "Item" being the generic type!
    我下面这种实现称为Delegation，是创建了一个LinkedList对象，用这个对象的方法来实现目标
    另外2种实现方法：1、MyStack继承LinkedList 2、构造方法中接收一个List对象，用来给innerList初始化，本质上也是Delegation
    就下面这个例子，Extension和Delegation看上去可等价替换
    但Extension需要完全知道父类的实现，并且子类与父类的行为类似
    而Delegation不关心父类的实现，只是借助其方法实现某些功能
     */
    public static class MyStack<Item> {
        private LinkedList<Item> innerList;

        public MyStack() {
            innerList = new LinkedList<>();
        }

        public void push(Item x) {
            innerList.add(x);
        }
    }
}
