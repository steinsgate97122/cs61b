import java.util.ArrayList;
import java.util.List;

public class Demo02Views {
    /*
    下面这个例子中，通过View的设计模式很好的封装设计了一个API
    View（视图），某些方法返回的不一定是一个全新的copy，而是原对象的view
    比如，下面这个例子中，subList就得到了原List的一个view，通过这个view可以查看修改一部分原始数据
    可以看一下ArrayList中subList方法的实现，借助了一个class SubList<E> extends AbstractList<E>
    它在原List的基础上维护了一个offset，不通过复制，也能隔离出了一部分来操作
    subList()通过视图，实现【看上去是一个新对象，背后还是在操作原对象】
    这种方式更加方便局部操作，例如希望Collections.reverse(myList.subList(100, 200));
    现在这个方法直接对myList中的数据也直接生效，用户其实不需要知道这个方法返回的是一个view，当成List用就行
     */
    public static void main(String[] args) {
        List<String> fullList = new ArrayList<>();
        List<String> sub = fullList.subList(1, 4);
        sub.set(0, "abc");
    }
}
