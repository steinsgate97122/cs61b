import org.junit.Assert;
import org.junit.Test;

public class TestSortedStack {
    @Test
    public void testV1() {
        SortedStackV1 stack = new SortedStackV1();
        stack.push(10);
        stack.push(4);
        stack.push(8);
        stack.push(2);
        stack.push(14);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 2);
        Assert.assertEquals(stack.pop(), 3);
        Assert.assertEquals(stack.pop(), 4);
        Assert.assertEquals(stack.pop(), 8);
        Assert.assertEquals(stack.pop(), 10);
        Assert.assertEquals(stack.pop(), 14);
    }

    @Test
    public void test() {
        SortedStack stack = new SortedStack();
        stack.push(10);
        stack.push(4);
        stack.push(8);
        stack.push(2);
        stack.push(14);
        stack.push(3);
        Assert.assertEquals(stack.pop(), 2);
        Assert.assertEquals(stack.pop(), 3);
        Assert.assertEquals(stack.pop(), 4);
        Assert.assertEquals(stack.pop(), 8);
        Assert.assertEquals(stack.pop(), 10);
        Assert.assertEquals(stack.pop(), 14);
    }
}
