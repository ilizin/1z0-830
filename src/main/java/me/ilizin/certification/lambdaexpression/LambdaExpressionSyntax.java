package me.ilizin.certification.lambdaexpression;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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

    /* A lambda expression has two parts separated by the "arrow" operator, i.e., ->. The left side is for variable
       declarations and the right side is for the code that you want executed. Just like a method, a lambda expression
       also can have any number of parameters and can return (or not return) a value.
       The parameter types of a lambda expression (if specified explicitly) must match exactly with the parameter
       types of the method declared in the interface and the type of the returned value must be covariant with the
       return type of the interface method. */
    public static void main(String[] args) {

        System.out.println();
        /* If a lambda expression takes no parameters, the parameter part of the expression must have an empty set
           of parentheses, i.e., ( ) */
        LambdaType lambda1 = () -> true; // Valid
        // -> 1 // Invalid, missing variable declaration part
        System.out.println(lambda1.test());

        System.out.println();
        /* If a lambda expression takes exactly one parameter, the parameter name may be specified within parentheses, i.e.,
           (pName) or without the parentheses, i.e., pName. If you want to include the parameter type then you will need
           to use parentheses. */
        LambdaType2 lambda2 = a -> a * a;
        lambda2 = (a) -> a * a;
        lambda2 = (int a) -> a * a;
        // int a -> a * a // Invalid
        System.out.println(lambda2.test(2));

        System.out.println();
        /* If a lambda expression takes more than one parameter, all the parameter names must be specified
           within the parentheses, i.e., ( pName1, pName2, pName3 ). Parameter types are optional.
           If you are specifying parameter types, you must specify them for all the parameters. */
        LambdaType3 lambda3 = (a, b, c) -> a + b + c;
        // a, b -> a + b // Invalid, parameters must be within ( )
        lambda3 = (int a, int b, int c) -> a + b + c;
        // (int a, int b, c) -> a + b + c // Invalid
        System.out.println(lambda3.test(2, 3, 4));

        System.out.println();
        /* Java 11 has added one more way to declare the parameters. You can use the var type for declaring
           the parameters, the parentheses, are required if you are using var. */
        lambda2 = (var a) -> a * a;
        System.out.println(lambda2.test(3));

        /* To be able to apply an annotation, you need to have a type for the variable or you can use the var declaration, */
        // lambda2 = (@NotNull Integer a) -> a * a; // Valid
        // lambda2 = (@NotNull var a) -> a * a; // Valid

        System.out.println();
        /* Expression with or without a return value: You can simply put an expression on the right side of ->,
           if the expression has a return value, the compiler will insert a return statement on it own. You must
           not write the return keyword. */
        lambda2 = a -> a + 2;
        // lambda2 = a -> return a + 2 // Invalid, must not have return keyword
        System.out.println(lambda2.test(3));
        lambda2 = a -> { return a + 2; };
        System.out.println(lambda2.test(3));
        // lambda2 = a -> { a + 2; }; // Invalid, must have return keyword

        System.out.println();
        /* Similarly, an expression that doesn't return any value can also be used directly as the body of the
           lambda expression. */
        LambdaType4 lambda4 = (a, b) -> System.out.println(a + b);
        lambda4.test(3, 2);

        System.out.println();
        /* If you have multiple lines of code, you must write them within curly braces, i.e., { }. If the expression
           is supposed to return a value, you must use a return statement to return the desired value. You can use this
           syntax even if you have just one statement in the body. Unlike lambdas with just an expression as their bodies,
           the statements within the block end with a semi-colon. */
        lambda2 = (a) -> {
            int x = 2;
            int y = x + a;
            return y;
        };
        LambdaType5 lambda5 = () -> {
            int x = 2;
            int y = 3;
            System.out.println(x + y);
        };
        System.out.println(lambda2.test(5));
        lambda5.test();

        System.out.println();
        /* The variables that you define in the variable section of a lambda expression exist in the same scope
           as which the lambda expression itself exists. This means, you cannot redefine the variables that already
           exist in that scope. */
         List<String> names = Arrays.asList("alex", "bob", "casy", "abel");
         for(String n :  names){
            //Predicate<String> p = n -> n.startsWith("a"); // Will not compile
            Predicate<String> p = x -> x.startsWith("a");
            if(p.test(n))  {
                System.out.println(n);
            }
         }

        System.out.println();
        /* It is possible to access a variable that is in scope of the lambda expression from within the lambda
           expression's body but only if that variable is declared as final or is "effectively final".
           This rule is applicable only for local variables and not for instance or static fields. It is possible
           to access instance and static fields from the body of a lambda expression even if they are not final. */
        names = Arrays.asList("alex", "bob", "casy", "abel");
        int x = 0;
        for(String n :  names) {
            Predicate<String> p = k-> {
                System.out.println(n); // Valid n is effectively final
                // System.out.println(x); // Will not compile
                return k.startsWith("a");
            };
            // n = null; // If uncomment the System.out.println(n); won't compile
            if(p.test(n))  {
                System.out.println(n);
            }
        }
        x = 1; //x is being changed here
    }
}
