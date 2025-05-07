package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameUtils {
    public static char getUserInput() {
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            return StdDraw.nextKeyTyped();
        }
    }

    public static String getUserInputSeed(int width, int height) {
        String input = "";

        while (true) {
            StdDraw.clear(new Color(0, 0, 0));
            StdDraw.text(width / 2.0, height / 2.0, "current input seed: " + input);
            StdDraw.show();
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            if (key == 's' || key == 'S') {
                break;
            }
            input += String.valueOf(key);
        }
        StdDraw.clear(new Color(0, 0, 0));
        StdDraw.text(width / 2.0, height / 2.0, "Game Start (" + input + ")");
        StdDraw.show();
        StdDraw.pause(1000);
        return input;
    }

    public static void displayMainMenu(int width, int height) {
        StdDraw.clear(new Color(0, 0, 0));

        Font bigFont = new Font("Monaco", Font.BOLD, 40);
        StdDraw.setFont(bigFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2.0, 3 * height / 4.0, "CS61B: THE GAME");

        Font smallFont = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2.0, height / 2.0 + 2, "New Game (N)");
        StdDraw.text(width / 2.0, height / 2.0, "Load Game (L)");
        StdDraw.text(width / 2.0, height / 2.0 - 2, "Quit (Q)");
        StdDraw.show();
    }

    public static void displayEnd(int width, int height) {
        StdDraw.clear(new Color(0, 0, 0));

        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2.0, height / 2.0, "We Won!");
        StdDraw.show();
    }

    public static void saveGame(TETile[][] world) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(world);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    public static TETile[][] loadGame() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                TETile[][] loadWorld = (TETile[][]) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }
        return null;
    }

    public static Position findPlayer(TETile[][] world) {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j].equals(Tileset.PLAYER)) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
}
