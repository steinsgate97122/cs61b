// extends用来在class之间定义继承关系，例如希望在SLList的基础上新增rotateRight，将最后一项变成第一项
// 与implements类似，RotatingSLList也是SLList
public class RotatingSLList<T> extends SLList<T> {
    public void rotateRight() {
        // 直接用了SLList内的方法，通过extends关键字，继承了parent class的所有members（成员变量、方法、嵌套类）
        // PS: 1、private不能访问 2、构造函数没有继承（细节在VengefulSLList里面解释）
        T item = removeLast();
        addFirst(item);
    }
}
