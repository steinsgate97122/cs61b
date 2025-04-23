import java.util.Arrays;

public class Merge {
    public static int[] mergeArr(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length + arr2.length];
        int index1 = 0;
        int index2 = 0;
        int index = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            int ele1 = arr1[index1];
            int ele2 = arr2[index2];
            if (ele1 < ele2) {
                res[index] = ele1;
                index++;
                index1++;
            } else {
                res[index] = ele2;
                index++;
                index2++;
            }
        }
        while (index1 < arr1.length) {
            res[index] = arr1[index1];
            index++;
            index1++;
        }
        while (index2 < arr2.length) {
            res[index] = arr2[index2];
            index++;
            index2++;
        }
        return res;
    }

    public static void mergeSort(int[] arr) {
        int length = arr.length;
        if (length <= 1) {
            return;
        }
        int[] left = new int[length / 2];
        System.arraycopy(arr, 0, left, 0, left.length);
        int[] right = new int[length - length / 2];
        System.arraycopy(arr, left.length, right, 0, right.length);
        mergeSort(left);
        mergeSort(right);
        int[] tmp = mergeArr(left, right);
        System.arraycopy(tmp, 0, arr, 0, tmp.length);
    }

    public static void main(String[] args) {
        int[] arr = {5, 5, 6, 3, 2, 4, 8, 9, 0};
        System.out.println(Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}