import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Disc02a {
    /*
    Given an array of integers A and an integer k, return true if any two numbers
    in the array sum up to k, and return false otherwise. How would you do this?
    Give the main idea and what ADT you would use.
    A中是否存在a+b=k? 转换为对于A内的任意元素a，是否存在b=k-a? 用Set来实现是最快的
     */
    public boolean twoSum(Set<Integer> A, Integer k) {
        for (Integer item : A) {
            Integer res = k - item;
            if (A.contains(res)) {
                return true;
            }
        }
        return false;
    }

    /*
    上面的实现有点问题，接收的参数应该是int[] A，那么在过程中构建Set即可
     */
    public boolean twoSum(int[] intArr, Integer k) {
        Set<Integer> intSet = new HashSet<>();
        for (int item : intArr) {
            if (intSet.contains(k - item)) {
                return true;
            }
            intSet.add(item);
        }
        return false;
    }

    /*
    Find the k most common words in a document. Assume that you can represent
    this as an array of Strings, where each word is an element in the array. You
    might find using multiple data structures useful.
    document已经用一个String[]来表示，要统计词频，应该用Map结构，value表示出现频率
    构建Map的过程中记录下最大value和对应的key，就能找到词频最高的word
    上面的方法只适合找到最高频率的key
     */
    public String docTopWord(String[] words) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        int topFreq = 0;
        String resStr = "";
        for (String word : words) {
            int freq = wordFrequency.getOrDefault(word, 0) + 1;
            wordFrequency.put(word, freq);
            if (freq > topFreq) {
                topFreq = freq;
                resStr = word;
            }
        }
        return resStr;
    }

    /*
    上面只实现了top1的情况，如果要实现k most common，可以将word都装进PriorityQueue
    Priority通过词频来判断，确定优先级后，poll()上k次就可
     */
    public static List<String> docTopK(String[] words, int k) {
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            int freq = wordFrequency.getOrDefault(word, 0) + 1;
            wordFrequency.put(word, freq);
        }
        PriorityQueue<String> wordPriorityQueue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 返回正值对应o1在o2之后，即o1词频小于o2，所以o2-o1
                return wordFrequency.get(o2) - wordFrequency.get(o1);
            }
        });
        wordPriorityQueue.addAll(wordFrequency.keySet());
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(wordPriorityQueue.poll());
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"Find", "the", "k", "most", "common", "words", "the", "the", "words", "k"};
        List<String> strings = docTopK(words, 3);
        System.out.println(strings);
    }
}
