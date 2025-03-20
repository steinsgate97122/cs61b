/*
下面的例子说明了继承会如何破坏封装性，Dog和DogV2的外部表现完全一致，但VerboseDog继承DogV2时会进入死循环
 */
public class Encapsulation {
    public class Dog {
        public void bark() {
            System.out.println("bark");
        }

        public void barkMany(int N) {
            for (int i = 0; i < N; i++) {
                bark();
            }
        }
    }

    public class DogV2 {
        public void bark() {
            barkMany(1);
        }

        public void barkMany(int N) {
            for (int i = 0; i < N; i += 1) {
                System.out.println("bark");
            }
        }
    }

    public class VerboseDog extends Dog {
        @Override
        public void barkMany(int N) {
            System.out.println("As a dog, I say: ");
            for (int i = 0; i < N; i += 1) {
                bark();
            }
        }
    }
}
