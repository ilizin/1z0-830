package me.ilizin.certification.lambdaexpression;

public class LambdaExpressionSyntax {

    public interface LambdaType {
        public boolean test();
    }
    public interface LambdaType2 {
        public int test(int parameter);
    }
    public interface LambdaType3 {
        public int test(int parameter1, int parameter2, int parameter3);
    }
    public interface LambdaType4 {
        public void test(int parameter1, int parameter2);
    }
    public interface LambdaType5 {
        public void test();
    }

    public static void main(String[] args) {

        /* A lambda expression has two parts separated by the "arrow" operator, i.e., ->. The left side is for variable
           declarations and the right side is for the code that you want executed. Just like a method, a lambda expression
           also can have any number of parameters and can return (or not return) a value.
           The parameter types of a lambda expression (if specified explicitly) must match exactly with the parameter
           types of the method declared in the interface and the type of the returned value must be covariant with the
           return type of the interface method. */

        /* If a lambda expression takes no parameters, the parameter part of the expression must have an empty set
           of parentheses, i.e., ( ) */
        LambdaType lamda1 = () -> true; // Valid
        // -> 1 // Invalid, missing variable declaration part

        /* If a lambda expression takes exactly one parameter, the parameter name may be specified within parentheses, i.e.,
           ( pName ) or without the parentheses, i.e., pName. If you want include the parameter type then you will need
           to use parentheses. */
        LambdaType2 lambda2 = a -> a * a; // Valid
        lambda2 = (a) -> a * a; // Valid
        lambda2 = (int a) -> a*a; // Valid
        // int a -> a*a // Invalid

        /* If a lambda expression takes more than one parameter, all the parameter names must be specified
           within the parentheses, i.e., ( pName1, pName2, pName3 ). Parameter types are optional.
           If you are specifying parameter types, you must specify them for all the parameters. */
        LambdaType3 lambda3 = (a, b, c) -> a + b + c; // Valid
        // a, b -> a+b // Invalid, parameters must be within ( )
        lambda3 = (int a, int b, int c) -> a + b + c; // Valid
        // (int a, int b, c) -> a + b + c // Invalid

        /* Java 11 has added one more way to declare the parameters. You can use the var type for declaring
        the parameters, the parentheses, are required if you are using var. */
        lambda2 = (var a) -> a * a;

        /* To be able to apply an annotation, you need to have a type for the variable or you can use
           the var declaration, */
        // lambda2 = (@NotNull Integer a) -> a * a; // Valid
        // lambda2 = (@NotNull var a) -> a * a; //Valid

        /* Expression with or without a return value: You can simply put an expression on the right side of ->.
           If the expression has a return value and if the lambda expression is supposed to return a value,
           the compiler will insert a return statement on it own. You must not write the return keyword. */
        lambda2 = a -> a + 2;
        // lambda2 = a -> return a + 2 // Invalid, must not have return keyword

        /* Similarly, an expression that doesn't return any value can also be used directly as the body of the
           lambda expression. */
        LambdaType4 lambda4 = (a, b) -> System.out.println(a + b);

        /* Block of code with or without a return value: If you have multiple lines of code, you must write them
           within curly braces, i.e., { }. If the expression is supposed to return a value, you must use a return
           statement to return the desired value. You can use this syntax even if you have just one statement in
           the body. Unlike lambdas with just an expression as their bodies, the statements within the block end with
           a semi-colon. */
        lambda2 = (a) -> {
            int x = 2;
            int y = x+a;
            return y;
        };

        LambdaType5 lambda5 = () -> {
            int x = 2;
            int y = 3;
            System.out.println(x+y);
        };
    }
}
