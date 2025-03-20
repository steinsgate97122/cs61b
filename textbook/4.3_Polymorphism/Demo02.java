public class Demo02<T> {
    public static Comparable max(Comparable[] items) {
        int maxDex = 0;
        for (int i = 0; i < items.length; i += 1) {
            if (items[i].compareTo(items[maxDex]) > 0) {
                maxDex = i;
            }
        }
        return items[maxDex];
    }

    public static void main(String[] args) {
        Dog[] dogs = {new Dog("Elyse", 3), new Dog("Sture", 9), new Dog("Benjamin", 15)};
        Dog maxDog = (Dog) max(dogs);  // 由于返回的类型是Comparable，还是必须要进行一次强制转换，解决方式后面再说
        maxDog.bark();
    }
}
