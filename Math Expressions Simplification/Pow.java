import java.util.List;
import java.util.Map;

/**
 *  Pow is a class representing the mathematical power operation.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * constructor (for expressions).
     * @param left - left expression
     * @param right - right expression
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public Pow(double left, double right) {
        super(left, right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public Pow(String left, String right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param left - left expression
     * @param right - a double (for numbers)
     */
    public Pow(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and variables).
     * @param left - left expression
     * @param right - a string (for variables)
     */
    public Pow(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param right - right expression
     */
    public Pow(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param right - right expression
     */
    public Pow(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public Pow(double left, String right) {
        super(left, right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public Pow(String left, double right) {
        super(left, right);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the power of left expression by right expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
       try {
           double result;
           result = Math.pow(this.getLeftExp().evaluate(assignment), this.getRightExp().evaluate(assignment));
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
        Pow p = new Pow(this.getLeftExp().assign(var, exp), this.getRightExp().assign(var, exp));
        return p;
    }

    /**
     * specified toString method for Pow expressions.
     * @return - a string representation of a Pow expression
     */
    @Override
    public String toString() {
        String s;
        s = "(" + this.getLeftExp().toString() + "^" + this.getRightExp().toString() + ")";
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
     * the differentiation of a Pow expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Mult m = new Mult(
                      new Pow(this.getLeftExp(), this.getRightExp()), new Plus(
                              new Mult(this.getLeftExp().differentiate(var), new Div(
                                      this.getLeftExp(), this.getRightExp())),
                                      new Mult(this.getRightExp().differentiate(var),
                                      new Log(new Var("e"), this.getLeftExp()))));
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
            // x ^ 0 = 1
            if (this.getRightExp().simplify().toString().equals("0.0")) {
                Num n = new Num(1);
                return n;
            }
            // x ^ 1 = x
            if (this.getRightExp().simplify().toString().equals("1.0")) {
                return this.getLeftExp().simplify();
            }
            // 0 ^ x = 0
            if (this.getLeftExp().simplify().toString().equals("0.0")) {
                Num n = new Num(0);
                return n;
            }
            // 1 ^ x = 1
            if (this.getLeftExp().simplify().toString().equals("1.0")) {
                Num n = new Num(1);
                return n;
            }
            // (x ^ y) ^ z
            if (this.getLeftExp().simplify() instanceof Pow) {
                Expression base = ((Pow) this.getLeftExp()).getLeftExp().simplify();
                Expression expo = ((Pow) this.getLeftExp()).getRightExp().simplify();
                Pow p = new Pow(base, new Mult(expo, this.getRightExp().simplify()));
                return p;
            }
        }
        Pow p = new Pow(this.getLeftExp().simplify(), this.getRightExp().simplify());
        return p;
    }
}
