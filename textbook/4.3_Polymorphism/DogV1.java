public class DogV1 implements OurComparable {
    public String name;
    public int size;

    public DogV1(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void bark() {
        System.out.println(name + " woof");
    }

    /*
    @Override
    public int compareTo(Object o) {
        Dog d = (Dog) o;
        if (this.size < d.size) {
            return -1;
        } else if (this.size == d.size) {
            return 0;
        } else {
            return 1;
        }
    }
     */

    /*
    this小于o时返回负数，希望比较的是size，即this.size小于o.size时返回负数，直接return this.size - o.size即可
     */
    @Override
    public int compareTo(Object o) {
        DogV1 d = (DogV1) o;
        return this.size - d.size;
    }
}
