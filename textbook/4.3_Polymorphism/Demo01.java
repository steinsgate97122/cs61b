/*
假定在python代码中希望实现一个功能：prints a string representation of the larger of two objects
有以下2种实现方式：
    def print_larger(x, y, compare, stringify):
        if compare(x, y):
            return stringify(x)
        return stringify(y)

    def print_larger(x, y):
        if x.largerThan(y):
            return x.str()
        return y.str()

前一种使用高阶函数，编写了一种通用的输出larger string的方法
后一种larger由对象自己判断，largerThan取决于x和y的对应方法如何定义
 */
public class Demo01 {
    /*
    public static Object max(Object[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            // 下面这段有问题，两个Object之间不能用>比较，因此会出现compilation error
            if (items[i] > items[maxDex]) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }
     */

    /*
    退而求其次，只能类似下面这样写一个接收参数为Dog[]的方法，但这样的话对于每个类都要写一个类似的方法，造成冗余代码
    前面的max无法使用本质上就是因为两个Object之间用>回编译错误，如果是python的话可以在类内部定义__gt__来重载>符号
    c++中也有类似的能力，但java不让这么干，只能用接口继承来实现类似功能，可以写一个接口，Dog类实现这个接口即实现了比较方法
     */
    public static DogV1 maxDog(DogV1[] dogs) {
        if (dogs == null || dogs.length == 0) {
            return null;
        }
        DogV1 maxDog = dogs[0];
        for (DogV1 d : dogs) {
            if (d.size > maxDog.size) {
                maxDog = d;
            }
        }
        return maxDog;
    }

    /*
    接收的对象都实现了OurComparable接口，this.compareTo(o)，this大于o时返回正数
     */
    public static Object max(OurComparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (items[i].compareTo(items[maxDex]) > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        DogV1[] dogs = {new DogV1("Elyse", 3), new DogV1("Sture", 9), new DogV1("Benjamin", 15)};
        DogV1 maxDog = (DogV1) max(dogs);
//        DogV1 maxDog = (DogV1) maxDog(dogs);
        maxDog.bark();
    }
}
