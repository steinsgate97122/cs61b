import java.util.PriorityQueue;

/*
Suppose we wanted a data structure SortedStack that takes in integers, and
maintains them in sorted order. SortedStack supports two operations: push(int
i) and pop(). Pushing puts an int onto our SortedStack, and popping returns
the next smallest item in the SortedStack. For example, if we inserted 10, 4, 8,
2, 14, and 3 into a SortedStack, and then popped everything off, we would get
2, 3, 4, 8, 10, 14.
看上去维护一个PriorityQueue就行
 */
public class SortedStackV1 {
    PriorityQueue<Integer> priorityQueue;

    public SortedStackV1() {
        priorityQueue = new PriorityQueue<>();
    }

    public void push(int i) {
        priorityQueue.add(i);
    }

    public int pop() {
        if (priorityQueue.isEmpty()) {
            throw new RuntimeException("Stack is null!");
        }
        return priorityQueue.poll();
    }
}
