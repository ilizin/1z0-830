package me.ilizin.certification.suitch;

public class SwitchExpression {

    public static void main(String[] args) {

        /* A switch statement does not have a return value and thus, cannot be used within an expression.
           In Java 14, switch expressions were introduced exactly for this purpose.
           Some basic rules to write switch expressions correctly:
            1. You may write a switch expression using the old syntax as well as the new syntax.
            2. The value of a switch expression is generated using
               a yield statement or by directly using an expression on the right side of the ->.
            3. The old syntax requires a yield statement, while the new syntax does not require it.
               If you want to use yield with the new syntax you need to put the code within { }.
            4. You may use any expression in the switch blocks (and not just expression statements, as is the case with
               switch statements) as long as the type of the expression is compatible with the type of the value
               expected from the switch expression. */

        double x = 2;
        int y = 3;
        // using the old syntax and the yield statement
        double result = x * switch (y) {
            case 0:
                yield 0.1;
            case 1: {
                yield 0.2;
            } // Enclosing the code within { } is optional
            default:
                yield 0.3;
        };
        System.out.print(result);

        // using the new arrow -> syntax
        result = x * switch (y) {
            case 0 -> 0.1; // no yield statement required here
            //case 1 -> { 0.2; } //can't use { } without a yield statement inside
            //case 1 -> yield 0.2; // yield statement must be inside { }
            case 1 -> {
                yield 0.2;
            } //fine
            default -> 0.3;
        };
        System.out.print(result);

        /* Switch expressions must have a value, Therefore, you cannot have a switch expression that has an execution
           path that doesn't generate any value, so this won't compile.
           A switch expression must be "exhaustive", which means, it must provide an execution path that returns
           a value for every possible value of the selector expression. */

        /* int b  = 0;
        return 1 * switch(b) {
            case 0 -> 0.1;
            case 1 -> 0.2;
        };*/

        /* A switch expression is allowed to return different types of values from different paths of execution.
            The only acceptable type is double, because a variable of type double can be assigned all
            three types of values.
            The type of switch expression is always determined by the compiler to be the most specific
            type that is compatible with all of the types of the values that the switch expression
            can possibly return. */

        int selector = 3;
        // int value =  switch (selector) { // I won't compile
        double value = switch (selector) {
            case 0 -> 0.1;
            case 1 -> 2;
            default -> 'x';
        };
        double test = 'x';
        int test2 = 'x';
    }
}
