// AList和SLList都是List61B
// 需要明确的是inheritance is multi-generational，AList不仅继承了List61B的方法，也继承了List61B的父类的方法
public interface List61B<Item> {
    public void addFirst(Item x);
    public void addLast(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();

    // method signature中加上default，就可以在interface里面定义方法，所有实现这个接口的都可以使用这个method
    // 不过使用SLList时，get()会遍历整个list，效率较差，所以在SLList中可以Override这个print方法
    default public void print() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}

/*
Interface Inheritance（接口继承），只规定子类需要做的事情，而不提供具体实现，例如上面的size()
Implementation Inheritance（实现继承），除了规定子类需要做的事情外，还提供具体实现，例如上面直接给出print的实现
相比之下，Implementation Inheritance更强调代码复用，可能会使代码复杂度提升，例如父类实现的变化可能会影响所有子类
按照OOD原则，组合优于继承，可以将所需的类维护为成员对象，仅需调用他们方法即可；当Override方法时，父方法重写后，子方法很可能也要重写
 */
