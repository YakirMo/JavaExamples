import java.util.List;
import java.util.Map;

/**
 * Expression is an interface that represents an expression.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided
     * in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * evaluates an expression without using a map.
     * an exception is thrown if the given expression contains variables
     * calculates the value of an expression
     * @return - the evaluated expression
     * @throws Exception - if the given expression contains variables
     */
    double evaluate() throws Exception;

    /**
     * .
     * @return - a list with all variables in an expression.
     */
    List<String> getVariables();

    /**
     *  toString method for multiple expressions.
     * @return - a string representation of an expression
     */
    String toString();

    /**
     * assign the expression to all the variables that are identical to 'var' with the given expression, recursively.
     * @param var - the var to be replaced
     * @param expression - the expression to replace with
     * @return - a new expression
     */
    Expression assign(String var, Expression expression);

    /**
     * the differentiation of an expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    Expression differentiate(String var);

    /**
     * simplifies expressions based on mathematical logic.
     * if it can't evaluate the numerical value of an expression, it will try to simplify the expression
     * as far as possible by checking and applying special cases of simplifications
     * @return - a new simplified expression
     */
    Expression simplify();

}
