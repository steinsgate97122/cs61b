/*
对于package中的public和private成员，能否访问package中的private变量？子类能否访问父类的private变量？规则如下：
1、对于private，只有class自己的代码可以访问
2、对于package private（没有加上修饰符的情况下默认是这个），同一个package可以访问，但subclass不能访问
   说明：可以访问同一个package的一般是一个项目组的成员，这些人比较了解程序的内部逻辑，因此允许访问，但不希望外部继承这个类的用户来访问
3、对于protected，用一个package还有subclass都能访问，但其他的不行
4、public 这个就谁都能用了
一般只要发布的signature就不应该更改其表现，如果开发人员不希望其再被使用，会加上deprecated

顺带一提，接口里面所有的抽象方法都自动由public修饰，因为他需要让不同package且不满足子类关系的class访问
所以在interface内如果写了void start(); 也会自动变成 public abstract void start();
另外对于接口中的default方法，如果不写public也会自动加上public，与上面抽象方法不同的是default方法允许private，是给接口内部的其他default方法用的
 */
public class Demo02 {
    public static void main(String[] args) {
    }
}
