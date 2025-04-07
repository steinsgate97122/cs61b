import java.util.PriorityQueue;
import java.util.Stack;

/*
Suppose we wanted a data structure SortedStack that takes in integers, and
maintains them in sorted order. SortedStack supports two operations: push(int
i) and pop(). Pushing puts an int onto our SortedStack, and popping returns
the next smallest item in the SortedStack. For example, if we inserted 10, 4, 8,
2, 14, and 3 into a SortedStack, and then popped everything off, we would get
2, 3, 4, 8, 10, 14.
材料上建议用2个Stack，其中一个维护排序后的stack，另外一个用来临时放数据
 */
public class SortedStack {
    Stack<Integer> stackA;
    Stack<Integer> stackB;

    public SortedStack() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int i) {
        while (!stackA.isEmpty() && stackA.peek() < i) {
            stackB.push(stackA.pop());
        }
        stackA.push(i);
        while (!stackB.isEmpty()) {
            stackA.push(stackB.pop());
        }
    }

    public int pop() {
        return stackA.pop();
    }
}
