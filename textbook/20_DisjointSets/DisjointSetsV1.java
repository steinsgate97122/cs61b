import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
下面维护了一个Map<Integer, Set<Integer>>，每个元素对应一个Set，connect的时候合并Set，检查是否connect比较方便
 */
public class DisjointSetsV1 {
    private Map<Integer, Set<Integer>> integerSetMap;

    public DisjointSetsV1(int[] intArr) {
        integerSetMap = new HashMap<>();
        for (int i : intArr) {
            integerSetMap.put(i, Collections.singleton(i));
        }
    }

    void connect(int p, int q) {
        // 获取p和q的集合，把q集合中的元素都塞进p集合内，q的集合变成p集合
        Set<Integer> pSet = integerSetMap.get(p);
        Set<Integer> qSet = integerSetMap.get(q);
        if (!pSet.equals(qSet)) {
            pSet.addAll(qSet);
        }
        integerSetMap.put(q, pSet);
    }

    boolean isConnected(int p, int q) {
        Set<Integer> pSet = integerSetMap.get(p);
        return pSet.contains(q);
    }
}
