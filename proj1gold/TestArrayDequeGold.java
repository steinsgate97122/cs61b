import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        StringBuilder errorMessage = new StringBuilder();

        Integer num = 0;
        while (true) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                sad1.addFirst(num);
                ads1.addFirst(num);
                errorMessage.append("addFirst(").append(num).append(")\n");
            } else if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(num);
                ads1.addLast(num);
                errorMessage.append("addLast(").append(num).append(")\n");
            } else if (numberBetweenZeroAndOne < 0.75) {
                Integer sadNum = sad1.removeFirst();
                Integer adsNum = ads1.removeFirst();
                errorMessage.append("removeFirst()\n");
                assertEquals(errorMessage.toString(), sadNum, adsNum);
            } else {
                Integer sadNum = sad1.removeLast();
                Integer adsNum = ads1.removeLast();
                errorMessage.append("removeLast()\n");
                assertEquals(errorMessage.toString(), sadNum, adsNum);
            }
            num += 1;
        }
    }
}
