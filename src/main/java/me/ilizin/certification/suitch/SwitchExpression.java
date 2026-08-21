package me.ilizin.certification.suitch;

public class SwitchExpression {

    /* A switch statement does not have a return value and thus, cannot be used within an expression.
       In Java 14, switch expressions were introduced exactly for this purpose.
       Some basic rules to write switch expressions correctly:
       1. You may write a switch expression using the old syntax as well as the new syntax.
       2. The value of a switch expression is generated using a yield statement or by directly using an expression
          on the right side of the ->.
       3. The old syntax requires a yield statement, while the new syntax does not require it.
          If you want to use yield with the new syntax you need to put the code within { }.
       4. You may use any expression in the switch blocks (and not just expression statements, as is the case with
          switch statements) as long as the type of the expression is compatible with the type of the value
          expected from the switch expression. */
    public static void main(String[] args) {

        System.out.println();
        /* using the old syntax and the yield statement */
        int y = 3;
        double result = switch (y) {
            case 0: yield 0.1;
            // case 0: 0.1; // Invalid
            case 1: { /* Enclosing the code within { } is optional */
                double a = 0.2;
                yield a; // Commenting this line is valid, it return 0.3
            }
            default: yield 0.3;
        };
        System.out.println(result);

        System.out.println();
        /* using the new arrow -> syntax */
        y = 1;
        result = switch (y) {
            case 0 -> 0.1; // no yield statement required here
            //case 1 -> { 0.2; } //can't use { } without a yield statement inside
            //case 1 -> yield 0.2; // yield statement must be inside { }
            case 1 -> {
                yield 0.2;
            }
            default -> 0.3;
        };
        System.out.println(result);

        /* Switch expressions must have a value, must be "exhaustive", therefore, you cannot have a switch expression
           that has an execution path that doesn't generate any value, so this won't compile. */
        /*int b  = 0;
        result = switch(b) {
            case 0 -> 0.1;
            case 1 -> 0.2;
         };*/

        System.out.println();
        /* A switch expression is allowed to return different types of values from different paths of execution.
           The only acceptable type is double, because a variable of type double can be assigned all
           three types of values. */
        int selector = 3;
        // int value =  switch (selector) { // I won't compile
        double value = switch (selector) {
            case 0 -> 0.1;
            case 1 -> 2;
            default -> 'x';
        };
        double test = 'x';
        int test2 = 'x';
        System.out.println(value);

        System.out.println();
        /* The type of switch expression is always determined by the compiler to be the most specific
        type that is compatible with all of the types of the values that the switch expression
        can possibly return. */
        Object obj = switch ("test") {
            case "test": yield 'x';
            default: yield "test";
        };
        System.out.println(obj.getClass());
    }
}
