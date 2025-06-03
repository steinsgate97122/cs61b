import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/*
目标是构造一个数据结构，insert的时间复杂度为O(logN)，deleteMax的时间复杂度O(logN)，deleteMin的时间复杂度O(logN)
答案是分别维护一个maxHeap和minHeap，并且在两个heap都放进所有元素
带来一个问题，从一个堆删除元素后，这个元素在另外一个堆内还在，并且heap这种数据结构不支持删除某个特定元素，那咋办
删不掉可以不删，再维护一个HashMap，key就是元素，value是这个元素当前是否生效，删除之后将这个元素标记为失效就可以了
当然heap内的元素可以重复，那么map的value改成一个Integer，每add一次这个计数就+1，只要计数还大于0就说明这个元素还可以弹出
当计数为0后，再碰见这个元素直接当成已经删掉的就可以了
 */
public class FindMaxMin {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    HashMap<Integer, Integer> itemCountMap = new HashMap<>();

    public void insert(Integer item) {
        maxHeap.add(item);
        minHeap.add(item);
        itemCountMap.put(item, itemCountMap.getOrDefault(item, 0) + 1);
    }

    public Integer deleteMax() {
        if (maxHeap.isEmpty()) {
            return null;
        }
        Integer delItem = null;
        while (delItem == null) {
            delItem = maxHeap.poll();
            if (delItem == null) {
                return null;
            }
            if (itemCountMap.get(delItem) > 0) {
                itemCountMap.put(delItem, itemCountMap.get(delItem) - 1);
            } else {
                delItem = null;
            }
        }
        return delItem;
    }

    public Integer deleteMin() {
        if (minHeap.isEmpty()) {
            return null;
        }
        Integer delItem = null;
        while (delItem == null) {
            delItem = minHeap.poll();
            if (delItem == null) {
                return null;
            }
            if (itemCountMap.get(delItem) > 0) {
                itemCountMap.put(delItem, itemCountMap.get(delItem) - 1);
            } else {
                delItem = null;
            }
        }
        return delItem;
    }
}
