public class LargerDemo {
    // Since all Java code is part of a class, functions must be defined in a class
    // Functions that are part of a class are commonly called "methods"
    // 看上去比python啰嗦了不少，不过更加安全，在大项目里面有好处
    // method上面都建议写上Javadoc, 如下
    /** Returns the larger of x and y. */
    public static int larger(int x, int y) {
        if (x > y) {
            return x;
        }
        return y;
    }

    public static void main(String[] args) {
        System.out.println(larger(8, 10));
    }
}