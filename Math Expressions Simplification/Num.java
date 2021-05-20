import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Num is a class that represents numbers.
 */
public class Num implements Expression {
    private double num;

    /**
     * constructor.
     * @param num - a number to be presented as an expression
     */
    public Num(double num) {
        this.num = num;
    }
    /**
     * @param assignment - a map containing the variables' values
     * @return - this num, no need of a map for numbers
     * @throws Exception - not thrown because a map is not used (num will always have a double value)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
    return this.num;
    }

    /**
     * @return - this num
     * @throws Exception - not thrown because a map is not used (num will always have a double value)
     */
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * @return - empty list, a num isn't a variable
     */
    public List<String> getVariables() {
    List<String> list = new ArrayList<String>();
    return list;
    }

    /**
     * @return - the string representation of a number
     */
    public String toString() {
    return ((Double) (this.num)).toString();

    }

    /**
     * return's the num, as it's not a variable.
     * @param var - the var to be replaced
     * @param expression - the expression to replace with
     * @return - a new expression
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }


    /**
     * the derivative of a number (any) is 0.
     * @param var - a variable to differentiate
     * @return - 0, the derivative of any number
     */
    public Expression differentiate(String var) {
        Num n = new Num(0);
        return n;
    }

    /**
     * nothing to simplify in a number.
     * @return - the number
     */
    public Expression simplify() {
        return this;
    }


}
