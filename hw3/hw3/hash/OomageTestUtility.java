package hw3.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        // key是bucket编号，value是bucket内的元素数量
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Oomage oomage : oomages) {
            int hashCode = oomage.hashCode();
            int hashIndex = (hashCode & 0x7FFFFFFF) % M;
            hashMap.put(hashIndex, hashMap.getOrDefault(hashIndex, 0) + 1);
        }
        int N = oomages.size();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            Integer freq = entry.getValue();
            if (freq < N / 50 || freq > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
