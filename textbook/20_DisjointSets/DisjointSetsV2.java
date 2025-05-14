import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
比V1版本麻烦，虽然List<Set<Integer>>占用空间少了，但要判断p在哪个集合里面更加麻烦了
 */
public class DisjointSetsV2 {
    private List<Set<Integer>> integerSetList;

    public DisjointSetsV2(int[] intArr) {
        integerSetList = new ArrayList<>();
        for (int i : intArr) {
            integerSetList.add(Collections.singleton(i));
        }
    }

    void connect(int p, int q) {
        Set<Integer> pSet = null;
        Set<Integer> qSet = null;
        for (Set<Integer> integerSet : integerSetList) {
            if (integerSet.contains(p)) {
                pSet = integerSet;
            }
            if (integerSet.contains(q)) {
                qSet = integerSet;
            }
        }
        // 非同一个集合时，合并集合
        if (pSet != null && qSet != null && !pSet.equals(qSet)) {
            pSet.addAll(qSet);
        }
        integerSetList.remove(qSet);
    }

    boolean isConnected(int p, int q) {
        for (Set<Integer> integerSet : integerSetList) {
            if (integerSet.contains(p) && integerSet.contains(q)) {
                return true;
            }
        }
        return false;
    }
}
