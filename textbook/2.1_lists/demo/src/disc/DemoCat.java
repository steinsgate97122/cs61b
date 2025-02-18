package disc;

public class DemoCat {
    public static void main(String[] args) {
        // noise先被赋值为Meow，再被赋值为Nyan，所以后面play出来的都是Nyan
        Cat a = new Cat("Cream", "Meow!");
        Cat b = new Cat("Tubbs", "Nyan!");
        a.play();
        b.play();
        Cat.anger();
        a.calm();  // 这里其实与Cat.calm()等效
        a.play();
        b.play();
    }
}
