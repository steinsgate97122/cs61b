package byog.Core;

public class Room implements Comparable<Room> {
    Position p;
    int height;
    int width;

    public Room(Position p, int height, int width) {
        this.p = p;
        this.height = height;
        this.width = width;
    }

    @Override
    public int compareTo(Room o) {
        // 当this小于o时，返回负数；优先比较高度，高度一样时比较宽度；高度宽度小的排在前面
        if (this.p.y != o.p.y) {
            return this.p.y - o.p.y;
        } else {
            return this.p.x - o.p.x;
        }
    }
}
