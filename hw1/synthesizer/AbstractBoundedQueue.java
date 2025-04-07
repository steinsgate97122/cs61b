package synthesizer;

/*
继承了接口，但没有实现接口里面的abstract method，就是abstract class
protected关键字是为了让同一个package和子类使用该成员变量
abstract class与interface的区分比较微妙，这里直接用AbstractBoundedQueue作为顶层抽象也没啥问题
实际java Library的结构关系会很长，比如Collection接口，继承了Iterable，然后Map List等接口由继承了Collection
这些接口又有对应的abstract class，例如AbstractMap AbstractList，但小型项目没必要这样
 */
public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
}
