import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class HashCollisionFind {
    // Find 2 strings in Java that hash to the same value
    public static void findStrings() {
        // 用哈希值作为key
        HashMap<Integer, String> seen = new HashMap<>();
        char[] charArr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        Queue<String> queue = new LinkedList<>();
        queue.add("");

        while (!queue.isEmpty()) {
            String cur = queue.poll();

            if (cur.length() > 3) {
                continue;
            }

            int curHash = cur.hashCode();

            if (seen.containsKey(curHash)) {
                System.out.println("string1:" + seen.get(curHash));
                System.out.println("string2:" + cur);
                return;
            }

            seen.put(curHash, cur);
            for (char c : charArr) {
                queue.add(cur + c);
            }
        }
    }

    public static void main(String[] args) {
        findStrings();
    }
}
