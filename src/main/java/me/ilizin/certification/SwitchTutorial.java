package me.ilizin.certification;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SwitchTutorial {

    public void part1() {
        /*
        * A switch statement allows you to select which code blocks should be executed based on a variable value.
        * A switch statement can be written in the old way and the new way (since Java 14).
        * The old way uses a colon (:) to separate the case label from the case block, while the new way uses an arrow (->).
        *
        * Depending on the selector expression value, the control will enter the code block associated
        * with that particular switch label and keep on executing statements until it finds a break statement.
        * If the value doesn't match with any of the case labels, the control enters the default block.
        * If there is no default block, the control does not enter the switch block.
        * */
        int switchExpression = 2;
        // The expression within () is named selector expression
        switch (switchExpression) {  // The switch block starts here
            case 0:
                System.out.println("The switchExpression is 0");
                break;
            case 1: {
                System.out.println("The switchExpression is 1");
                break;
            }//enclosing code within { } is optional
            //associating multiple case labels with the same case block
            case 2, 3:
                System.out.println("The switchExpression might be 2 or 3");
                break;

            //associating multiple case labels with the same case block
            case 4:
            case 5: // This is named case label
                System.out.println("The switchExpression might be 4 or 5");
                break;
            default: // This is named default label, both case labels and default labels are named switch labels
                // There can be at most one default block in a switch statement.
                // The default block is executed if the
                // value of the switch expression does not match with any of the case labels.
                // The code associated with a switch label is called switch block
                System.out.println("The switchExpression is different from 0, 1, 2, 3, 4 or 5");
                break;
        } // The switch block ends here

        /* Case labels must consist of one or more compile time constants that are assignable to the
           type of the selector expression.
         */

        String switchExpressionStr = "hello";
        switch(switchExpressionStr) {
            // Valid because "1", "2" and "1" + "2" are compile time constants assignable to String.
            case "1", "2" : System.out.println("one or two");
            case "1" + "2" : System.out.println("one plus two");
            //will not compile because "abc".toUpperCase() is not a compile time constant
            // case "abc".toUpperCase() : System.out.println("ABC");
        }

        byte b = 10;
        // A switch statement doesn't necessarily have to have a case label or the default label.
        switch (b) {
            // It won't compile because 10000 is too large to fit into a byte
            // case 10000 : System.out.println("10000");
        }

        // It is common to use a single variable as the selector expression but you can use any expression inside the switch.
        byte b2 = 10;
        switch (b2 + 1) { // type of the selector expression here is now int due to numeric promotion
            case 1000 : // 1000 can fit into an int
                System.out.println("1000");
        }

        // Java does not impose any particular order for the case and the default block,
        // except in special situations involving null and pattern matching.
        // It's common to have the default block at the end of a switch block, but you can have it
        // at the beginning also. Case and default blocks order in combination
        // with the use of the break statement determines the case block that get executed.

        // It prints:
        // default
        // 20
        b2 = 10;
        switch (b2) {
            default : System.out.println("default");
            case 20 : System.out.println("20");
        }

        // the break statement is optional.
        // This is called "fall through" behavior
        // The control entered at the block labelled case 0, and executed all the other switch blocks
        int c = 0;
        switch (c) {  //switch block starts here
            case 0 : System.out.println("0");
            case 1 : System.out.println("1");
            case 2 : System.out.println("2");
            default : System.out.println("default");
        }

        // The code blocks associated with the switch labels are called switch blocks.
        // In a switch statement written using the old syntax
        // the switch block might be empty, it could contain zero or more "expression statements", it could contain zero or more code blocks,
        // or it may throw an exception.
        int i = 0;
        switch(i) {
            case 0 : i++; //single expression statement
            case 1 : i++;i++; //multiple expression statements
            case 2 : { int w = 0; w++; } // single code block
            case 3 :  { int w = 0; w++; }  { int w = 0; w++; } // multiple code blocks
            case 4: //empty
            case 5: throw new RuntimeException("Invalid value for i");
        }

    }

    public void part2() {
        /* Prior to Java 21, the switch selector expression was restricted to only a few types: byte, short, char,
           int, their wrapper types, String, and enums
           In Java 21, the following types are allowed:
           1. A limited set of integral types (byte, char, short, int), and their wrapper classes.
           2. The enum type
           3. String expressions since Java 7
           4. Since to Java 21, null as a case constant is allowed.
              Prior to Java 21 if the selector expression returned null, a NullPointerException would be thrown.
           5. With Java 21, the selector expression is allowed to  return any reference type (including all wrapper types).
           */
        String str = null;
        switch (str) {
            case null -> System.out.println("str is null");
            default -> System.out.println("str is not null");
            //the above two lines can also be combined into one line:
            //case null, default -> System.out.println("null or default");
        }

        switch (str) {
            // default can be only combined with case null
            case null, default -> System.out.println("str is null or default");
        }

        /* To maintain backward compatibility, a null value does not match the default label.
        *  Without the case null block, a switch statement will cause a NullPointerException to be thrown if the selector
        * expression returns null.
        * */
        try {
            switch (str) {
                // default can be only combined with case null
                default -> System.out.println("str null or default");
            }
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException was thrown");
        }
    }

    public void part3 () {
        /* There is a new syntax available since Java 14 , Here are a few points
         1. Here are a few points
         2. Every switch label is associated with either a single expression statement or single a code block
         3. The switch statement breaks automatically after
            the expression or the block executes. It is valid to write a break statement in the code block but it is redundant
         4. The new syntax has only one way to allow multiple values to be associated with a case block.
         5. Cannot mix old and new syntax in the same switch statement.
         */

        int i = 0;
        switch(i) {
            case 0 -> System.out.println("0");
            //break; //invalid only single expression statement or single a code block are allowed
            case 1 -> {
                System.out.println("1");
                break; //valid, but redundant
            } //enclosing code within { } is optional
            case 2, 3 -> System.out.println("2 or 3");
            //cannot do this
            //case 4 -> case 5 -> System.out.println("Four or five arguments");//invalid
        }
    }

    public void part4 () {
        // Enums are used very often in a switch statement.

        DayOfWeek dow = LocalDate.now().getDayOfWeek();

         switch(dow) {
             case MONDAY:
                 System.out.println("MONDAY");
                 break;

             // Prior to Java 21, the case statements could only use constant without the enum name
             // To following will cause a compilation error.
             case DayOfWeek.TUESDAY:
                 System.out.println("TUESDAY");
                 break;
             default:
                 System.out.println("ANOTHER DAY");
         }
        dow = DayOfWeek.THURSDAY; // It prints SAT/SUN
        switch(dow){
            case MONDAY, TUESDAY -> System.out.println("MON/TUE");
            case WEDNESDAY, FRIDAY -> System.out.println("WED - FRI");
            default -> System.out.println("SAT/SUN");
        }

        dow = DayOfWeek.THURSDAY; // It prints SAT/SUN
        switch(dow){
            // TUESDAY is just a label applied to the print statement.
            case MONDAY: TUESDAY: System.out.println("MON/TUE"); break;
            case WEDNESDAY: FRIDAY: System.out.println("WED - FRI");
            default : System.out.println("SAT/SUN");
        }
    }

    public void part5 () {
        /* A switch statement does not have a return value and thus,
        cannot be used within an expression.
        In Java 14, switch expressions were introduced exactly for this purpose.

         1. You may write a switch expression using the old syntax as well as the new syntax.
         2. The value of a switch expression is generated using
            a yield statement or by directly using an expression on the right side of the ->.
        3. The old syntax requires a yield statement, while the new syntax does not require it.
        If you want to use yieldwith the new syntaxmyou need to put the code within {and }.
         4. You may use any expression in the switch blocks (and not just expression statements, as is the case with switch
            statements) as long as the type of the expression is compatible with the type of the value expected from the
            switch expression.
         */

        double a = compute(2, 3);
        a = compute2(2, 3);

        // Switch expressions must have a value, Therefore, you cannot have a switch expression that has an execution
        // path that doesn't generate any value, so this won't compile.
        // A switch expression must be "exhaustive", which means,
        // it must provide an execution path that returns a value for every possible value of the selector expression.
        // Thus, you must either provide a case block for every value of the selector expression or provide a default block.

        /*int b  = 0;
        return 1 * switch(b){
            case 0 -> 0.1;
            case 1 -> 0.2;
        };*/

        /* A switch expression is allowed to return different types of values from different paths of execution.
            The only acceptable type is double, because a variable of type double can be assigned all three types of values.
            The type of switch expression is always determined by the compiler to be the most specific
            type that is compatible with all of the types of the values that the switch expression can possibly return.
* */
            int selector = 3;
            double value =  switch (selector) {
            // int value =  switch (selector) { // I won't compile
            case 0 -> 0.1;
            case 1 -> 2;
            default -> 'x';
        };
    }

    public void part6 () {
        /* If/else statement can benefit from pattern matching with instanceof.
        The pattern matching feature is extended to switch.
        The following code listing shows a decision making logic written using the
        switch statement written using new as well as old syntax: */


        /*
        The code tries to match the actual type of the object referred to by
        obj with the reference types given in the case labels. The block associated with the case label that matches
        with the type of the object is then executed. For each of the case labels, a pattern variable is also defined,
        which can be used within that case block. The switch statement with the old syntax requires
        a break statement for each case block. */

        /*
            Exhaustive - A regular switch statement need not be exhaustive but a switch statement that uses
            pattern matching is required to be exhaustive. A switch expression must always be exhaustive

        */

        Object object = 10;
        //using the new arrow syntax, it produces "Integer 10"
        switch (object) {

            case Integer i -> System.out.println("Integer " + i);
            case Double d -> {
                System.out.println("Double " + d);
                break; //allowed but redundant
            }
            default -> System.out.println("Object " + object);
        }

        //using the old syntax
        switch (object) {
            case Integer i : System.out.println("Integer " + i); break;
            case Double d : System.out.println("Double " + d); break;
            default : System.out.println("Object " + object);
        }

        /* The "fall through" behavior is not permitted when using pattern matching.
           So, a break statement is required in each case block in the old syntax.
               The reason for prohibiting fall through is clear from the following code:

           If obj points to an Integer and if the first case is allowed to fall through to the second one,
           there will be a problem because the second case block expects str to be initialized to point to a String */

        Object object2 = 2;
        switch (object2) {
            case Integer i : System.out.println(i);
            // case String str : System.out.println(str.length()); // won't compile
            default : System.out.println(object2);
        }

        /* However, it is possible to fall through to case null and default because no variables are expected
           to be available in these blocks. Therefore, the following is valid:*/

        /* The matching is done in the order of appearance of case labels.
           It is therefore, important to specify case labels in the order of increasing "dominance".
        * */
        switch(object2) {
            // A pattern variable is required with each case label, except default and null
            case Integer i : System.out.println(i); break; //fall through is NOT allowed here
            // String i is also valid
            case String str : System.out.print(str.length()); //fall through is allowed here
            case null : System.out.print("null"); //fall through is allowed here
            default : System.out.print(object2);
        }


        /* A case label dominates another case label if every value that matches another case label also matches
        this case label but not vice-versa. For example, case Object dominates case Number n, because every value
        that matches Number also matches Object. Thus, you cannot put case Object o before case Number n. The reason is simple.
        If you do that, then case Number n will become unreachable. default label can only be used at the end of the switch
        block because default dominates every other case label and any case label after default will be unreachable. */

        Object object3 = new String();
        switch (object3) {

            case String str : System.out.print(str.length()); break;//fall through is allowed here
            case Object str : System.out.print(str); // Before String str won't compile

                // case null does not dominate any other label and is dominated only by default.
                // Thus, a case null may appear at any position but not after default.
            case null : System.out.print("null"); //fall through is allowed here
            // default : System.out.print(object3); // won't compile unreachable because of Object str
        }
    }

    // using the old syntax and the yield statement
    double compute (double x, int y){
        return x * switch(y) {
            case 0 : yield 0.1;
            case 1 : { yield 0.2; } //enclosing the code within { } is optional
            default :  yield 0.3;
        };
    }

    // using the new arrow -> syntax
    double compute2 (double x, int y){
        return x * switch(y){
            case 0 -> 0.1; //no yield statement required here
            //case 1 -> { 0.2; } //can't use { } without a yield statement inside
            //case 1 -> yield 0.2; //yield statement must be inside { }
            case 1 -> { yield 0.2; } //fine
            default -> 0.3;
        };
    }
}