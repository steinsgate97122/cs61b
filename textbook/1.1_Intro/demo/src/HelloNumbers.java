public class HelloNumbers {
    public static void main(String[] args) {
        // variable must be declared before it is used, and it must be given a type!
        int x = 0;
        int sum = 0;
        while (x < 10) {
            // use print instead of println, and add a space to make sure numbers don't run into each other.
            System.out.print(x + " ");
            sum += x;
            x = x + 1;
        }
        System.out.println();
        System.out.println(sum);
    }
}
