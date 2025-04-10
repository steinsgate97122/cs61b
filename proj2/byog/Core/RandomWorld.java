package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
生成一个随机世界，需要伪随机
需要包含rooms and hallways，都用FLOOR来填充，rooms和hallways的数量也是随机的，rooms and hallways需要有walls包在外面
rooms and hallways还需要连通，其他都可以自由发挥
下面这个版本生成的地图很工整，优化空间包括：hallway的走线方式可以随机一点，另外可以加一些死胡同进去
房间也应该避免过于集中，可能先把地图分成几个区块然后在每个区块随机生成更好，另外房间大小也应该区分大一些，比如20%生成大房间，80%小房间
 */
public class RandomWorld {
    private long seed = 99233;
    private final Random RANDOM;

    public RandomWorld() {
        RANDOM = new Random(seed);
    }

    public RandomWorld(long seed) {
        this.seed = seed;
        RANDOM = new Random(seed);
    }

    public void generateWorld(TETile[][] world) {
        int height = world[0].length;
        int width = world.length;
        // 先要确定room最大的边长
        int maxLength = (int) Math.pow((height * width) / 4.0, 0.4);
        // 同时maxLength不应该超过height weight长度的1/2
        maxLength = Math.min(Math.min(maxLength, height / 2), width / 2);
        // room最多填充一半游戏空间，由此确定room最多的生成数量
        int maxRoomNum = (height * width) / (maxLength * maxLength * 2);
        int roomNum = RANDOM.nextInt(maxRoomNum - 3) + 3;
        Room[] rooms = generateRooms(world, roomNum, maxLength);
        // 根据rooms生成hallway，随机连接
        ArrayList<Room> roomList = new ArrayList<>(List.of(rooms));
        Collections.shuffle(roomList, RANDOM);

        for (int i = 0; i < roomNum - 1; i++) {
            Room room1 = roomList.get(i);
            Room room2 = roomList.get(i + 1);
            addHallway(world, room1, room2);
        }

        Collections.shuffle(roomList, RANDOM);
        addHallway(world, roomList.get(0), roomList.get(1));
        addHallway(world, roomList.get(0), roomList.get(2));
    }

    private Room[] generateRooms(TETile[][] world, int roomNum, int maxRoomSize) {
        int height = world[0].length;
        int width = world.length;
        // 先清空棋盘
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Room[] rooms = new Room[roomNum];
        int curRoomRum = 0;
        while (curRoomRum < roomNum) {
            // 生成room的height、width、坐标，坐标不用0，外面留一层放围墙
            int roomHeight = RANDOM.nextInt(maxRoomSize - 3) + 3;
            int roomWidth = RANDOM.nextInt(maxRoomSize - 3) + 3;
            int roomPositionX = RANDOM.nextInt(width - roomWidth - 1 - 1) + 1;
            int roomPositionY = RANDOM.nextInt(height - roomHeight - 1 - 1) + 1;
            Position startPosition = new Position(roomPositionX, roomPositionY);
            Room room = new Room(startPosition, roomHeight, roomWidth);
            if (!isOverlap(rooms, room) && addRoom(world, room)) {
                addWall(world, room);
                rooms[curRoomRum] = room;
                curRoomRum += 1;
            }
        }
        return rooms;
    }

    /*
    rooms内维护了已经添加的房间，新添加的room需要保证与所有已有room至少存在3个间隔
     */
    private boolean isOverlap(Room[] rooms, Room roomNew) {
        for (Room room : rooms) {
            if (room == null) {
                continue;
            }
            int x = room.p.x;
            int y = room.p.y;
            int height = room.height;
            int width = room.width;
            int xN = roomNew.p.x;
            int yN = roomNew.p.y;
            int heightN = roomNew.height;
            int widthN = roomNew.width;
            // x-3到x+width+3这个范围 && y-3到y+height+3 这个矩形区域内不能出现新的room
            // 遍历roomNew的四个角，任意一个出现在这个区域内就认为overlap
            Position[] roomCorners = new Position[4];
            roomCorners[0] = new Position(xN, yN);
            roomCorners[1] = new Position(xN + widthN, yN);
            roomCorners[2] = new Position(xN, yN + heightN);
            roomCorners[3] = new Position(xN + widthN, yN + heightN);
            for (Position roomCorner : roomCorners) {
                if ((roomCorner.x >= x - 3)
                        && (roomCorner.x <= x + width + 3)
                        && (roomCorner.y >= y - 3)
                        && (roomCorner.y <= y + height + 3)
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
    指定起始位置和房间长宽，添加房间，如果撞上边界则添加失败返回false，否则返回true
     */
    private boolean addRoom(TETile[][] world, Room room) {
        int wHeight = world[0].length;
        int wWidth = world.length;
        // 假设(1,1) world是5*5 这个时候最多让添加宽度3的矩形，如果添加宽度4，1+4=5，这个时候房间本身就顶到天花板，不能再加墙了
        if (room.p.x + room.width >= wWidth || room.p.y + room.height >= wHeight) {
            return false;
        }
        TETile fillTile = Tileset.FLOOR;
        for (int i = 0; i < room.width; i++) {
            for (int j = 0; j < room.height; j++) {
                world[room.p.x + i][room.p.y + j] = fillTile;
            }
        }
        return true;
    }

    /*
    在room的最外层加上墙壁，需要操作
    第room.p.y - 1和room.p.y + room.height行
    第room.p.x - 1和room.p.x + room.width列
    如果这些在world中不存在就报错
     */
    private boolean addWall(TETile[][] world, Room room) {
        if (room.p.y - 1 < 0
                || room.p.y + room.height > world[0].length
                || room.p.x - 1 < 0
                || room.p.x + room.width > world.length) {
           return false;
        }
        TETile fillTile = Tileset.WALL;
        for (int i = 0; i < (room.width + 2); i++) {
            world[room.p.x - 1 + i][room.p.y - 1] = fillTile;
            world[room.p.x - 1 + i][room.p.y + room.height] = fillTile;
        }
        for (int i = 0; i < (room.height + 2); i++) {
            world[room.p.x - 1][room.p.y - 1 + i] = fillTile;
            world[room.p.x + room.width][room.p.y - 1 + i] = fillTile;
        }
        return true;
    }

    /*
    给定2个room，就可以在这两个room之间搭一条路
     */
    private void addHallway(TETile[][] world, Room r1, Room r2) {
        Position start, end;
        // 只要不是水平或者垂直，都可以走出一条L型的通路
        if (r1.p.x < r2.p.x) {
            start = new Position(r1.p.x + r1.width, r1.p.y + r1.height / 2);
            if (r1.p.y < r2.p.y) {
                end = new Position(r2.p.x + r2.width / 2, r2.p.y);
            } else {
                end = new Position(r2.p.x + r2.width / 2, r2.p.y + r2.height);
            }
        } else {
            start = new Position(r2.p.x + r2.width, r2.p.y + r2.height / 2);
            if (r1.p.y < r2.p.y) {
                end = new Position(r1.p.x + r1.width / 2, r1.p.y + r1.height);
            } else {
                end = new Position(r1.p.x + r1.width / 2, r1.p.y);
            }
        }
        connectPositions(world, start, end);
    }

    private void connectPositions(TETile[][] world, Position p1, Position p2) {
        if (p1.x < p2.x) {
            drawHorizontalLine(world, p1.x, p2.x, p1.y);
            if (p1.y < p2.y) {
                drawVerticalLine(world, p1.y, p2.y, p2.x);
            } else {
                drawVerticalLine(world, p2.y, p1.y, p2.x);
            }
        } else {
            drawHorizontalLine(world, p2.x, p1.x, p2.y);
            if (p1.y < p2.y) {
                drawVerticalLine(world, p1.y, p2.y, p1.x);
            } else {
                drawVerticalLine(world, p2.y, p1.y, p1.x);
            }
        }
    }

    /*
    调用时保证x1<x2，同时画上WALL
     */
    private void drawHorizontalLine(TETile[][] world, int x1, int x2, int y) {
        TETile floor = Tileset.FLOOR;
        TETile wall = Tileset.WALL;
        if (x1 >= x2) {
            return;
        }
        for (int i = 0; i < (x2 - x1 + 1); i++) {
            world[x1 + i][y] = floor;
            if (world[x1 + i][y + 1].equals(Tileset.NOTHING)) {
                world[x1 + i][y + 1] = wall;
            }
            if (world[x1 + i][y - 1].equals(Tileset.NOTHING)) {
                world[x1 + i][y - 1] = wall;
            }
        }
    }

    /*
    调用时保证y1<y2
     */
    private void drawVerticalLine(TETile[][] world, int y1, int y2, int x) {
        TETile floor = Tileset.FLOOR;
        TETile wall = Tileset.WALL;
        if (y1 >= y2) {
            return;
        }
        for (int i = 0; i < (y2 - y1 + 1); i++) {
            world[x][y1 + i] = floor;
            if (world[x + 1][y1 + i].equals(Tileset.NOTHING)) {
                world[x + 1][y1 + i] = wall;
            }
            if (world[x - 1][y1 + i].equals(Tileset.NOTHING)) {
                world[x - 1][y1 + i] = wall;
            }
        }
    }
}
