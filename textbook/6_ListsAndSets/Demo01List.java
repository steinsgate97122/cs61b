import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo01List {
    /*
    直接使用full name of classes, eg: java.util.List<Integer> L = new java.util.ArrayList<>();
    import java libraries之后，下面的代码更加简洁一些
    Set的实现也是类似，在下面给出
     */
    public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();
        L.add(5);
        L.add(10);
        System.out.println(L);

        Set<String> S = new HashSet<>();
        S.add("Tokyo");
        S.add("Lagos");
        System.out.println(S.contains("Tokyo"));

        for (String s : S) {
            System.out.println(s);
        }
    }
}
