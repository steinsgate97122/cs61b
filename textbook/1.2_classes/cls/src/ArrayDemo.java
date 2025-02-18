public class ArrayDemo {
    public static void main(String[] args) {
        /* Create an array of five integers. */
        int[] someArray = new int[5];  // 数组也用new来instantiate
        someArray[0] = 3;
        someArray[1] = 4;

        // Objects Array也可以用new来定义
        Dog[] dogs = new Dog[2];  // 创建Array
        dogs[0] = new Dog(8);  // 创建Object
        dogs[1] = new Dog(20);
        /* Yipping will result, since dogs[0] has weight 8. */
        dogs[0].makeNoise();
    }
}
