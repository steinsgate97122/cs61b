public class TestAnimals {
    public static void main(String[] args) {
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);

        a.greet();  // Animal Pluto says: Huh?
        c.greet();  // Cat Garfield says: Huh?
        d.greet();  // Dog Fido says: WOOF!
        a = c;
        ((Cat) a).greet();  // Cat Pluto says: Huh?
        a.greet();  // Cat Pluto says: Huh?

        a = new Dog("Spot", 10);
        d = (Dog) a;
    }
}
