import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creats graphical abstract art drawing.
 */
public class AbstractArtDrawing {
    /**
     * .
     * This method creates a random Line in a given area
     *
     * @return - the newly generated random Line
     */
    public Line randomizeLine() {
        Random rand = new Random();
        Point start = new Point((rand.nextDouble() * 400),
                (rand.nextDouble() * 300));
        Point end = new Point((rand.nextDouble() * 400),
                (rand.nextDouble() * 300));
        Line randLine = new Line(start, end);
        return randLine;
    }

    /**
     * .
     * This method draws a black Line on a DrawSurface
     *
     * @param l - the Line to be drawn
     * @param d - the DrawSurface a Line will be drawn on
     */
    public void drawLine(Line l, DrawSurface d) {
        int x1, y1, x2, y2;
        x1 = (int) l.start().getX();
        y1 = (int) l.start().getY();
        x2 = (int) l.end().getX();
        y2 = (int) l.end().getY();
        d.setColor(Color.BLACK);
        d.drawLine(x1, y1, x2, y2);
    }

    /**
     * .
     * This method draws in blue the middle Point of a Line
     *
     * @param l - the Line which middle Point will be drawn
     * @param d - the DrawSurface to draw on
     */
    public void drawMidPLine(Line l, DrawSurface d) {
        int midX, midY;
        midX = (int) l.middle().getX();
        midY = (int) l.middle().getY();
        d.setColor(Color.BLUE);
        d.fillCircle(midX, midY, 3);
    }

    /**
     * .
     * This method draws the intersections Points of Lines
     *
     * @param linesArr - the Lines which intersection Points will be drawn
     * @param d        - the DrawSurface to draw on
     */
    public void drawIntersection(Line[] linesArr, DrawSurface d) {
        int i, j, interX, interY;
        for (i = 0; i < linesArr.length - 1; ++i) {
            for (j = i + 1; j < linesArr.length; ++j) {
                if (linesArr[i].isIntersecting(linesArr[j])) {
                    interX = (int) (linesArr[i].intersectionWith(linesArr[j]).getX());
                    interY = (int) (linesArr[i].intersectionWith(linesArr[j]).getY());
                    d.setColor(Color.RED);
                    d.fillCircle(interX, interY, 3);
                }
            }
        }
    }

    /**
     * .
     * This method draws 10 random Lines on a surface, including their
     * middle points (painted blue) and intersection Points (painted red)
     */
    public void drawAbstractArt() {
        int i;
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] linesArr = new Line[10];
        for (i = 0; i < 10; i++) {
            linesArr[i] = randomizeLine();
            drawLine(linesArr[i], d);
            drawMidPLine(linesArr[i], d);
        }
        drawIntersection(linesArr, d);
        gui.show(d);
    }

    /**
     * .
     * Main method of the program
     *
     * @param args - no user args
     */
    public static void main(String[] args) {
        AbstractArtDrawing art = new AbstractArtDrawing();
        art.drawAbstractArt();
    }
}