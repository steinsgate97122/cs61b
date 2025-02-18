/*
javac Dog.java
java Dog
这样会报错：找不到main method
要么在Dog类里面加上public static void main(String[] args)，要么再创建一个DogLauncher来运行Dog类的method
 */
public class Dog {
    public int weightInPounds;  // 对象的属性
    public static String binomen = "Canis familiaris";  // 类的固有属性

    // 下面2个称为constructor，在new的时候调用，和python中的__init__方法差不多
    public Dog() {
    }

    public Dog(int w) {
        weightInPounds = w;
    }

    public void makeNoise() {
        if (weightInPounds < 10) {
            System.out.println("yipyipyip!");
        } else if (weightInPounds < 30) {
            System.out.println("bark. bark.");
        } else {
            System.out.println("woof!");
        }
    }

    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.weightInPounds > d2.weightInPounds) {
            return d1;
        }
        return d2;
    }

    public Dog maxDog(Dog d2) {
        if (this.weightInPounds > d2.weightInPounds) {
            return this;
        }
        return d2;
    }
}