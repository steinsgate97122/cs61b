package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 45;
    TETile[][] worldFrame;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        ter.initialize(WIDTH, HEIGHT);
        while (true) {
            GameUtils.displayMainMenu(WIDTH, HEIGHT);
            char playerCommand = GameUtils.getUserInput();
            if (playerCommand == 'N' || playerCommand == 'n') {
                TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
                String userInputSeed = GameUtils.getUserInputSeed(WIDTH, HEIGHT);
                int seed = Integer.parseInt(userInputSeed);
                RandomWorld randomWorld = new RandomWorld(seed, ter);
                randomWorld.generateWorld(finalWorldFrame);
                if (randomWorld.playGame(finalWorldFrame)) {
                    GameUtils.displayEnd(WIDTH, HEIGHT);
                    return;
                }
            } else if (playerCommand == 'L' || playerCommand == 'l') {
                TETile[][] loadWorldFrame = GameUtils.loadGame();
                if (loadWorldFrame != null) {
                    Position playerPosition = GameUtils.findPlayer(loadWorldFrame);
                    RandomWorld randomWorld = new RandomWorld(ter, playerPosition);
                    if (randomWorld.playGame(loadWorldFrame)) {
                        GameUtils.displayEnd(WIDTH, HEIGHT);
                        return;
                    }
                }
            } else if (playerCommand == 'Q' || playerCommand == 'q') {
                return;
            }
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        // 假定输入起始都是N#S或者L，:Q结尾会返回保存的TETile[][]
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        if (input.startsWith("n")) {
            // 创建新世界
            worldFrame = finalWorldFrame;
            // 先提取出seed
            int sIndex = input.indexOf("s");
            String seedString = input.substring(1, sIndex);
            long seed = Long.parseLong(seedString);
            RandomWorld randomWorld = new RandomWorld(seed);
            randomWorld.generateWorld(finalWorldFrame);
            randomWorld.playGame(finalWorldFrame, input.substring(sIndex + 1));
        } else if (input.startsWith("l")) {
            if (worldFrame != null) {
                Position playerPosition = GameUtils.findPlayer(worldFrame);
                RandomWorld randomWorld = new RandomWorld(ter, playerPosition);
                randomWorld.playGame(worldFrame, input.substring(1));
                finalWorldFrame = worldFrame;
            }
        }
        return finalWorldFrame;
    }
}
