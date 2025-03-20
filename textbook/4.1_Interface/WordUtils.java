public class WordUtils {
    public static String longest(SLList<String> list) {
        String longestString = "";
        for (int index = 0; index < list.size(); index++) {
            String currentString = list.get(index);
            if (currentString.length() > longestString.length()) {
                longestString = currentString;
            }
        }
        return longestString;
    }

    /* 这个方法和上面的除了参数外其他完全一致，在java中称为overloading，执行WordUtils.longest时会自己判断用哪一个
       但存在缺陷：1、大量重复代码 2、维护成本变高，修改longest时2种都要调整 3、如果有更多List类型，每个都要写一份
       从这个角度出发，引入interface的概念，指定一个类需要做什么，但不提供任何具体实现
     */
    public static String longest(AList<String> list) {
        String longestString = "";
        for (int index = 0; index < list.size(); index++) {
            String currentString = list.get(index);
            if (currentString.length() > longestString.length()) {
                longestString = currentString;
            }
        }
        return longestString;
    }

    // 上面2个可以删掉，直接用这个即可，之前提到对于 a = b，实际上是将b的bits复制一份到a，因此Dog d = 1是非法的
    // longest接收的是一个AList<String>，这个放进List61B<String>是完全可以的，因为AList就是List61B，所以可以塞进去
    public static String longest(List61B<String> list) {
        String longestString = "";
        for (int index = 0; index < list.size(); index++) {
            String currentString = list.get(index);
            if (currentString.length() > longestString.length()) {
                longestString = currentString;
            }
        }
        return longestString;
    }

    // List61B<String> LP = new SLList<String>();  peek(LP)会调用这个方法
    public static void peek(List61B<String> list) {
        System.out.println(list.getLast());
    }

    // SLList<String> SP = new SLList<String>();  peek(SP)会调用这个方法
    public static void peek(SLList<String> list) {
        System.out.println(list.getFirst());
    }

    public static void main(String[] args) {
        SLList<String> SP = new SLList<String>();
        List61B<String> LP = SP;
        SP.addLast("elk");
        SP.addLast("are");
        SP.addLast("cool");
        peek(SP);
        peek(LP);
    }
}
