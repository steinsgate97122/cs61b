package demo;

public class IntList {
    // 61A中很常见的数据结构，叫Linked List（链表），理论上只需要构造方法就能满足需求，但使用起来很麻烦也容易出问题
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size() {
        // 用recursion来写，没必要加上if(this==null)的判断，this为null的话根本调用不了这个方法
        if (rest == null) {
            return 1;
        }
        return this.rest.size() + 1;
    }

    public int iterativeSize() {
        int count = 1;
        IntList tmp = rest;  // this不能重新赋值，所以另外创建一个来遍历
        while (tmp != null) {
            count += 1;
            tmp = tmp.rest;
        }
        return count;
    }

    public int get(int i) {
        // 获取第i个元素，可以假定i是合法数据
        IntList tmp = this;
        for (int j = 0; j < i; j++) {
            tmp = tmp.rest;
        }
        return tmp.first;
    }

    public int getRe(int i) {
        // 当然也可以用递归来写
        if (i == 0) {
            return first;
        }
        return rest.getRe(i - 1);
    }

    public static void main(String[] args) {
        // 正向构建，比较丑
        IntList L = new IntList(5, null);
        L.rest = new IntList(10, null);
        L.rest.rest = new IntList(15, null);

        // 反向构建，理解麻烦一些
        L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);
        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));

        System.out.println(L.getRe(0));
        System.out.println(L.getRe(1));
        System.out.println(L.getRe(2));
    }
}