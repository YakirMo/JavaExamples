import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creats a bouncing Ball animation.
 */
public class BouncingBallAnimation {
    /**
     * .
     * Main method
     *
     * @param args - no user args
     */
    public static void main(String[] args) {
        Point startBoundary = new Point(0, 0);
        Point endBoundary = new Point(200, 200);
        GUI gui = new GUI("title", (int) endBoundary.getX(), (int) endBoundary.getY());
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK, startBoundary, endBoundary);
        ball.setVelocity(2, 2);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}