import java.util.List;
import java.util.Map;

/**
 * Minus is a class representing the mathematical minus operation.
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * constructor (for expressions).
     * @param left - left expression
     * @param right - right expression
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public Minus(double left, double right) {
        super(left, right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public Minus(String left, String right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param left - left expression
     * @param right - a double (for numbers)
     */
    public Minus(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and variables).
     * @param left - left expression
     * @param right - a string (for variables)
     */
    public Minus(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param right - right expression
     */
    public Minus(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param right - right expression
     */
    public Minus(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public Minus(double left, String right) {
        super(left, right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public Minus(String left, double right) {
        super(left, right);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the subtraction of left expression by right expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
       try {
           double result;
           result = (this.getLeftExp().evaluate(assignment) - this.getRightExp().evaluate(assignment));
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
        Minus m = new Minus(this.getLeftExp().assign(var, exp), this.getRightExp().assign(var, exp));
        return m;
    }

    /**
     * specified toString method for Minus expressions.
     * @return - a string representation of a Minus expression
     */
    @Override
    public String toString() {
        String s;
        s = "(" + this.getLeftExp().toString() + " - " + this.getRightExp().toString() + ")";
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
     * the differentiation of a Minus expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Minus m = new Minus(this.getLeftExp().differentiate(var), this.getRightExp().differentiate(var));
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
        } catch (Exception e) {
            // 0 - x = - x
            if (this.getLeftExp().simplify().toString().equals("0.0")) {
                return new Neg(this.getRightExp().simplify());
            }
            // x - 0 = x
            if (this.getRightExp().simplify().toString().equals("0.0")) {
                return this.getLeftExp().simplify();
            }
            // x - x = 0
            if (this.getLeftExp().simplify().toString().equals(this.getRightExp().simplify().toString())) {
                Num n = new Num(0);
                return n;
            }
            // x - (- x) = x + x
            if (this.getRightExp() instanceof Neg) {
                Plus p = new Plus(this.getLeftExp().simplify(), new Neg(this.getRightExp().simplify()).simplify());
                p.simplify();
                return p;
            }

            // log a - log b = log (a / b)
            if (this.getLeftExp().simplify() instanceof Log && this.getRightExp().simplify() instanceof Log) {
                if (((Log) this.getLeftExp()).getRightExp().simplify().toString().equals(
                ((Log) this.getRightExp()).getRightExp().simplify().toString())) {
                    Log l = new Log(
                            new Div(
                                    ((Log) this.getLeftExp()).getLeftExp(),
                                    ((Log) this.getRightExp()).getLeftExp()), ((Log) this.getRightExp()).getRightExp());
                    return l;
                }
            }
            // x * a - x * b = x(a + b)
            if (this.getLeftExp().simplify() instanceof Mult && this.getRightExp().simplify() instanceof Mult) {
                if (((Mult) this.getLeftExp()).getRightExp().simplify().toString().equals(
                        ((Mult) this.getRightExp()).getRightExp().simplify().toString())) {
                    Mult m = new Mult(
                            new Minus(
                                    ((Mult) this.getLeftExp()).getLeftExp(), ((Mult) this.getRightExp()).getLeftExp()),
                            ((Mult) this.getLeftExp()).getRightExp());
                    return m;
                }
            }
        }
        Minus m = new Minus(this.getLeftExp().simplify(), this.getRightExp().simplify());
        return m;
    }
}
