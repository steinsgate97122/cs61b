/*
反射的基本操作就是先通过getDeclaredField获取到私有字段，然后把对象的这个字段改掉，Spring框架这玩意用得比较多
比如依赖注入（Dependency Injection） class前面加上@Component 对象属性前面加上@Autowired 就创建了Bean，可以往里面注入值
比如AOP（Aspect-Oriented Programming），切面定义之后可以让所有方法执行前先执行额外逻辑，也是通过反射来修改原先的逻辑
自己写代码不要用反射~性能差、可读性差、还破坏了私有字段的封装性
 */

import java.lang.reflect.Field;
import java.util.Arrays;
import static java.lang.System.out;

enum Tweedle { DEE, DUM }

public class Book {
    public long chapters = 0;
    public String[] characters = { "Alice", "White Rabbit" };
    public Tweedle twin = Tweedle.DEE;

    public static void main(String... args) {
        Book book = new Book();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();

            Field chap = c.getDeclaredField("chapters");
            out.format(fmt, "before", "chapters", book.chapters);  // 0
            chap.setLong(book, 12);
            out.format(fmt, "after", "chapters", chap.getLong(book));  // 12

            Field chars = c.getDeclaredField("characters");
            out.format(fmt, "before", "characters",
                    Arrays.asList(book.characters));
            String[] newChars = { "Queen", "King" };
            chars.set(book, newChars);
            out.format(fmt, "after", "characters",
                    Arrays.asList(book.characters));

            Field t = c.getDeclaredField("twin");
            out.format(fmt, "before", "twin", book.twin);
            t.set(book, Tweedle.DUM);
            out.format(fmt, "after", "twin", t.get(book));

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}