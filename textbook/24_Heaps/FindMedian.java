import java.util.Comparator;
import java.util.PriorityQueue;

/*
目标是构造一个数据结构，insert的时间复杂度为O(logN)，find median的时间复杂度O(1)，delete median的时间复杂度也是O(1)
由于find median的时间复杂度要O(1)，那么median肯定是要维护在堆顶了，但是heap只支持max或者min，那么就要把元素对半分维护两个heap
较小的一半元素维护在一个maxHeap内，较大的一半元素维护在minHeap内，并且保证maxHeap内的元素比minHeap多1个或者一样多，就成功了
 */
public class FindMedian {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            // 更大的排在前面
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    public void insert(Integer i) {
        // 比较i与中位数的大小
        if (maxHeap.isEmpty() || maxHeap.peek() > i) {
            maxHeap.add(i);
        } else {
            minHeap.add(i);
        }

        // 接下来平衡两个heap的大小
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }
    }

    public Integer findMedian() {
        return maxHeap.peek();
    }

    public void deleteMedian() {
        Integer del = maxHeap.poll();

        // 平衡两个heap的大小
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.remove());
        }
    }
}
