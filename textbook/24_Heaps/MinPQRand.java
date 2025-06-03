/*
在MinPQ的基础上，新增sample()和delRandom()，期望这两个方法在剩余所有元素中等概率选择所有元素返回
思路：底层维护的就是一个arr，在这个arr中随机获取一个索引就行，删除方法与delMin应该一致，也是将最后一个元素交换上来之后交换即可
 */
public interface MinPQRand<Key extends Comparable<Key>> {
    void insert(Key key);
    Key min();
    Key delMin();
    Key sample();
    Key delRandom();
}
