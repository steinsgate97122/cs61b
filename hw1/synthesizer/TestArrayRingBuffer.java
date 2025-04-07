package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        Assert.assertEquals(arb.dequeue().intValue(), 1);
        Assert.assertEquals(arb.dequeue().intValue(), 2);
        Assert.assertEquals(arb.peek().intValue(), 3);
        Assert.assertEquals(arb.dequeue().intValue(), 3);
    }

    @Test
    public void moreTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(4);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        List<Integer> t = new ArrayList<>();
        for (Integer i : arb) {
            t.add(i);
        }
        Assert.assertEquals(t.get(0).intValue(), 1);
        Assert.assertEquals(t.get(1).intValue(), 2);
        Assert.assertEquals(t.get(2).intValue(), 3);
        Assert.assertEquals(arb.peek().intValue(), 1);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
