public class Main {
    /*
    1. public, 目前所有methods开头都是这个
    2. static, 静态方法，因此没有与特定instance关联上
    3. void, 没有返回值类型
    4. main, method的名称
    5. String[] args, 传递给main方法的参数
    由于main方法是经由Java解释器来调用，因此通过命令行参数来传递参数，eg:
        $ java Main these are command line arguments
        these
     */
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
