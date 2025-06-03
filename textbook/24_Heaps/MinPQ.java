/** (Min) Priority Queue: Allowing tracking and removal of the
 * smallest item in a priority queue. */
public interface MinPQ<Item> {
    /** Adds the item to the priority queue. */
    public void add(Item x);
    /** Returns the smallest item in the priority queue. */
    public Item getSmallest();
    /** Removes the smallest item from the priority queue. */
    public Item removeSmallest();
    /** Returns the size of the priority queue. */
    public int size();
}
/*
对于Un-harmonious Texts问题，希望追踪每天最有问题的M条message
比较不太合适的方法是将一天的所有message都存储在一个list内，然后sort出前M条
更加合适的是使用MinPQ，当其size等于M后，每次add元素时都removeSmallest，这样内存开销小很多

下一个问题就是MinPQ怎么实现？
1）用orderedArr？
假设将最小的放在arr[0]，那么获取smallest是O(1)
但remove时需要移动后面的所有元素，不太合适，add的时候为了维持顺序也可能要挪动所有元素，所以这个方法不太好
2）用Bushy BST？
对于多个元素优先级相同的情况不好处理，BST需要避免重复元素，或者强行加一个属性让两个元素不等
删除场景也类似，如果有多个元素都是最低优先级，需要人为规定这些元素之间的大小，所以用BST来实现很不自然
3）用HashTable？
也不太好，元素在table内的索引都是随机的，找到smallest非常困难，可能要遍历所有元素，复杂度变成了O(N)
 */