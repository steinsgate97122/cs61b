package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};
    private String encourage;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        // Initialize random number generator
        rand = new Random(seed);
        encourage = ENCOURAGEMENT[rand.nextInt(ENCOURAGEMENT.length)];
    }

    public String generateRandomString(int n) {
        // Generate random string of letters of length n
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int charIndex = rand.nextInt(CHARACTERS.length);
            sb.append(CHARACTERS[charIndex]);
        }
        return sb.toString();
    }

    public void drawFrame(String s) {
        // Take the string and display it in the center of the screen
        StdDraw.clear();
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width / 2.0, height / 2.0, s);
        // If game is not over, display relevant game information at the top of the screen
        font = new Font("Monaco", Font.BOLD, 15);
        StdDraw.setFont(font);
        String roundMessage = "Round: " + round;
        StdDraw.textLeft(1, height - 1, roundMessage);
        if (playerTurn) {
            StdDraw.text(width / 2.0, height - 1, "Type!");
        } else {
            StdDraw.text(width / 2.0, height - 1, "Watch!");
        }
        StdDraw.textRight(width - 1, height - 1, encourage);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        // Each character should be visible on the screen for 1 second and
        // there should be a brief 0.5 second break between characters where the screen is blank
        for (int i = 0; i < letters.length(); i++) {
            String letter = letters.substring(i, i + 1);
            drawFrame(letter);
            StdDraw.pause(1000);  // 底层就是Thread.sleep()
            drawFrame("");
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        // Read n letters of player input
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                sb.append(c);
                drawFrame(sb.toString());
                count += 1;
                if (count == n) {
                    StdDraw.pause(500);
                    return sb.toString();
                }
            }
        }
    }

    public void startGame() {
        // Set any relevant variables before the game starts
        round = 1;
        // Establish Game loop
        while (true) {
            playerTurn = false;
            StdDraw.clear();
            String roundMessage = "Round: " + round;
            drawFrame(roundMessage);
            StdDraw.pause(1000);
            String randomString = generateRandomString(round);
            flashSequence(randomString);
            playerTurn = true;
            drawFrame("");
            String userInputString = solicitNCharsInput(round);
            if (!randomString.equals(userInputString)) {
                String loseStr = "Game Over! You made it to round:" + round;
                drawFrame(loseStr);
                return;
            } else {
                round += 1;
            }
        }
    }

}
