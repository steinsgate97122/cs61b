import java.time.chrono.MinguoChronology;
import java.util.Date;

public class Mid01Data {
    private static String[] rs;

    private static void printStringArray(String[] s) {
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + " ");
        }
        System.out.println();
    }

    private static void fillMany(String[] d) {
        System.arraycopy(Mid01Data.rs, 0 ,d, 0, d.length);
    }

    private static void fillOne(String d) {
        d = Mid01Data.rs[0];
    }

    public static void main(String[] args) {
        String a = "alice";
        String b = "bob";
        String c = "carol";
        String d = "dan";
        String[][] twod = {{a, b}, {c, d}};

//        String[] X = twod[1];
//        printStringArray(X);
//        Mid01Data.rs = X;
//        String[] Y = Mid01Data.rs;
//        Y = new String[]{d, c};
//        Mid01Data.rs[1] = "eve";
//        printStringArray(Mid01Data.rs);
//        printStringArray(Y);
//        printStringArray(twod[0]);
//        printStringArray(twod[1]);

        Mid01Data.rs = new String[]{"fritz", "gritz"};
        String[] X = twod[0];
        printStringArray(X);
        fillOne(X[0]);
        printStringArray(X);
        fillMany(X);
        printStringArray(X);
    }
}
