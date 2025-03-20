/*
先回顾一下interface的性质：
1、所有method需要public
2、所有成员变量需要public static final
3、不能实例化
4、所有method默认是抽象方法，除非加上了default修饰
5、每个class可以实现多个interface

下面的GraphicObject是一个abstract class，可以认为是一个介于interface和class之间的概念
1、method可以是public或者private
2、成员变量可以用任何关键字修饰
3、不能实例化
4、所有method默认是实现了的，除非加上abstract修饰
5、每个class只能实现1个abstract class

大体上abstract class能做的事情相比interface只多不少，分不清楚的话就interface就足够了
 */
public abstract class GraphicObject {
    public int x, y;

    public void moveTo(int newX, int newY) {
        // do something.
    }

    public abstract void draw();
    public abstract void resize();
}
