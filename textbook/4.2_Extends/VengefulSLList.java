public class VengefulSLList<Item> extends SLList<Item> {
    SLList<Item> deletedItems;

    // VengefulSLList需要记录通过removeLast移除的所有item，因此维护了一个SLList来维护这些信息
    public void printLostItems() {
        deletedItems.print();
    }

    // 重写一份removeLast，将remove的元素记录下来
    @Override
    public Item removeLast() {
        // super关键字可调用父类中被重写的方法，方便复用代码
        Item item = super.removeLast();
        deletedItems.addLast(item);
        return item;
    }

    public VengefulSLList() {
        super();  // 子类创建时先调用父类的无参构造来创建父对象，不过这一行去掉也可以，去掉的话java会默认在构造方法的第一行插入super()
        deletedItems = new SLList<Item>();
    }

    public VengefulSLList(SLList<Item> x) {
        super(x);  // 这一行一定要加，去掉的话java默认在第一行加上super()，而不是super(x)，不符合预期
        deletedItems = new SLList<Item>();
    }
}

/*
所有java的class都是Object类的子类，即使定义类的时候没有加上extends也一样，那么从Object继承了啥？
可参考：https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html
使用extends的时候，两个class必须满足is a的关系
 */
