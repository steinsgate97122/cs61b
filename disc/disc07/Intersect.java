import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersect {
    public static int[] intersect(int[] a1, int[] a2) {
        Set<Integer> mySet = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i : a1) {
            mySet.add(i);
        }
        for (int i : a2) {
            if (mySet.contains(i)) {
                res.add(i);
            }
            mySet.add(i);
        }
        int[] resArr = new int[res.size()];
        int i = 0;
        for (Integer ele : res) {
            resArr[i] = ele;
            i++;
        }
        return resArr;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 4, 5, 6};
        int[] res = intersect(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }
}
