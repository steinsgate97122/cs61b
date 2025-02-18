/*
由于DogLauncher用到了Dog类，可以将DogLauncher称为Dog类的client，即 DogLauncher is a client of Dog
 */
public class DogLauncher {
    public static void main(String[] args) {
        Dog d;
        d = new Dog();
        d.weightInPounds = 20;
        d.makeNoise();
    }
}

/*
1. An Object in Java is an instance of any class
2. Dog类中的variable must be declared inside the class, Python中可以在运行时加新变量，这个有区别
3. Dog类中定义的makeNoise没有加static，称为non-static methods
4. 要调用makeNoise方法，要先using new to instantiate a Dog, 之后用d.makeNoise()调用
5. class内的Variables and methods可以被称为members of a class
6. members of a class通过dot notation来访问
 */