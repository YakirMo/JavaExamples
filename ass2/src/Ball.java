import biuoop.DrawSurface;

import java.awt.Color;

/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creates Ball instances.
 */
public class Ball {
    // constructor
    private Point centerP;
    private int rad;
    private Color col;
    private Velocity veloc;
    private Point startBoundary;
    private Point endBoundary;
    // 4 constructors (Point / X,Y), 2 if boundaries weren't defined

    /**
     * .
     * A constructor
     *
     * @param center - center Point of a Ball
     * @param r      - Ball's radius
     * @param color  - Ball's colors
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.centerP = center;
        this.rad = r;
        this.col = color;
        this.startBoundary = new Point(0, 0);
        this.endBoundary = new Point(500, 500);
        this.centerP = validatePointLocation(centerP.getX(), centerP.getY());
    }

    /**
     * .
     * A constructor
     *
     * @param x     - X value of a center Point of a Ball
     * @param y     - Y value of a center Point of a Ball
     * @param r     - Ball's radius
     * @param color - Ball's color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.centerP = new Point(x, y);
        this.rad = r;
        this.col = color;
        this.startBoundary = new Point(0, 0);
        this.endBoundary = new Point(500, 500);
        this.centerP = validatePointLocation(x, y);
    }

    /**
     * .
     * A constructor
     *
     * @param center        - center Point of a Ball
     * @param r             - Ball's radius
     * @param color         - Ball's color
     * @param startBoundary - starting Point of Boundary (upper-left)
     * @param endBoundary   - starting Point of Boundary (right-bottom)
     */
    public Ball(Point center, int r, java.awt.Color color, Point startBoundary, Point endBoundary) {
        this.centerP = center;
        this.rad = r;
        this.col = color;
        this.startBoundary = startBoundary;
        this.endBoundary = endBoundary;
        this.centerP = validatePointLocation(centerP.getX(), centerP.getY());
    }

    /**
     * .
     * A constructor
     *
     * @param x             - X value of a center Point of a Ball
     * @param y             - Y value of a center Point of a Ball
     * @param r             - Ball's radius
     * @param color         - Ball's color
     * @param startBoundary - starting Point of Boundary (upper-left)
     * @param endBoundary   - starting Point of Boundary (right-bottom)
     */
    public Ball(int x, int y, int r, java.awt.Color color, Point startBoundary, Point endBoundary) {
        this.centerP = new Point(x, y);
        this.rad = r;
        this.col = color;
        this.startBoundary = startBoundary;
        ;
        this.endBoundary = endBoundary;
        this.centerP = validatePointLocation(x, y);
    }

    /**
     * .
     * An accessor
     *
     * @return - X value of center Point
     */
    public int getX() {
        return (int) this.centerP.getX();
    }

    /**
     * .
     * An accessor
     *
     * @return - Y value of center Point
     */
    public int getY() {
        return (int) this.centerP.getY();
    }

    /**
     * .
     * An accessor
     *
     * @return - Radius of a Ball
     */
    public int getSize() {
        return this.rad;
    }

    /**
     * .
     * An accessor
     *
     * @return - Color of a Ball
     */
    public java.awt.Color getColor() {
        return this.col;
    }

    /**
     * .
     * This method draws a Ball on a given DrawSurface
     *
     * @param surface - the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(col);
        surface.fillCircle(this.getX(), this.getY(), this.rad);
    }

    /**
     * .
     * This method sets the Velocity of a Ball
     *
     * @param v - Velocity object to apply on a Ball
     */
    public void setVelocity(Velocity v) {
        this.veloc = v;
    }

    /**
     * .
     * This method make's the change on the Ball's center Point (X,Y)
     *
     * @param dx - the position change for X value (of center Point)
     * @param dy - the position change for Y value (of center Point)
     */
    public void setVelocity(double dx, double dy) {
        this.veloc = new Velocity(dx, dy);
    }

    /**
     * .
     * An accessor
     *
     * @return - Velocity of a Ball
     */
    public Velocity getVelocity() {
        return this.veloc;
    }

    /**
     * .
     * This method moves the Ball in an area, minding boundaries of the
     * DrawSurface, by calculating Ball's location, Radius and
     * future moves (dx, dy)
     */
    public void moveOneStep() {
        if (this.centerP.getX() + this.rad + this.veloc.getDx() >= endBoundary.getX()) {
            this.setVelocity(-1 * this.veloc.getDx(), this.veloc.getDy());
        }
        if (this.centerP.getX() - this.rad + this.veloc.getDx() <= startBoundary.getX()) {
            this.setVelocity(-1 * this.veloc.getDx(), this.veloc.getDy());
        }
        if (this.centerP.getY() + this.rad + this.veloc.getDy() >= endBoundary.getY()) {
            this.setVelocity(this.veloc.getDx(), -1 * this.veloc.getDy());
        }
        if (this.centerP.getY() - this.rad + this.veloc.getDy() <= startBoundary.getY()) {
            this.setVelocity(this.veloc.getDx(), -1 * this.veloc.getDy());
        }
        this.centerP = this.getVelocity().applyToPoint(this.centerP);
    }

    /**
     * .
     * This method validates that a Ball is within the boundaries given
     * if not, it'll fix it by setting new center Point to the Ball
     *
     * @param x - current X of center Point of a Ball
     * @param y - current Y of center Point of a Ball
     * @return - corrected center Point of a ball
     */
    public Point validatePointLocation(double x, double y) {
        double correctX = x;
        double correctY = y;
        //checks left and right boundaries
        if (x - this.rad < startBoundary.getX()) {
            correctX = this.startBoundary.getX() + this.rad;
        } else if (x + this.rad >= endBoundary.getX()) {
            correctX = endBoundary.getX() - this.rad;
        }
        //checks upper and bottom boundaries
        if (y - this.rad < startBoundary.getY()) {
            correctY = this.startBoundary.getY() + this.rad;
        } else if (y + this.rad >= endBoundary.getY()) {
            correctY = endBoundary.getY() - this.rad;
        }
        return new Point(correctX, correctY);
    }

    /**
     * .
     * This method validates the radius of a given Ball
     *
     * @return - true or false whether the radius is valid or not
     */
    public boolean validRad() {
        if (this.rad <= ((endBoundary.getX() - startBoundary.getX()) / 2)
                && (this.rad <= (endBoundary.getY()
                - startBoundary.getY()) / 2)) {
            return true;
        }
        return false;
    }
}