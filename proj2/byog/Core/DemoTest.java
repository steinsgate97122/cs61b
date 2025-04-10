package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class DemoTest {
    public static void main(String[] args) {
        int width = 80;
        int height = 50;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        TETile[][] world = new TETile[width][height];

        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        RandomWorld randomWorld = new RandomWorld();
        randomWorld.generateWorld(world);
//        Room r1 = new Room(new Position(10, 10), 7 ,8);
//        Room r2 = new Room(new Position(30, 14), 7 ,8);
//        Room r3 = new Room(new Position(45, 25), 5 ,10);
//        randomWorld.addRoom(world, r1);
//        randomWorld.addRoom(world, r2);
//        randomWorld.addWall(world, r1);
//        randomWorld.addWall(world, r2);
//        randomWorld.addHallway(world, r2, r1);

        ter.renderFrame(world);
    }
}
