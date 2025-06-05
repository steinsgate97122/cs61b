package hw3.hash;
import java.awt.Color;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdDraw;


public class SimpleOomage implements Oomage {
    protected int red;
    protected int green;
    protected int blue;

    private static final double WIDTH = 0.01;
    private static final boolean USE_PERFECT_HASH = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        SimpleOomage simpleOomage = (SimpleOomage) o;
        if (this.red == simpleOomage.red
                && this.green == simpleOomage.green
                && this.blue == simpleOomage.blue) {
            return true;
        }
        return false;
    }


    private int hashCodeV1() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            // Write a perfect hash function for Simple Oomages.
            int hash = 1;
            hash = hash * 31 + red;
            hash = hash * 31 + green;
            hash = hash * 31 + blue;
            return hash;
        }
    }

    @Override
    public int hashCode() {
        if (!USE_PERFECT_HASH) {
            return red + green + blue;
        } else {
            /* 上面的V1版本符合java惯例，但无法保证完全不冲突，想要完全没有冲突还需要单独设计
            rgb分别有52个可能的取值，所以需要52^3个hash值，14万多一点，在int值的范围内
            可以用52进制的数，r作为最高位，b最低位，就是 r*52*52+g*52+b ，rgb属于[0,51]，就能保证错开
            先除上5很有必要，不这么做的话算出来的hashCode都会是5的倍数，这样在bucket数量也是5的倍数时就会发生collision
             */
            int rIndex = red / 5;
            int gIndex = green / 5;
            int bIndex = blue / 5;
            return rIndex * 52 * 52 + gIndex * 52 + bIndex;
        }
    }

    public SimpleOomage(int r, int g, int b) {
        if (r < 0 || r > 255 || g < 0 || g > 255 || b < 0 || b > 255) {
            throw new IllegalArgumentException();
        }
        if ((r % 5 != 0) || (g % 5 != 0) || (b % 5 != 0)) {
            throw new IllegalArgumentException("red/green/blue values must all be multiples of 5!");
        }
        red = r;
        green = g;
        blue = b;
    }

    @Override
    public void draw(double x, double y, double scalingFactor) {
        StdDraw.setPenColor(new Color(red, green, blue));
        StdDraw.filledSquare(x, y, WIDTH * scalingFactor);
    }

    public static SimpleOomage randomSimpleOomage() {
        int red = StdRandom.uniform(0, 51) * 5;
        int green = StdRandom.uniform(0, 51) * 5;
        int blue = StdRandom.uniform(0, 51) * 5;
        return new SimpleOomage(red, green, blue);
    }

    public static void main(String[] args) {
        System.out.println("Drawing 4 random simple Oomages.");
        randomSimpleOomage().draw(0.25, 0.25, 1);
        randomSimpleOomage().draw(0.75, 0.75, 1);
        randomSimpleOomage().draw(0.25, 0.75, 1);
        randomSimpleOomage().draw(0.75, 0.25, 1);
    }

    public String toString() {
        return "R: " + red + ", G: " + green + ", B: " + blue;
    }
} 
