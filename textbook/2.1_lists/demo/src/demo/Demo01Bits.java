package demo;

public class Demo01Bits {
    public static void main(String[] args) {
        // 下面c和x的二进制存储均为01001000，但print时输出不同，因为两者有不同的type
        char c = 'H';
        int x = c;
        System.out.println(c);
        System.out.println(x);
    }
}

/*
int分配32位 double分配64位 与C不同的是，Java不支持查看存储的address，能避免一些乱七八糟的崩溃
赋值符号= 将右边的位信息复制到左边的变量，这个仅针对primitive type（基本类型）而不是reference type（引用类型）
在执行new demo.Walrus(1000, 8.3)后，创建了一个box来存储该对象，除此之外还创建了一个64位的box来存储刚才创建对象的内存地址
new语句的返回结果就是一个地址，关键字null其实就是分配了全0的地址，实际表示的时候用一个箭头指向

demo.Walrus a = new demo.Walrus(1000, 8.3);
demo.Walrus b;
b = a;

对于上面的例子，执行第二行后b为undefined，执行第三行就是将a的地址复制到了b，因此b和a指向了用一个实际的instance

另外java中的参数传递是pass by value，考虑下面的例子

double x = 5.5;
double y = 10.5;
double avg = average(x, y);

public static double average(double a, double b) {
    return (a + b) / 2;
}

调用函数时average函数有自己的scope，在其自己的scope内有两个box分别为a和b，类似的用=将位信息复制了过去
pass by value的含义是所有信息传递本质上都是复制了一份信息过去，完全不影响原先被复制的内容
java的对象引用本质上也是pass by value，例如传递了一个对象p，如果在函数内调用了p = new Person();也完全不影响原先被复制的引用
这个和C不同，C如果用指针可以改原变量的值，这个称为Pass by Reference，这种情况下是传递了变量的引用，所以会直接动变量

数组array也类似，是reference variable
int[] x;
x = new int[]{0, 1, 2, 95, 4};
第一行声明创建了64位的box，第二行通过new关键字创建了5个box，每个box有32位，并返回地址给x
假如之后使用x=null，可能就永久丢失了创建的数组，如果用完了的话丢到这个引用也没啥问题
 */
