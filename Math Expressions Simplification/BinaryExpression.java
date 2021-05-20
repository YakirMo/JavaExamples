import java.util.ArrayList;
import java.util.List;

/**
 * BinaryExpression is an abstract class that represents expressions that consist two expressions.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExp;
    private Expression rightExp;

    /**
     * constructor (for expressions).
     * @param leftExp - left expression
     * @param rightExp - right expression
     */
    public BinaryExpression(Expression leftExp, Expression rightExp) {
        this.leftExp = leftExp;
        this.rightExp = rightExp;
    }

    /**
     * constructor (for numbers).
     * @param left - a double (for numbers)
     * @param right - a double (for numbers)
     */
    public BinaryExpression(double left, double right) {
        this.leftExp = new Num(left);
        this.rightExp = new Num(right);
    }

    /**
     * constructor (for variables).
     * @param left - a string (for variables)
     * @param right - a string (for variables)
     */
    public BinaryExpression(String left, String right) {
        this.leftExp = new Var(left);
        this.rightExp = new Var(right);
    }

    /**
     * constructor (for expressions and numbers).
     * @param leftExp - left expression
     * @param right - a double (for numbers)
     */
    public BinaryExpression(Expression leftExp, double right) {
    this.leftExp = leftExp;
    this.rightExp = new Num(right);
    }

    /**
     * constructor (for expressions and variables).
     * @param leftExp - left expression
     * @param right - a string (for variables)
     */
    public BinaryExpression(Expression leftExp, String right) {
        this.leftExp = leftExp;
        this.rightExp = new Var(right);
    }

    /**
     * constructor (for numbers and expressions).
     * @param left - a double (for numbers)
     * @param rightExp - right expression
     */
    public BinaryExpression(double left, Expression rightExp) {
        this.leftExp = new Num(left);
        this.rightExp = rightExp;
    }

    /**
     * constructor (for variables and expressions).
     * @param left - a string (for variables)
     * @param rightExp - right expression
     */
    public BinaryExpression(String left, Expression rightExp) {
        this.leftExp = new Var(left);
        this.rightExp = rightExp;
    }

    /**
     * constructor (for numbers and variables).
     * @param left - a double (for numbers)
     * @param right - a string (for variables)
     */
    public BinaryExpression(double left, String right) {
        this.leftExp = new Num(left);
        this.rightExp = new Var(right);
    }

    /**
     * constructor (for variables and numbers).
     * @param left - a string (for variables)
     * @param right - a double (for numbers)
     */
    public BinaryExpression(String left, double right) {
        this.leftExp = new Var(left);
        this.rightExp = new Num(right);
    }

    /**
     * getter.
     * @return - right expression
     */
    public Expression getRightExp() {
        return this.rightExp;
    }

    /**
     * getter.
     * @return - left expression
     */
    public Expression getLeftExp() {
        return this.leftExp;
    }

    /**
     * setter.
     * @param exp - expression to be set (left)
     */
    protected void setLeftExp(Expression exp) {
        this.leftExp = exp;
    }

    /**
     * setter.
     * @param exp - expression to be set (right)
     */
    protected void setRightExp(Expression exp) {
        this.rightExp = exp;
    }

    /**
     * adds all the variables in the expression to a list and return's the list.
     * @return - list of variables in the expression
     */
    public List<String> getVars() {
        List<String> list = new ArrayList<String>();
        list.addAll(this.leftExp.getVariables());
        list.addAll(this.rightExp.getVariables());
        return list;
    }

    /**
     * rearrange the expression for a more convenient use in the sub-classes.
     */
    public void expArrange() {
        Expression temp;
        if (getLeftExp().toString().compareTo(getRightExp().toString()) > 0) {
            temp = getRightExp();
            setRightExp(getLeftExp());
            setLeftExp(temp);
        }
    }
}
