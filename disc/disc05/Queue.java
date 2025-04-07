import java.util.Stack;

/*
Define a Queue class that implements the push and poll methods of a queue ADT
using only a Stack class which implements the stack ADT.
 */
public class Queue<T> {
    private Stack<T> myStack;

    public Queue() {
        myStack = new Stack<>();
    }

    public void push(T x) {
        myStack.push(x);
    }

    /*
    用到一个临时stack存储pop出来的元素
     */
    public T poll() {
        Stack<T> tmpStack = new Stack<>();
        while (!myStack.isEmpty()) {
            tmpStack.push(myStack.pop());
        }
        T result = tmpStack.pop();
        while (!tmpStack.isEmpty()) {
            myStack.push(tmpStack.pop());
        }
        return result;
    }

    /*
    如果用递归实现poll，需要一个递归表达式？
    假设接收的参数为当前栈顶元素 和 去除栈顶元素的剩余stack，helper函数返回栈底元素，并恢复栈
     */
    public T pollV2() {
        return helperPoll(myStack.pop());
    }

    private T helperPoll(T prevTopItem) {
        if (myStack.isEmpty()) {
            // empty的时候，prevTopItem就是需要返回的元素，并且不需要将这个元素放回栈
            return prevTopItem;
        }
        T result = helperPoll(myStack.pop());
        myStack.push(prevTopItem);
        return result;
    }
}
