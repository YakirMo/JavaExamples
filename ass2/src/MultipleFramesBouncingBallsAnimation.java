import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creats a multiple bouncing Balls animation,
 * divided in to two windows.
 */
public class MultipleFramesBouncingBallsAnimation {
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
        for (i = 0; i < (numsToConvert.length); i++) {
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
     * @param endBoundary   - ending Point of Boundary (right-bottom)
     * @param onePart       - A part of the array of Balls (for one windows)
     * @param otherPart     - another part of the array of Balls
     *                      (for other windows)
     * @return - an array of Balls
     */
    public Ball[] produceBalls(int[] sizes, Point startBoundary,
                               Point endBoundary, int onePart, int otherPart) {
        int i, j = 0, highestRad;
        highestRad = sizes[0];
        for (i = 0; i < (sizes.length); i++) {
            if (highestRad < sizes[i]) {
                highestRad = sizes[i];
            }
        }
        Ball[] balls = new Ball[otherPart - onePart];
        Random rand = new Random();
        for (i = onePart; i < otherPart; i++, j++) {
            if (sizes[i] < 0) {
                System.exit(1);
            }
            balls[j] = new Ball(rand.nextInt((int) endBoundary.getX()),
                    rand.nextInt((int) endBoundary.getY())
                    , sizes[i], Color.BLACK, startBoundary, endBoundary);
            if (balls[j].validRad()) {
                Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360),
                        (highestRad / (balls[j].getSize())));
                balls[j].setVelocity(v);
            }
        }
        return balls;
    }

    /**
     * .
     * This method creates two rectangles with different colors and an equal
     * amount of animated bouncing Balls (within each)
     *
     * @param args       - user chosen radius'es as strings
     * @param grbStartP  - gray board starting Point of boundary
     * @param grbEndP    - gray board ending Point of boundary
     * @param yelbStartP - yellow board starting Point of boundary
     * @param yelbEndP   - yellow board ending Point of boundary
     */
    public void bounceBalls(String[] args, Point grbStartP, Point grbEndP, Point yelbStartP, Point yelbEndP) {
        int i, highestRad;
        int[] sizes = stringToInt(args);
        highestRad = sizes[0];
        for (i = 0; i < (sizes.length); i++) {
            if (highestRad < sizes[i]) {
                highestRad = sizes[i];
            }
        }
        Ball[] firstGroup = produceBalls(sizes, grbStartP, grbEndP, 0, ((sizes.length) / 2));
        Ball[] secondGroup = produceBalls(sizes, yelbStartP, yelbEndP, ((sizes.length) / 2), (sizes.length));
        GUI gui = new GUI("title", 700, 700);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.yellow);
            d.fillRectangle(450, 450, 150, 150);
            for (i = 0; i < firstGroup.length; i++) {
                firstGroup[i].drawOn(d);
                firstGroup[i].moveOneStep();
                secondGroup[i].drawOn(d);
                secondGroup[i].moveOneStep();
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
        Point grbStartP = new Point(50, 50);
        Point grbEndP = new Point(500, 500);
        Point yelbStartP = new Point(450, 450);
        Point yelbEndP = new Point(600, 600);
        MultipleFramesBouncingBallsAnimation begin = new MultipleFramesBouncingBallsAnimation();
        begin.bounceBalls(args, grbStartP, grbEndP, yelbStartP, yelbEndP);
    }
}