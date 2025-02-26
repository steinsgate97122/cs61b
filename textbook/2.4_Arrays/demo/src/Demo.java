/*
类比array和class，都会创建memory boxes，array length不可变，class fields也不可变，区别在于：
1）array box通过[]访问，class box通过dot notation访问
2）array boxes都需要是相同类型，class boxes类型可以不同
假如用户输入了一个index，可以通过这个index访问数组元素；但如果用户输入了一个字符串，不能通过这个字符串来访问对象的属性
其实也能通过反射在运行期间指定class field，但建议别这么写

PS: java array相比python没有slice的特性，相比js没有成员方法
 */
public class Demo {
    public static void main(String[] args) {
        int[] z = null;
        int[] x, y;

        x = new int[]{1, 2, 3, 4, 5};
        y = x;
        x = new int[]{-1, 2, 5, 4, 99};
        y = new int[3];
        z = new int[0];
        int xL = x.length;

        String[] s = new String[6];
        s[4] = "ketchup";
        s[x[3] - x[1]] = "muffins";

        int[] b = {9, 10, 11};
        // 下面这行等价于python中的x[3:5] = b[0:2]，arraycopy也可以用循环来实现，但效率不如System.arraycopy
        // 注意java中的bounds check只在运行时检查，因此如果写了System.arraycopy(b, 0, x, 3, 3)会造成ArrayIndexOutOfBoundsException
        System.arraycopy(b, 0, x, 3, 2);
    }
}
