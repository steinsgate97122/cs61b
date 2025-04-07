import org.junit.Assert;
import org.junit.Test;

public class TestQueue {
    @Test
    public void testQueue() {
        Queue<String> myQueue = new Queue<>();
        myQueue.push("s");
        myQueue.push("h");
        myQueue.push("o");
        Assert.assertEquals("s", myQueue.poll());
        Assert.assertEquals("h", myQueue.poll());
        myQueue.push("w");
        Assert.assertEquals("o", myQueue.poll());
        Assert.assertEquals("w", myQueue.poll());
    }

    @Test
    public void testQueueV2() {
        Queue<String> myQueue = new Queue<>();
        myQueue.push("s");
        myQueue.push("h");
        myQueue.push("o");
        Assert.assertEquals("s", myQueue.pollV2());
        Assert.assertEquals("h", myQueue.pollV2());
        myQueue.push("w");
        Assert.assertEquals("o", myQueue.pollV2());
        Assert.assertEquals("w", myQueue.pollV2());
    }
}
