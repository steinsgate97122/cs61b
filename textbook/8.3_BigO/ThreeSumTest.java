import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ThreeSumTest {
    /*
    下面用的是双指针，只统计count可以用Hashset
     */
    public static int threeSum(int[] arr) {
        // 先排序
        Arrays.sort(arr);
        // 先确定1个固定元素，剩下2个用双指针
        int count = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1;
            int right = arr.length - 1;
            while (left < right) {
                int sum = arr[left] + arr[right] + arr[i];
                if (sum == 0) {
                    count++;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }

    @Test
    public void testThreeSum() {
        int[] a = {30, -40, -20, -10, 40, 0, 10, 5};
        int c = threeSum(a);  // 期望有4组
        Assert.assertEquals(c, 4);
    }
}
