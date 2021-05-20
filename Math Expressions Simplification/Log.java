import java.util.List;
import java.util.Map;

/**
 * Log is a class that represents the Logarithm function.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * constructor (for expressions).
     * @param left - left expression
     * @param right - right expression
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public Log(double left, double right) {
        super(left, right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public Log(String left, String right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param left - left expression
     * @param right - a double (for numbers)
     */
    public Log(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and variables).
     * @param left - left expression
     * @param right - a string (for variables)
     */
    public Log(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param right - right expression
     */
    public Log(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param right - right expression
     */
    public Log(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public Log(double left, String right) {
        super(left, right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public Log(String left, double right) {
        super(left, right);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the logarithm value of an expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
           double result, logBase, inLog;
           logBase = this.getRightExp().evaluate(assignment);
           inLog = this.getLeftExp().evaluate(assignment);
           if (inLog <= 0.0 || inLog == 1.0 || logBase <= 0.0) {
               throw new ArithmeticException();
           }
           result = (Math.log(inLog) / Math.log(logBase));
           return result;
    }

    /**
     * assign the expression to all the variables that are identical to 'var' with the given expression, recursively.
     * @param var - the var to be replaced
     * @param exp - the expression to replace with
     * @return - a new expression
     */
    public Expression assign(String var, Expression exp) {
        Log l = new Log(this.getLeftExp().assign(var, exp), this.getRightExp().assign(var, exp));
        return l;
    }

    /**
     * specified toString method for Log expressions.
     * @return - a string representation of a Log expression
     */
    @Override
    public String toString() {
        String s;
        s = "Log(" + this.getLeftExp().toString() + ", " + this.getRightExp().toString() + ")";
        return s;
    }

    /**
     * .
     * @return - a list with all variables in an expression.
     */
    public List<String> getVariables() {
        return super.getVars();
    }

    /**
     * the differentiation of a Log expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Mult m = new Mult(new Div(new Num(1), new Mult(this.getLeftExp(), new Log(this.getRightExp(), "e"))),
                this.getLeftExp().differentiate(var));
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
            Num n = new Num(this.evaluate());
            return n;
        } catch (ArithmeticException e) {
            System.out.println("Illegal Log");
        } catch (Exception e) {
            // log (x, x) = 1
            if (this.getLeftExp().simplify().toString().equals(this.getRightExp().simplify().toString())) {
                Num n = new Num(1);
                return n;
            }
            // log (a ^ y) = y * log a
            if (this.getLeftExp().simplify() instanceof Pow) {
                Expression power = ((Pow) this.getLeftExp().simplify()).getRightExp().simplify();
                Expression inLog = ((Pow) this.getLeftExp().simplify()).getLeftExp().simplify();
                Expression base = (this.getRightExp().simplify());
                Mult m = new Mult(power, new Log(inLog, base));
                return m;
            }
        }
            Log l = new Log(this.getLeftExp().simplify(), this.getRightExp().simplify());
            return l;
    }
}
