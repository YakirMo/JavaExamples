/**
 * A demo for advanced simplification.
 */
public class SimplificationDemo {
    /**
    * Main method to test advanced simplifications.
    * @param args - none.
    */
    public static void main(String[] args) {
        // x + x = 2x
        Expression e1 = new Plus("x", "x");
        System.out.println(e1.toString());
        System.out.println(e1.simplify().toString());
        System.out.println();
        // x + (- x) = 0
        Expression e2 = new Plus("x", new Neg("x"));
        System.out.println(e2.toString());
        System.out.println(e2.simplify().toString());
        System.out.println();
        //(- x) + x = 0
        Expression e3 = new Plus(new Neg("x"), "x");
        System.out.println(e3.toString());
        System.out.println(e3.simplify().toString());
        System.out.println();
        // x * a + x * b = x(a + b)
        Expression e4 = new Plus(new Mult("x", "a"), new Mult("x", "b"));
        System.out.println(e4.toString());
        System.out.println(e4.simplify().toString());
        System.out.println();
        // log(y, x) + log(z, x) = log((y*x), x)
        Expression e5 = new Plus(new Log("y", "x"), new Log("z", "x"));
        System.out.println(e5.toString());
        System.out.println(e5.simplify().toString());
        System.out.println();
        // (x ^ y) ^ z = x ^ (y * z)
        Expression e6 = new Pow(new Pow("x", "y"), "z");
        System.out.println(e6.toString());
        System.out.println(e6.simplify().toString());
        System.out.println();
        // x - (- y) =  x + y
        Expression e7 = new Minus("x", new Neg("y"));
        System.out.println(e7.toString());
        System.out.println(e7.simplify().toString());
        System.out.println();
        // x * a - x * b = x(a + b)
        Expression e8 = new Minus(new Mult("x", "a"), new Mult("x", "b"));
        System.out.println(e8.toString());
        System.out.println(e8.simplify().toString());
        System.out.println();
        // log(y, x) - log(z, x) = log((y*x), x)
        Expression e9 = new Minus(new Log("y", "x"), new Log("z", "x"));
        System.out.println(e9.toString());
        System.out.println(e9.simplify().toString());
        System.out.println();
        // x * x = x ^ 2
        Expression e10 = new Mult("x", "x");
        System.out.println(e10.toString());
        System.out.println(e10.simplify().toString());
        System.out.println();
        // x * (y / x) = y
        Expression e11 = new Mult("x", new Div("y", "x"));
        System.out.println(e11.toString());
        System.out.println(e11.simplify().toString());
        System.out.println();
        // x ^ n * x ^ m = x ^ (n + m)
        Expression e12 = new Mult(new Pow("x", "n"), new Pow("x", "m"));
        System.out.println(e12.toString());
        System.out.println(e12.simplify().toString());
        System.out.println();
        // x ^ n / x ^ m = x ^ (n - m)
        Expression e13 = new Div(new Pow("x", "n"), new Pow("x", "m"));
        System.out.println(e13.toString());
        System.out.println(e13.simplify().toString());
        System.out.println();
        // - x / x || x / - x = -1
        Expression e14 = new Div(new Neg("x"), "x");
        System.out.println(e14.toString());
        System.out.println(e14.simplify().toString());
        System.out.println();
        // log (y, (x ^ b)) = b * log (y, x)
        Expression e15 = new Log(new Pow("y", "b"), "x");
        System.out.println(e15.toString());
        System.out.println(e15.simplify().toString());
        System.out.println();
        // - (- x) = x
        Expression e16 = new Neg(new Neg("x"));
        System.out.println(e16.toString());
        System.out.println(e16.simplify().toString());
        System.out.println();
        // Sin (- x) = - Sin (x)
        Expression e17 = new Sin(new Neg("x"));
        System.out.println(e17.toString());
        System.out.println(e17.simplify().toString());
        System.out.println();
        // Cos (- x) =  Cos (x)
        Expression e18 = new Cos(new Neg("x"));
        System.out.println(e18.toString());
        System.out.println(e18.simplify().toString());
        System.out.println();
        // Cos (x + y) / Cos (y + x) = 1
        Expression e19 = new Div(new Cos(new Plus("x", "y")), new Cos(new Plus("y", "x")));
        System.out.println(e19.toString());
        System.out.println(e19.simplify().toString());
        System.out.println();
        // Sin (x) / Cos (x) = Tan (x)
        Expression e20 = new Div(new Sin("x"), new Cos("x"));
        System.out.println(e20.toString());
        System.out.println(e20.simplify().toString());
        System.out.println();
    }
}
