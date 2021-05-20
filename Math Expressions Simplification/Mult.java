import java.util.List;
import java.util.Map;

/**
 * Mult is a class representing the mathematical multiplication operation.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * constructor (for expressions).
     * @param left - left expression
     * @param right - right expression
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public Mult(double left, double right) {
        super(left, right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public Mult(String left, String right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param left - left expression
     * @param right - a double (for numbers)
     */
    public Mult(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor (for expressions and variables).
     * @param left - left expression
     * @param right - a string (for variables)
     */
    public Mult(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param right - right expression
     */
    public Mult(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param right - right expression
     */
    public Mult(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public Mult(double left, String right) {
        super(left, right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public Mult(String left, double right) {
        super(left, right);
    }

    /**
     * evaluates the expression using the given variables values in 'assignment' and returns the evaluation.
     * an exception is thrown if the expression contains a variable that is not assigned (in the assignment)
     * calculates the multiplication of left expression by right expression
     * @param assignment - a map containing the variables' values
     * @return - the evaluated expression
     * @throws Exception - if an expression contains a variable that is not assigned (in the assignment)
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            double result;
            result = (this.getLeftExp().evaluate(assignment) * this.getRightExp().evaluate(assignment));
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
        Mult m = new Mult(this.getLeftExp().assign(var, exp), this.getRightExp().assign(var, exp));
        return m;
    }

    /**
     * specified toString method for Mult expressions.
     * @return - a string representation of a Mult expression
     */
    @Override
    public String toString() {
        String s;
        s = "(" + this.getLeftExp().toString() + " * " + this.getRightExp().toString() + ")";
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
     * the differentiation of a Mult expression.
     * @param var - a variable to differentiate
     * @return - the derivative of an expression
     */
    public Expression differentiate(String var) {
        Plus p = new Plus(new Mult(this.getLeftExp().differentiate(var), this.getRightExp()),
                new Mult(this.getLeftExp(), this.getRightExp().differentiate(var)));
        return p;
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
            super.expArrange();
            // x * 0 || 0 * x = 0
            if (this.getLeftExp().simplify().toString().equals("0.0")
                    || this.getRightExp().simplify().toString().equals("0.0")) {
                Num n = new Num(0);
                return n;
            }
            // 1 * x = x
            if (this.getLeftExp().simplify().toString().equals("1.0")) {
                return this.getRightExp().simplify();
            }
            // x * 1 = x
            if (this.getRightExp().simplify().toString().equals("1.0")) {
                return this.getLeftExp().simplify();
            }
            // x * x = x ^ 2
            if (this.getLeftExp().simplify().toString().equals(this.getRightExp().simplify().toString())) {
                Pow p = new Pow(this.getLeftExp(), 2);
                return p;
            }
            // x * (y / x)
            if (this.getRightExp() instanceof Div) {
                Expression numer = (((Div) this.getRightExp().simplify()).getRightExp().simplify());
                Expression denom = (((Div) this.getRightExp().simplify()).getLeftExp().simplify());
                if (this.getLeftExp().simplify().toString().equals(denom.simplify().toString())) {
                    return numer;
                }
                Mult m = new Mult(this.getLeftExp().simplify(), this.getRightExp().simplify());
                return m;
            }
            // (y / x) * x
            if (this.getLeftExp() instanceof Div) {
                Expression numer = ((Div) this.getLeftExp().simplify()).getLeftExp().simplify();
                Expression denom = ((Div) this.getLeftExp().simplify()).getRightExp().simplify();
                if (this.getRightExp().simplify().toString().equals(denom.simplify().toString())) {
                    return numer;
                }
            }
            // (x ^ n) * (x ^ m) = x ^ (n + m)
            if (this.getLeftExp().simplify() instanceof Pow && this.getRightExp().simplify() instanceof Pow) {
                Expression pow1 = ((Pow) this.getLeftExp()).getRightExp().simplify();
                Expression pow2 = ((Pow) this.getRightExp()).getRightExp().simplify();
                Expression expo1 = ((Pow) this.getLeftExp()).getLeftExp().simplify();
                Expression expo2 = ((Pow) this.getRightExp()).getLeftExp().simplify();
                if (expo1.simplify().toString().equals(expo2.simplify().toString())) {
                    Pow p = new Pow(((Pow) this.getLeftExp().simplify()).getLeftExp(), new Plus(pow1, pow2));
                    return p;
                }
            }
        }
        Mult m = new Mult(this.getLeftExp().simplify(), this.getRightExp().simplify());
        return m;
    }
}
