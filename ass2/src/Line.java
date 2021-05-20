/**
 * @author Yakir Moshe <YakirMoshe@gmail.com>
 * This class creates Line instances (each defined by 2 Points).
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * .
     * Constructor (based on Points)
     *
     * @param start - start Point of a Line
     * @param end   - end Point of a Line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * .
     * Constructor (based on X's and Y's for start and end Points)
     *
     * @param x1 - x of start Point of a Line
     * @param y1 - y of start Point of a Line
     * @param x2 - x of end Point of a Line
     * @param y2 - y of end Point of a Line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * .
     * This method returns the length of a Line
     * (That by using 'distance' method of Point class)
     *
     * @return - the length of a Line
     */
    public double length() {
        return (start.distance(end));
    }

    /**
     * .
     * This method calculates the middle Point of a Line
     *
     * @return - middle Point of a line
     */
    public Point middle() {
        double midX = ((this.start.getX() + this.end.getX()) / 2);
        double midY = ((this.start.getY() + this.end.getY()) / 2);
        return new Point(midX, midY);
    }

    /**
     * .
     * This method returns the start Point of a Line
     *
     * @return - start Point of a Line
     */
    public Point start() {
        return this.start;
    }

    /**
     * .
     * This method returns the end Point of a Line
     *
     * @return - end Point of a Line
     */
    public Point end() {
        return this.end;
    }

    /**
     * .
     * This method checks whether Lines intersect
     *
     * @param other - another Line to check if current Line intersects with
     * @return - true or false whether the Lines intersect
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * .
     * This method calculates the Intersection Point between Lines
     * if such point exists
     *
     * @param other - another Line to check if current Line intersects with
     * @return - intersection point if exists, null if there's no intersection
     */
    public Point intersectionWith(Line other) {
        double slopeL1, slopeL2, intercL1, intercL2, interX, interY;
        //get L1 slope
        slopeL1 = (this.start.getY() - this.end.getY());
        slopeL1 = (slopeL1 / (this.start.getX() - this.end.getX()));
        //get L2 slope
        slopeL2 = (other.start.getY() - other.end.getY());
        slopeL2 = (slopeL2 / (other.start.getX() - other.end.getX()));
        //if slopes identical - Lines are parallel
        if (slopeL1 == slopeL2) {
            return null;
        }
        //get L1 intercept
        intercL1 = (this.end.getY() - (slopeL1 * this.end.getX()));
        //get L2 intercept
        intercL2 = (other.end.getY() - (slopeL2 * other.end.getX()));
        //get intersection X, Y (Point of intersection)
        interX = (intercL2 - intercL1) / (slopeL1 - slopeL2);
        interY = (interX * slopeL1) + intercL1;
        Point p = new Point(interX, interY);
        //Check that intersection Point is not beyond the given Lines
        if (notOverTheEdge(p) && other.notOverTheEdge(p)) {
            return p;
        }
        return null;
    }

    /**
     * .
     * This method checks if Lines are equal
     *
     * @param other - another Line to check equality on
     * @return - true or false whether Lines are identical or not
     */
    public boolean equals(Line other) {
        //check equality of the Lines' Points
        if ((this.start == other.start) && (this.end == other.end)) {
            return true;
        }
        return false;
    }

    /**
     * .
     * This method checks that an intersection Point is between the edges
     * of both Lines
     *
     * @param interP - the intersection Point to check
     * @return - true or false whether the Point is between the edges
     * or beyond
     */
    public boolean notOverTheEdge(Point interP) {
        int xMax, yMax, xMin, yMin;
        /* comparing the given point with all the start and end Points
        of both Lines */
        xMax = Math.max((int) this.start.getX(), (int) this.end.getX());
        yMax = Math.max((int) this.start.getY(), (int) this.end.getY());
        xMin = Math.min((int) this.start.getX(), (int) this.end.getX());
        yMin = Math.min((int) this.start.getY(), (int) this.end.getY());
        if ((interP.getX() >= xMin) && (interP.getY() >= yMin)
                && (interP.getX() <= xMax) && (interP.getY() <= yMax)) {
            return true;
        }
        return false;
    }
}