import java.util.List;
import java.util.Map;

/**
 * Div is a class that represents the mathematical division operation.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * constructor (for expressions).
     * @param left - left expression
     * @param right - right expression
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public Div(double left, double right) {
        super(left, right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public Div(String left, String right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param left - left expression
     * @param right - a double (for numbers)
     */
    public Div(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and variables).
     * @param left - left expression
     * @param right - a string (for variables)
     */
    public Div(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param right - right expression
     */
    public Div(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param right - right expression
     */
    public Div(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public Div(double left, String right) {
        super(left, right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public Div(String left, double right) {
        super(left, right);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the division of left expression by right expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double result;
            if (this.getRightExp().evaluate(assignment) == 0) {
                throw new Exception("Can't divide by 0");
            }
            result = (this.getLeftExp().evaluate(assignment) / this.getRightExp().evaluate(assignment));
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
        Div d = new Div(this.getLeftExp().assign(var, exp), this.getRightExp().assign(var, exp));
        return d;
    }

    /**
     * specified toString method for Div expressions.
     * @return - a string representation of a Div expression
     */
    @Override
    public String toString() {
        String s;
        s = "(" + this.getLeftExp().toString() + " / " + this.getRightExp().toString() + ")";
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
     * the differentiation of a Div expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Div d = new Div(
                    new Minus(
                        new Mult(this.getLeftExp().differentiate(var), this.getRightExp()),
                        new Mult(this.getLeftExp(), this.getRightExp().differentiate(var))),
                        new Pow(this.getRightExp(), 2));
        return d;
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
            // x / x = 1
            if (this.getLeftExp().simplify().toString().equals(this.getRightExp().simplify().toString())) {
                Num n = new Num(1);
                return n;
            }
            // x / 1 = x
            if (this.getRightExp().simplify().toString().equals("1.0")) {
                return this.getLeftExp().simplify();
            }
            // 0 / x = 0
            if (this.getLeftExp().simplify().toString().equals("0.0")) {
                Num n = new Num(0);
                return n;
            }
            // (x ^ n) / (x ^ m) = x ^ (n - m)
            if (this.getLeftExp().simplify() instanceof Pow && this.getLeftExp().simplify() instanceof Pow) {
                Expression power1 = ((Pow) this.getLeftExp()).getRightExp().simplify();
                Expression power2 =  ((Pow) this.getRightExp()).getRightExp().simplify();
                Expression expo1 = ((Pow) this.getLeftExp()).getLeftExp().simplify();
                Expression expo2 = ((Pow) this.getRightExp()).getLeftExp().simplify();
                if (expo1.simplify().toString().equals(expo2.simplify().toString())) {
                    Pow p = new Pow(((Pow) this.getLeftExp().simplify()).getLeftExp(), new Minus(power1, power2));
                    return p;
                }
            }
            // (- x / x) or (x  / - x)
            if (new Neg(this.getLeftExp().simplify()).toString().equals(this.getRightExp().simplify().toString())
                    || (new Neg(this.getRightExp().simplify()).toString().equals(
                            this.getLeftExp().simplify().toString()))) {
                Num n = new Num(-1);
                return n;
            }
            // sin (x) / cos (x) = tan (x)
            if (this.getLeftExp().simplify() instanceof Sin && this.getRightExp().simplify() instanceof Cos) {
                Tan t = new Tan(((Sin) (this.getLeftExp().simplify())).getExp());
                return t;
            }
        }
        Div d = new Div(this.getLeftExp().simplify(), this.getRightExp().simplify());
        return d;
    }
}
