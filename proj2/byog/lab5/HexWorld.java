package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 * <a href="https://sp18.datastructur.es/materials/lab/lab5/drawhexagon.txt">参考答案</a>
 * 同样是将绘制Hexagon拆解为了一组行的绘制，但是进一步，在画每一行时只需要知道行的width和xOffset就可以，比我下面的实现清楚一点
 * 画row的时候y坐标固定，x坐标知道最左边的那个和宽度就足够了，那么就可以分别写计算width和xOffset的函数即可，完成解耦
 * 计算width，接收参数hexSize、rowNumber
 * 当rowNumber < hexSize时，effectiveI = rowNumber，否则 effectiveI = 2 * hexSize - 1 - rowNumber
 * 最终width = s + 2 * effectiveI
 * 计算xOffset，同样接收参数hexSize、rowNumber，也是类似的先计算effectiveI，最终xOffset = -effectiveI
 * 这俩函数完成后，addRow和addHexagon函数逻辑可以更加清楚一些
 *
 * 然后是生成19个六边形的方法，Professor一开始也是和我类似的思路，打算编写生成邻居Hexagon的方法，但要获取整个grid并不简单
 * 用递归看上去也不太可能，所以他直接跳过了这个想法；真是悲伤，我直接暴力填充了19个，当然不是一个好办法，虽然可以work (ಥ﹏ಥ)
 * 最终期望的形状其实包含了5列，分别包含3 4 5 4 3个Hexagon，所以可以写一个方法，用来位置一列N个六边形,需要指定起始位置
 * 在确定起始位置的时候addTopLeftHexagon和addTopRightHexagon还是可以派上用场的，这个看上去至少比调用19次add好一点~~
 * 下次打开这个文件的时候考虑用这个思路实现下，今天累累的~~ ε=(´ο｀*)))
 */
public class HexWorld {
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
    Position表示hexagon的左下角元素位置 s为hexagon的边长 t为填充样式
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int i = 0; i < s; i++) {
            fillLine(world, p, s, t, i);
        }
    }

    /* 确定第line行需要填充的格子，fillLine会将line和line对称的部分都填充好
    line=0, (p.x, p.y) (p.x+1, p.y) s格
    line=1, (p.x-1, p.y+1) (p.x, p.y+1) (p.x+1, p.y+1) (p.x+2, p.y+1) s+2格
    line=2, (p.x-1, p.y+2) (p.x, p.y+2) (p.x+1, p.y+2) (p.x+2, p.y+2) s+2格
    line=3, (p.x, p.y+3) (p.x+1, p.y+3) s格
     */
    private static void fillLine(TETile[][] world, Position p, int s, TETile t, int line) {
        // line对应的格子数量为 s+2*line
        int tileNum = s + line * 2;
        // 计算出需要填充的底部行号和顶部行号
        int bottomLineIndex = p.y + line;
        int topLineIndex = p.y + line + 2 * (s - line) - 1;
        for (int index = 0; index < tileNum; index++) {
            // 填充时，如果出现outOfIndex，就跳过对应的格子
            int cX = p.x - line + index;
            if (cX < 0 || cX >= world.length) {
                continue;
            }
            if (bottomLineIndex >= 0 && bottomLineIndex < world[0].length) {
                world[cX][bottomLineIndex] = t;
            }
            if (topLineIndex >= 0 && topLineIndex < world[0].length) {
                world[cX][topLineIndex] = t;
            }
        }
    }

    /*
    对于一个Hexagon，其左上，上方，右上都可以拼一个新的Hexagon（只要有空间）
    只要分别写出上面的3个方法，就可以在main方法中完成组装
     */
    public static Position addTopLeftHexagon(TETile[][] world, Position originPosition, int s, TETile t) {
        int newY = originPosition.y + s;
        int newX = originPosition.x - s + 1 - s;
        Position newPosition = new Position(newX, newY);
        addHexagon(world, newPosition, s, t);
        return newPosition;
    }

    public static Position addTopHexagon(TETile[][] world, Position originPosition, int s, TETile t) {
        int newY = originPosition.y + s * 2;
        int newX = originPosition.x;
        Position newPosition = new Position(newX, newY);
        addHexagon(world, newPosition, s, t);
        return newPosition;
    }

    public static Position addTopRightHexagon(TETile[][] world, Position originPosition, int s, TETile t) {
        int newY = originPosition.y + s;
        int newX = originPosition.x + s - 1 + s;
        Position newPosition = new Position(newX, newY);
        addHexagon(world, newPosition, s, t);
        return newPosition;
    }

    public static void main(String[] args) {
        int WIDTH = 60;
        int HEIGHT = 40;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position startPosition = new Position(28, 0);
        addHexagon(world, startPosition, 4, Tileset.FLOWER);
        Position p2 = addTopLeftHexagon(world, startPosition, 4, Tileset.GRASS);
        Position p3 = addTopHexagon(world, startPosition, 4, Tileset.MOUNTAIN);
        Position p4 = addTopRightHexagon(world, startPosition, 4, Tileset.SAND);
        Position p5 = addTopLeftHexagon(world, p2, 4, Tileset.FLOOR);
        Position p6 = addTopHexagon(world, p2, 4, Tileset.LOCKED_DOOR);
        Position p7 = addTopHexagon(world, p3, 4, Tileset.WATER);
        Position p8 = addTopHexagon(world, p4, 4, Tileset.FLOWER);
        Position p9 = addTopRightHexagon(world, p4, 4, Tileset.GRASS);
        Position p10 = addTopHexagon(world, p5, 4, Tileset.MOUNTAIN);
        Position p11 = addTopHexagon(world, p6, 4, Tileset.SAND);
        Position p12 = addTopHexagon(world, p7, 4, Tileset.GRASS);
        Position p13 = addTopHexagon(world, p8, 4, Tileset.MOUNTAIN);
        Position p14 = addTopHexagon(world, p9, 4, Tileset.FLOOR);
        Position p15 = addTopHexagon(world, p10, 4, Tileset.FLOOR);
        Position p16 = addTopHexagon(world, p11, 4, Tileset.FLOWER);
        Position p17 = addTopHexagon(world, p12, 4, Tileset.MOUNTAIN);
        Position p18 = addTopHexagon(world, p13, 4, Tileset.SAND);
        Position p19 = addTopHexagon(world, p14, 4, Tileset.WATER);

        ter.renderFrame(world);
    }
}
