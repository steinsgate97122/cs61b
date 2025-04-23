import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Union {
    public static List<Integer> union(List<Integer> a1, List<Integer> a2) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < a1.size() && index2 < a2.size()) {
            int ele1 = a1.get(index1);
            int ele2 = a2.get(index2);
            if (ele1 < ele2) {
                result.add(ele1);
                index1++;
            } else if (ele1 > ele2) {
                result.add(ele2);
                index2++;
            } else {
                result.add(ele1);
                index1++;
                index2++;
            }
        }
        while (index1 < a1.size()) {
            result.add(a1.get(index1));
            index1++;
        }
        while (index2 < a2.size()) {
            result.add(a2.get(index2));
            index2++;
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6);
        System.out.println(union(list1, list2));
    }
}
