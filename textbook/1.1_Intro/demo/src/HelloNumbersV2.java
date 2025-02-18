public class HelloNumbersV2 {
    /**
     * Compile the program, we see:
         * $ javac HelloNumbers.java
         * HelloNumbers.java:9: error: incompatible types: String cannot be converted to int
         *         x = "horse";
         *                 ^
         * 1 error
     * the compiler rejects this program, 因此运行代码时不可能出现类型问题
     * 上面的特性称为static type（静态类型） , This is in contrast to dynamically typed languages like Python
     */
    public static void main(String[] args) {
        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }
//        x = "horse";
    }
}

/*
 Java中可以写 System.out.print(5 + " ") , 但python中不能写 print(5 + "horse"), why???
 考虑2中Java的statement, String h = 5 + "horse";  int h = 5 + "horse";  h为string时可以组合, h非string时不能组合
 但python中没有限制类型, python无法确定 5+ "horse" 希望得到int还是String, 因此报错
*/