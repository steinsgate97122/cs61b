import org.junit.Test;
import static org.junit.Assert.*;

/*
相比上一版本，加上了@org.junit.Test注解，方法变成非静态，去掉了main方法
 */
public class TestSortV2 {
    @Test
    public void testSortV1() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        // 也可以不写循环，直接用Arrays.equals，建议还是用junit写方便一点
        for (int i = 0; i < input.length; i += 1) {
            // 用.equals而不用==是因为需要比对两个对象的value而不是底层的bit
            if (!input[i].equals(expected[i])) {
                System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i] + ".");
                break;
            }
        }
    }

    @Test
    public void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int smallestIndex = Sort.findSmallest(input, 0);
        assertEquals(expected, smallestIndex);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;

        int smallestIndex2 = Sort.findSmallest(input2, 0);
        assertEquals(expected2, smallestIndex2);
    }

    @Test
    public void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);
        assertArrayEquals(expected, input);
    }
}
