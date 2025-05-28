import org.junit.Assert;
import org.junit.Test;

public class D01 {
    public static int f1(int i, int[] numList) {
        int left = 0;
        int right = numList.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (numList[mid] == i) {
                return mid;
            } else if (numList[mid] < i) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void testF1() {
        int[] numL = {1,2,3,4,5,6};
        for (int i = 0; i < numL.length; i++) {
            Assert.assertEquals(i, f1(numL[i], numL));
        }
        Assert.assertEquals(-1, f1(7, numL));
    }
}
