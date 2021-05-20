import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Tan is a class that represents the Tangent function.
 */
public class Tan extends UnaryExpression implements Expression {

    /**
     * constructor.
     * @param exp - a given expression
     */
    public Tan(Expression exp) {
        super(exp);
    }

    /**
     * constructor.
     * @param str - a given string
     */
    public Tan(String str) {
        super(str);
    }

    /**
     * constructor.
     * @param num - a given num
     */
    public Tan(double num) {
        super(num);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the Tangent value of an expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double result;
            result = Math.tan(Math.toRadians(this.getExp().evaluate(assignment)));
            return result;
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * evaluates an expression without using a map.
     * an exception is thrown if the given expression contains variables
     * calculates the Tangent value of an expression
     * @return - the evaluated expression
     * @throws Exception - if the given expression contains variables
     */
    public double evaluate() throws Exception {
        try {
            Map<String, Double> m = new TreeMap<>();
            double result;
            result = Math.tan(Math.toRadians(this.getExp().evaluate(m)));
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * assign the expression to all the variables that are identical to 'var' with the given expression, recursively.
     * @param var - the var to be replaced
     * @param exp - the expression to replace with
     * @return - a new expression
     */
    public Expression assign(String var, Expression exp) {
        Tan t = new Tan(this.getExp().assign(var, exp));
        return t;
    }

    /**
     * specified toString method for Tan expressions.
     * @return - a string representation of a Cos expression
     */
    @Override
    public String toString() {
        String s;
        s = "Tan(" + this.getExp().toString() + ")";
        return s;
    }


    /**
     * .
     * @return - a list with all variables in an expression.
     */
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * supposedly differentiate Tan expressions.
     * @param var - the var to be differentiated
     * @return - null
     */
    public Expression differentiate(String var) {
        return null;
    }

    /**
     * supposedly simplify Tan expressions.
     * @return - null
     */
    public Expression simplify() {
        return null;
    }
}
