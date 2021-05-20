/**
 * .
 *
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * the Velocity class specifies the change in position
 * on the `x` and the `y` axes of a Ball.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * .
     * A constructor
     *
     * @param dx - the change on the position of X
     * @param dy - the change on the position of Y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * .
     * An accessor
     *
     * @return - the position change of the X axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * .
     * An accessor
     *
     * @return - the position change of the Y axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * .
     * This method changes the position of a Ball by adding the dx and dy
     * values to the center Point of a Ball (x+dx, y+dy)
     *
     * @param p - the Point to change its position
     * @return - the new Point after position change
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        Point newP = new Point(newX, newY);
        return newP;
    }

    /**
     * .
     * This method constructs a Velocity object from a polar representation
     *
     * @param angle - angle of the vector
     * @param speed - speed of the vector
     * @return - Velocity object with dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radAngle = Math.toRadians(angle);
        double dx = (Math.sin(radAngle) * speed);
        double dy = (Math.cos(radAngle) * speed);
        return new Velocity(dx, dy);
    }
}