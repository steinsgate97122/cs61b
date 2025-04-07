package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Create a buffer with capacity = SR / frequency.
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        // buffer should be initially filled with zeros
        while (!buffer.isFull()) {
            buffer.enqueue((double) 0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in the buffer
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while (!buffer.isFull()) {
            // generate a random number between -0.5 and 0.5
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * ringBuffer模拟了能量来回传递的介质，其长度决定了产生声音的频率，DECAY模拟了波在穿过弦时的轻微能量损耗
     * average运算可看作一个低通滤波器，模拟了弹拨吉他的动作
     * 所以pluck相当于弦的初始化状态，tic模拟了弦波动后震动的表现
     */
    public void tic() {
        double newSample = (buffer.dequeue() + buffer.peek()) / 2 * DECAY;
//        newSample = -newSample;  // 变为harp声
        buffer.enqueue(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
