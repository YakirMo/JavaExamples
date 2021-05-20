import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Var is a class that represents variables.
 */
public class Var implements Expression {
    private String var;

    /**
     * constructor.
     * @param var - the variables to be presented as an expression
     */
    public Var(String var) {
        this.var = var;
    }

    /**
     *  evaluates the value of a variable (if) provided by the map and returns the evaluation.
     * @param assignment - a map containing the variables' values
     * @return - the evaluated variable
     * @throws Exception - if the expression contains a variable not in the map (which means has no value)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.var)) {
            return assignment.get(this.var);
        }
        throw new Exception("Can't evaluate variable: \"" + this.var + "\" - No assigment to compare with.");
    }

    /**
     * throws exception if called, because a variables can't be evaluated without having a value.
     * @return - the evaluated variable
     * @throws Exception - if there is no map to compare to
     */
    public double evaluate() throws Exception {
        throw new Exception("Can't evaluate variable: \"" + this.var + "\" - No assigment to compare with.");
    }

    /**
     * getter.
     * @return - a list with all the variables
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.var);
        return list;
    }

    /**
     * .
     * @return - a string representation for a variable (already defined as a string)
     */
    public String toString() {
        return this.var;
    }

    /**
     * returns a new expression that replaces the variable with the given expression if identical.
     * @param v - the variable to be replaced
     * @param expression - the expression to replace with
     * @return - a new expression
     */
    public Expression assign(String v, Expression expression) {
    if (this.var.equals(v)) {
        return expression;
    }
    return this;
    }

    /**
     * the derivative of any variable is 1, unless differentiated with another variable.
     * @param v - the variable to be derived
     * @return - the derivative of a variable
     */
    public Expression differentiate(String v) {
        if (this.var.equals(v)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * nothing to simplify in a variable.
     * @return - the variable itself
     */
    public Expression simplify() {
        return this;
    }

}
