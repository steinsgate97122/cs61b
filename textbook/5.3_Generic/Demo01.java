import org.junit.Assert;
import org.junit.Test;

public class Demo01 {
    @Test
    public void test() {
        Map61B<Integer, Integer> am = new ArrayMap<>();
        am.put(2, 5);
        int expected = 5;
        // 下面这段代码会编译错误，Ambiguous method，expected类型是int，am.get(2)返回了Integer，不知道使用哪个重载方法，
        // java匹配出了以下2个可能的重载方法：assertEquals(long expected, long actual) 和 assertEquals(Object expected, Object actual)
//        Assert.assertEquals(expected, am.get(2));
        // 处理上面这种情况，都换成int或者都换成Integer就行了
        Assert.assertEquals(expected, am.get(2).intValue());
        Assert.assertEquals(Integer.valueOf(expected), am.get(2));
    }
}
