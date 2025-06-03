/*
需要一个min-based priority queue，min()需要返回最小元素中最早插入的那个
显然，维护一个heap，再封装一个对象加入插入序号，序号用一个全局计数器递增就可以，下面用English来表达一下
maintain a min-heap where each entry is a pair(key, timestamp), where timestamp is a unique counter
that increases with each insertion. The heap compares entries first by key, and in case of equal keys,
by timestamp, giving priority to the one inserted earlier.
 */
public interface StableMinPQ<Key extends Comparable<Key>> {
    public Key min();

    public Key delMin();

    public void insert(Key key);

    public boolean isEmpty();
}
