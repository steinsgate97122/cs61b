package disc;

import demo.IntList;

public class SquareList {
    public static IntList square(IntList L) {
        // 不要影响原数组
        if (L.rest == null) {
            return new IntList(L.first * L.first, null);
        }
        return new IntList(L.first * L.first, square(L.rest));
    }

    public static void squareDestructive(IntList L) {
        // 直接在原数组上动手
        L.first = L.first * L.first;
        if (L.rest == null) {
            return;
        }
        squareDestructive(L.rest);
    }

    public static IntList squareIterative(IntList L) {
        // 用循环，不影响原数组
        IntList curHead = new IntList(L.first * L.first, null);
        IntList result = curHead;
        while (L.rest != null) {
            IntList nextHead = L.rest;
            curHead.rest = new IntList(nextHead.first * nextHead.first, null);
            curHead = curHead.rest;
            L = L.rest;
        }
        return result;
    }

    public static void squareDestructiveIterative(IntList L) {
        // 直接在原数组上动手，但是用循环
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(10, null);
        L.rest.rest = new IntList(15, null);
        IntList newList = square(L);
        System.out.println(newList.first);
        System.out.println(newList.rest.first);
        System.out.println(newList.rest.rest.first);

        squareDestructive(L);
        System.out.println(L.first);
        System.out.println(L.rest.first);
        System.out.println(L.rest.rest.first);

        L = new IntList(2, null);
        L.rest = new IntList(3, null);
        L.rest.rest = new IntList(4, null);
        newList = squareIterative(L);
        System.out.println(newList.first);
        System.out.println(newList.rest.first);
        System.out.println(newList.rest.rest.first);

        squareDestructiveIterative(L);
        System.out.println(L.first);
        System.out.println(L.rest.first);
        System.out.println(L.rest.rest.first);
    }
}
