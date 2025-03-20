/*
由于同名类的存在，实际使用时需要将class的web地址写出来，形如：
ug.joshh.animal.Dog d = new ug.joshh.animal.Dog()
这样写有点恶心，所有在把package先import进去：import ug.joshh.animal.Dog;
之后就能直接用Dog了
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
考虑Deque，这个接口有ArrayDeque、LinkedListDeque这两种实现，其中Deque提供了一系列方法，具体实现放在ArrayDeque、LinkedListDeque内
Java中将Deque称为接口，理论上将其称为Abstract data type（抽象数据类型），由于Deque仅定义了方法的表现，因此是抽象的
在java.util这个Library中，有以下3种重要的ADT：
1、List（有序项的集合）https://docs.oracle.com/javase/8/docs/api/java/util/List.html
    List的一种常见实现类是ArrayList https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
2、Set（无序唯一项的集合） https://docs.oracle.com/javase/7/docs/api/java/util/Set.html
    Set的一种常见实现类是HashSet https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html
3、Map（key/value项的集合，可通过key访问value） https://docs.oracle.com/javase/8/docs/api/java/util/Map.html
    Map的一种常见实现类是HashMap https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
上面三个ADT都继承自Collection接口，Collection接口的定义比较含糊，java将其称为:
                    "represent a group of objects, known as its elements"
 */
public class Demo01Libraries {
    /*
    Write a method getWords that takes in a String inputFileName
    and puts every word from the input file into a list.
    Recall how we read words from a file in proj0. *Hint: use In
     */
    public List<String> getWords(String inputFileName) {
        In in = new In(inputFileName);
        List<String> result = new ArrayList<>();
        while (!in.isEmpty()) {
            result.add(in.readString());
        }
        return result;
    }

    /*
    Write a method countUniqueWords that takes in a List<String>
    and counts how many unique words there are in the file.
     */
    public int countUniqueWords(List<String> strList) {
        Set<String> uniqueWords = new HashSet<>();
        for(String item : strList) {
            uniqueWords.add(item);
        }
        int uniqueWordCount = uniqueWords.size();
        return uniqueWordCount;
    }

    /*
    Write a method collectWordCount that takes in a List<String> targets
    and a List<String> words and finds the number of times each target word appears in the word list.
     */
    public Map<String, Integer> collectWordCountV1(List<String> targets, List<String> words) {
        Map<String, Integer> result = new HashMap<>();
        for (String target : targets) {
            if (result.containsKey(target)) {
                // 出现重复target时，直接跳过
                continue;
            }
            int count = 0;
            for (String word : words) {
                if (word.equals(target)) {
                    count += 1;
                }
            }
            result.put(target, count);
        }
        return result;
    }

    /*
    下面这样不需要2层循环，2次遍历就行
    相比python代码直接用result = {}定义字典看上去麻烦很多，但java这样也存在优势
    1、java是静态类型，编译时会进行类型检查，可以在编译阶段进行type checking
    2、java明确了继承关系，对于大型系统更易于拓展维护；而python的类继承是动态的，可在运行时修改
    3、python的dict直接基于哈希表实现，而java中允许选择不同数据结构，eg: HashMap TreeMap LinkedHashMap，可优化性能
    4、java的设计更接近底层硬件，底层硬件中不存在可变长度的数组，因此java的数组可以直接映射到底层内存，性能更高
     */
    public Map<String, Integer> collectWordCount(List<String> targets, List<String> words) {
        Map<String, Integer> result = new HashMap<>();
        for (String target : targets) {
            result.put(target, 0);
        }
        for (String word : words) {
            if (result.containsKey(word)) {
                result.put(word, result.get(word) + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Demo01Libraries l = new Demo01Libraries();
        List<String> words = l.getWords("./3body.txt");
        System.out.println(words.size());
        int unique = l.countUniqueWords(words);
        System.out.println(unique);
    }
}
