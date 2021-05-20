import java.util.List;

/**
 * UnaryExpression is a class that represents expressions that consist one expression.
 */
public class UnaryExpression {
    private Expression exp;

    /**
     * constructor (for an expression).
     * @param exp - an expression to be set (as unary expression)
     */
    public UnaryExpression(Expression exp) {
    this.exp = exp;
    }

    /**
     * constructor (for numbers).
     * @param num - a double to be set (as unary expression)
     */
    public UnaryExpression(double num) {
        this.exp = new Num(num);
    }

    /**
     * constructor (for strings).
     * @param var - a string to be set (as unary expression)
     */
    public UnaryExpression(String var) {
        this.exp = new Var(var);
    }


    /**
     * .
     * @return - a list with all variables in an expression.
     */
    public List<String> getVariables() {
        return this.exp.getVariables();
    }

    /**
     * getter.
     * @return - the given unary expression
     */
    protected Expression getExp() {
        return this.exp;
    }
}
