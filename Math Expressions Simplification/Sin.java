import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Sin is a class that represents the Sine function.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * constructor.
     * @param exp - a given expression
     */
    public Sin(Expression exp) {
        super(exp);
    }

    /**
     * constructor.
     * @param str - a given string
     */
    public Sin(String str) {
        super(str);
    }

    /**
     * constructor.
     * @param num - a given num
     */
    public Sin(double num) {
        super(num);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the Sine value of an expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double result;
            result = Math.sin(Math.toRadians(this.getExp().evaluate(assignment)));
            return result;
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * evaluates an expression without using a map.
     * an exception is thrown if the given expression contains variables
     * calculates the Sine value of an expression
     * @return - the evaluated expression
     * @throws Exception - if the given expression contains variables
     */
    public double evaluate() throws Exception {
       try {
           Map<String, Double> m = new TreeMap<>();
           double result;
           result = Math.sin(Math.toRadians(this.getExp().evaluate(m)));
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
        Sin s = new Sin(this.getExp().assign(var, exp));
        return s;
    }

    /**
     * specified toString method for Sin expressions.
     * @return - a string representation of a Cos expression
     */
    @Override
    public String toString() {
        String s;
        s = "sin(" + this.getExp().toString() + ")";
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
     * the differentiation of a Sin expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Mult m = new Mult(new Cos(getExp()), this.getExp().differentiate(var));
        return m;
    }

    /**
     * simplifies expressions based on mathematical logic.
     * if it can't evaluate the numerical value of an expression, it will try to simplify the expression
     * as far as possible by checking and applying special cases of simplifications (indicated by line comments)
     * @return - a new simplified expression
     */
    public Expression simplify() {
        try {
            Num n = new Num(this.getExp().evaluate());
            return n;
        } catch (Exception e) {
            // sin ( - x) = - sin (x)
            if (this.getExp().simplify() instanceof Neg) {
                return new Neg(new Sin(((Neg) this.getExp().simplify()).getExp().simplify()));
            }
            return new Sin(this.getExp().simplify());
        }
    }
}
