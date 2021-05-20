import java.util.Map;
import java.util.TreeMap;

/**
 * BaseExpression is an abstract class that represents basic expressions.
 */
public abstract class BaseExpression {

    /**
     * Evaluate an expression by using the variables' values in the assignment and return the evaluation.
     * @param assignment - the map containing the variables' values
     * @return the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluates an expression without using a map.
     * @return - the evaluated expression
     * @throws Exception - if the expression given contains variables
     */
    public double evaluate() throws Exception {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);
    }
}