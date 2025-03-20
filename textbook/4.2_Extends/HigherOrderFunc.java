/*
def tenX(x):
    return 10*x

def do_twice(f, x):
    return f(f(x))
上面这段python代码中，do_twice是一个高阶函数，do_twice(tenX, 2)会调用两次tenX返回200
但java中的变量不允许存储指向函数的指针，但是可以存储接口，于是可以用接口继承来实现这个功能
 */
public class HigherOrderFunc {
    public interface IntUnaryFunction {
        /* apply函数接收一个整数，返回一个整数 */
        int apply(int x);
    }

    public class TenX implements IntUnaryFunction {
        /* 等效于上面python里面的tenX函数 */
        public int apply(int x) {
            return 10 * x;
        }
    }

    /*
    等效于上面python里面的do_twice函数，第一个参数接收一个interface
    需要调用的时候用do_twice(new TenX(), 2)即可
    */
    public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }
}
