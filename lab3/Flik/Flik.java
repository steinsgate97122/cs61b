import java.util.Objects;

/** An Integer tester created by Flik Enterprises. */
public class Flik {
    public static boolean isSameNumber(Integer a, Integer b) {
        // a和b是两个对象，==比较的是对象的引用，Integer.valueOf()在-128到127会缓存已有对象，128之后就是不同对象了
        // Objects.equals等价于a.intValue() == b.intValue()，这个时候比较的是值
        return Objects.equals(a, b);
    }
}
