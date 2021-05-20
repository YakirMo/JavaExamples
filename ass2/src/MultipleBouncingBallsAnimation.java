import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creats a multiple bouncing Balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * .
     * This method converts a given string to numbers
     *
     * @param numsToConvert - the given string to convert to numbers
     * @return - an array with the converted numbers
     */
    public static int[] stringToInt(String[] numsToConvert) {
        int i;
        int[] arr = new int[numsToConvert.length];
        for (i = 0; i < numsToConvert.length; i++) {
            arr[i] = Integer.parseInt(numsToConvert[i]);
        }
        return arr;
    }

    /**
     * .
     * This method produces Balls according to a number of parameters
     * and stores them in an array
     *
     * @param sizes         - the radius'es of given Balls
     * @param startBoundary - starting Point of Boundary (upper-left)
     * @param endBoundary   - starting Point of Boundary (right-bottom)
     * @param highestRad    - the highest radius among all given Balls
     * @return - an array of Balls
     */
    public Ball[] produceBalls(int[] sizes, Point startBoundary,
                               Point endBoundary, int highestRad) {
        int i;
        Ball[] balls = new Ball[sizes.length];
        Random rand = new Random();
        for (i = 0; i < sizes.length; i++) {
            balls[i] = new Ball(rand.nextInt((int) endBoundary.getX()),
                    rand.nextInt((int) endBoundary.getY()),
                    sizes[i], Color.BLACK, startBoundary, endBoundary);
            if (balls[i].validRad()) {
                Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360),
                        (highestRad / balls[i].getSize()));
                balls[i].setVelocity(v);
            } else {
                System.exit(1);
            }
        }
        return balls;
    }

    /**
     * .
     * This method draws a windows with animated bouncing Balls
     *
     * @param args          - user chosen radius'es as strings
     * @param startBoundary - starting Point of Boundary (upper-left)
     * @param endBoundary   - ending Point of Boundary (right-bottom)
     */
    public void bounceBalls(String[] args, Point startBoundary, Point endBoundary) {
        int i, highestRad;
        int[] sizes = stringToInt(args);
        highestRad = sizes[0];
        for (i = 0; i < sizes.length; i++) {
            if (highestRad < sizes[i]) {
                highestRad = sizes[i];
            }
        }
        Ball[] balls = produceBalls(sizes, startBoundary, endBoundary, highestRad);
        GUI gui = new GUI("title", (int) endBoundary.getX(), (int) endBoundary.getY());
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (i = 0; i < balls.length; i++) {
                balls[i].drawOn(d);
                balls[i].moveOneStep();
            }
            gui.show(d);
            sleeper.sleepFor(10);
        }
    }

    /**
     * .
     * Main method
     *
     * @param args - user desired radius'es
     */
    public static void main(String[] args) {
        Point startBoundary = new Point(0, 0);
        Point endBoundary = new Point(800, 600);
        MultipleBouncingBallsAnimation begin = new MultipleBouncingBallsAnimation();
        begin.bounceBalls(args, startBoundary, endBoundary);
    }
}
