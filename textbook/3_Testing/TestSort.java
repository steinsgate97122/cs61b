public class TestSort {
    /** Tests the sort method of the Sort class. */
    public static void testSortV1() {
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

    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);
        // err的结果看上去稍微丑了点，后面优化
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int smallestIndex = Sort.findSmallest(input, 0);
        org.junit.Assert.assertEquals(expected, smallestIndex);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;

        int smallestIndex2 = Sort.findSmallest(input2, 0);
        org.junit.Assert.assertEquals(expected2, smallestIndex2);
    }

    public static void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);
        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void main(String[] args) {
        testSort();
    }
}
